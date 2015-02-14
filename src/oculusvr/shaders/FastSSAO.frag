uniform vec2 g_Resolution;
uniform vec2 m_FrustumNearFar;
uniform sampler2D m_Texture;
uniform sampler2D m_Normals;
uniform sampler2D m_DepthTexture;
uniform vec3 m_FrustumCorner;
uniform float m_SampleRadius;
uniform float m_Intensity;
uniform float m_Scale;
uniform float m_Bias;


varying vec2 texCoord;

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
    return step(0.00002,d)*max(0.0, dot(norm, v) - m_Bias) * ( 1.0/(1.0 + d) ) * (m_Intensity+0.075) * smoothstep(0.00002,0.0027,d);
}

void main(){
    float result;
    vec3 position = getPosition(texCoord);
    vec3 normal = texture2D(m_Normals, texCoord).xyz * 2.0 - 1.0;

    float ao = 0.0;
    float rad = 0.06 * (m_SampleRadius/max(position.z, 32.0) + (fract(texCoord.x*(g_Resolution.x/2.0))*0.125) + (fract(texCoord.y*(g_Resolution.y/2.0))*0.25));

    ao += doAmbientOcclusion(texCoord + vec2( rad,  rad), position, normal);
    ao += doAmbientOcclusion(texCoord + vec2(-rad,  rad), position, normal);
    ao += doAmbientOcclusion(texCoord + vec2( rad, -rad), position, normal);
    ao += doAmbientOcclusion(texCoord + vec2(-rad, -rad), position, normal);

    ao += doAmbientOcclusion(texCoord + vec2(-rad, 0.0), position, normal);
    ao += doAmbientOcclusion(texCoord + vec2( rad, 0.0), position, normal);
    ao += doAmbientOcclusion(texCoord + vec2(0.0, -rad), position, normal);
    ao += doAmbientOcclusion(texCoord + vec2(0.0,  rad), position, normal);

    rad *= 0.7;

    ao += doAmbientOcclusion(texCoord + vec2(-rad, -rad), position, normal);
    ao += doAmbientOcclusion(texCoord + vec2( rad, -rad), position, normal);
    ao += doAmbientOcclusion(texCoord + vec2(-rad,  rad), position, normal);
    ao += doAmbientOcclusion(texCoord + vec2( rad,  rad), position, normal);

    ao /= 34.0;
    result = 1.0 - ao;

    gl_FragColor = texture2D(m_Texture, texCoord) * vec4(result, result, result, 1.0);
}
