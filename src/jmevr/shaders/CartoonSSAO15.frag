uniform vec2 g_Resolution;
uniform vec2 g_ResolutionInverse;
uniform vec2 m_FrustumNearFar;
uniform sampler2D m_Texture;
uniform sampler2D m_Normals;
uniform sampler2D m_DepthTexture;
uniform vec3 m_FrustumCorner;
uniform float m_SampleRadius;
uniform float m_Intensity;
uniform float m_Scale;
uniform float m_Bias;

in vec2 texCoord;

out vec4 outColor;

#define gl_FragColor outColor

vec4 fetchNormalDepth(vec2 tc){
    vec4 nd;
    nd.xyz = texture2D(m_Normals, tc).rgb;
    nd.w   = 150.0 * texture2D(m_DepthTexture,   tc).r;
    return nd;
}

vec3 getPosition(in vec2 uv){
    float depth= (2.0 * m_FrustumNearFar.x) / (m_FrustumNearFar.y + m_FrustumNearFar.x - texture2D(m_DepthTexture,uv).r * (m_FrustumNearFar.y-m_FrustumNearFar.x));
    float x = mix(-m_FrustumCorner.x, m_FrustumCorner.x, uv.x);
    float y = mix(-m_FrustumCorner.y, m_FrustumCorner.y, uv.y);
    return depth* vec3(x, y, m_FrustumCorner.z);
}

float doAmbientOcclusion(in vec2 tc, in vec3 pos, in vec3 norm){
    vec3 diff = getPosition(tc)- pos;
    vec3 v = normalize(diff);
    float d = length(diff) * m_Scale;
    return step(0.00002,d)*max(0.0, dot(norm, v) - m_Bias) * ( 1.0/(1.0 + d) ) * smoothstep(0.00002,0.0027,d);
}

void main(){
    float result;
    vec3 position = getPosition(texCoord);
    vec3 normal = texture2D(m_Normals, texCoord).xyz * 2.0 - 1.0;

    float rad = 0.06 * (m_SampleRadius/max(position.z, 32.0) + (fract(texCoord.x*(g_Resolution.x/2.0))*0.125) + (fract(texCoord.y*(g_Resolution.y/2.0))*0.25));
    float intens = m_Intensity + 0.075;

    float ao = doAmbientOcclusion(texCoord + vec2( rad,  rad), position, normal) * intens;
    ao += doAmbientOcclusion(texCoord + vec2(-rad,  rad), position, normal) * intens;
    ao += doAmbientOcclusion(texCoord + vec2( rad, -rad), position, normal) * intens;
    ao += doAmbientOcclusion(texCoord + vec2(-rad, -rad), position, normal) * intens;

    ao += doAmbientOcclusion(texCoord + vec2(-rad, 0.0), position, normal) * intens;
    ao += doAmbientOcclusion(texCoord + vec2( rad, 0.0), position, normal) * intens;
    ao += doAmbientOcclusion(texCoord + vec2(0.0, -rad), position, normal) * intens;
    ao += doAmbientOcclusion(texCoord + vec2(0.0,  rad), position, normal) * intens;

    rad *= 0.7;

    ao += doAmbientOcclusion(texCoord + vec2(-rad, -rad), position, normal) * intens;
    ao += doAmbientOcclusion(texCoord + vec2( rad, -rad), position, normal) * intens;
    ao += doAmbientOcclusion(texCoord + vec2(-rad,  rad), position, normal) * intens;
    ao += doAmbientOcclusion(texCoord + vec2( rad,  rad), position, normal) * intens;

    ao /= 34.0;
    result = 1.0 - (1.25 - max(16.0 - position.z, 0.0) / 25.0) * ao;

    // ok, done with ambient occlusion, do cartoon edge

    vec3 color = texture2D(m_Texture, texCoord).rgb;

    vec4 n1 = fetchNormalDepth(texCoord + vec2(-0.5, -0.5) * g_ResolutionInverse);
    vec4 n2 = fetchNormalDepth(texCoord + vec2( 0.5,  0.5) * g_ResolutionInverse);
    vec4 n3 = fetchNormalDepth(texCoord + vec2(-0.5,  0.5) * g_ResolutionInverse);
    vec4 n4 = fetchNormalDepth(texCoord + vec2( 0.5, -0.5) * g_ResolutionInverse);

    // fade lines off in the distance
    float d0 = (-0.00015625 * 0.7) / (texture2D(m_DepthTexture, texCoord).r - 1.0);
    //float avg = (n1.w + n2.w + n3.w + n4.w) * 0.25;
    //float d0 = (-0.00015625 * 0.7) / (avg - 1.0);
    if( d0 < 0.0001 ) d0 = 1.0;

    // Work out how much the normal and depth values are changing.
    vec4 diagonalDelta = abs(n1 - n2) + abs(n3 - n4);

    float normalDelta = dot(diagonalDelta.xyz, vec3(1.0));
    float totalDelta = diagonalDelta.w + normalDelta * 0.4;
   
    gl_FragColor.rgb = color.rgb * vec3(result) * (1.0 - clamp(totalDelta - d0, 0.0, 1.0));
    gl_FragColor.a = 1.0;
}
