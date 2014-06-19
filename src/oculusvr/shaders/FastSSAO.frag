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
uniform vec3 m_Samples[12];

varying vec2 texCoord;

vec3 getPosition(in vec2 uv){
    float depth= (2.0 * m_FrustumNearFar.x) / (m_FrustumNearFar.y + m_FrustumNearFar.x - texture2D(m_DepthTexture,uv).r * (m_FrustumNearFar.y-m_FrustumNearFar.x));
    float x = mix(-m_FrustumCorner.x, m_FrustumCorner.x, uv.x);
    float y = mix(-m_FrustumCorner.y, m_FrustumCorner.y, uv.y);
    return depth* vec3(x, y, m_FrustumCorner.z);
}

vec3 getNormal(in vec2 uv){
    return normalize(texture2D(m_Normals, uv).xyz * 2.0 - 1.0);
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
    vec3 normal = getNormal(texCoord);

    float ao = 0.0;
    float rad = m_SampleRadius/max(position.z, 16.0) + 0.075;
    float fade = 1.0 - max(16.0 - position.z, 0.0) / 32.0;

    vec3 sampler;
    vec2 coord1;
    for (int j = 0; j < 12; ++j) {
        sampler = m_Samples[j];
        float math = (sampler.x + sampler.y + sampler.z);
        coord1.xy = (sampler.xy - math) * rad;
        ao += doAmbientOcclusion(texCoord + coord1.xy * 0.125, position, normal) - 0.075;
    }

    ao /= 27.3;
    result = 1.0 - (ao * fade);

    gl_FragColor = vec4(result, result, result, 1.0);
}
