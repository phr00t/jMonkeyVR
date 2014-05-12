uniform vec2 g_Resolution;
uniform vec2 m_FrustumNearFar;
uniform sampler2D m_Texture;
uniform sampler2D m_Normals;
uniform sampler2D m_Noise;
uniform sampler2D m_DepthTexture;
uniform vec3 m_FrustumCorner;
uniform float m_SampleRadius;
uniform float m_Intensity;
uniform float m_Scale;
uniform float m_Bias;
//uniform bool m_EnableFD;
//uniform float m_SampleRadiusFD;
//uniform float m_IntensityFD;
//uniform float m_ScaleFD;
//uniform float m_BiasFD;
uniform vec3 m_Samples[12];

//uniform bool m_UseDistanceFalloff;
//uniform float m_FalloffStartDistance;
//uniform float m_FalloffRate;

varying vec2 texCoord;

//float depthv;
//float shadowFactor;

vec3 getPosition(in vec2 uv){
    //depthv = texture2D(m_DepthTexture,uv).r;
    float depth= (2.0 * m_FrustumNearFar.x) / (m_FrustumNearFar.y + m_FrustumNearFar.x - texture2D(m_DepthTexture,uv).r * (m_FrustumNearFar.y-m_FrustumNearFar.x));
    float x = mix(-m_FrustumCorner.x, m_FrustumCorner.x, uv.x);
    float y = mix(-m_FrustumCorner.y, m_FrustumCorner.y, uv.y);
    return depth* vec3(x, y, m_FrustumCorner.z);
}

vec3 getNormal(in vec2 uv){
    return normalize(texture2D(m_Normals, uv).xyz * 2.0 - 1.0);
}

vec3 getRandom(in vec2 uv){
    float rand=(fract(uv.x*(g_Resolution.x/2.0))*0.25)+(fract(uv.y*(g_Resolution.y/2.0))*0.5);
    return normalize(vec3(rand,rand,rand));
}

vec3 getNoise(in vec2 uv){
    vec4 noise = texture2D(m_Noise, uv*25.0);
    return (noise.xyz);
}

float doAmbientOcclusion(in vec2 tc, in vec3 pos, in vec3 norm){
    vec3 diff = getPosition(tc)- pos;
    vec3 v = normalize(diff);
    float d = length(diff) * m_Scale;
    return step(0.00002,d)*max(0.0, dot(norm, v) - m_Bias) * ( 1.0/(1.0 + d) ) * (m_Intensity+0.075/*shadowFactor*/) * smoothstep(0.00002,0.0027,d);
}

vec3 reflection(in vec3 v1,in vec3 v2){
    vec3 result= 2.0 * dot(v2, v1) * v2;
    result=v1-result;
    return result;
}

/*float doAmbientOcclusionFD(in vec2 tc, in vec3 pos, in vec3 norm){
    vec3 diff = getPosition(tc)- pos;
    vec3 v = normalize(diff);
    float d = length(diff) * m_ScaleFD;
    return step(0.00002,d)*max(0.0, dot(norm, v) - m_BiasFD) * ( 1.0/(1.0 + d) ) * (m_IntensityFD+shadowFactor) * smoothstep(0.00002,0.0027,d);
}*/

void main(){
    float result;
    vec3 position = getPosition(texCoord);
    //if(depthv==1.0){
    //    gl_FragColor=vec4(1.0);
    //    return;
    //}
    vec3 normal = getNormal(texCoord);
    vec3 rand = getRandom(texCoord);

    float ao = 0.0;
    //shadowFactor = 0.075;//0.0;//(position.z*0.002);
    float rad = m_SampleRadius/position.z + 0.075; //shadowFactor;
    //float radFD = m_SampleRadiusFD/position.z+shadowFactor;

    //int iterations = 12;
    /*if (m_UseDistanceFalloff) {
        float LOG2 = 1.442695;
        vec2 m_DistanceFrustum = vec2(1.0,m_FalloffStartDistance);
        float depth= (m_DistanceFrustum.x / 4.0) / (m_DistanceFrustum.y - depthv * (m_DistanceFrustum.y));

        float falloffFactor = exp2( -m_FalloffRate * m_FalloffRate * depth *  depth * LOG2 );
        falloffFactor = clamp(falloffFactor, 0.0, 1.0);

        if (falloffFactor < 1.0) {
            for (int j = 0; j < iterations; ++j) {
                vec3 coord1 = reflection(vec3(m_Samples[j]), rand) * vec3(rad);
                ao += doAmbientOcclusion(texCoord + coord1.xy * 0.125, position, normal) - shadowFactor;
                // Fine Detail
                //if (m_EnableFD) {
                //vec3 coord2 = reflection(vec3(m_Samples[j]), rand) * vec3(radFD*0.5);
                //ao += doAmbientOcclusionFD(texCoord + coord2.xy * 0.05, position, normal) * (0.25-shadowFactor);
                //}
            }
            ao /= float(iterations) * (2.35-shadowFactor);
            result = 1.0-ao;
            result = mix(result,1.0,1.0-falloffFactor);
        } else {
            result = 1.0;
        }
    } else {*/
        //vec3 rad3 = vec3(rad);
        vec3 sampler;
        vec2 coord1;
        for (int j = 0; j < 12; ++j) { // 12 iterations
            sampler = m_Samples[j];
            float math = 2.0 * (rand.x*sampler.x + rand.y*sampler.y + rand.z*sampler.z);
            coord1.xy = (sampler.xy - (math * rand.xy)) * rad;
            ao += doAmbientOcclusion(texCoord + coord1.xy * 0.125, position, normal) - 0.075; //shadowFactor;
        }
        //ao /= 12.0 * (2.35-0.075/*shadowFactor*/); // 12.0 iterations
        ao /= 27.3;
        result = 1.0-ao;
    //}

    //gl_FragColor = vec4(vec3(result),1.0);
    gl_FragColor = vec4(result, result, result, 1.0);
}
