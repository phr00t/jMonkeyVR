#import “Common/ShaderLib/MultiSample.glsllib”
 
uniform sampler2D m_Texture;
in vec2 texCoord;
 
uniform vec2 m_LensCenter;
uniform vec2 m_ScreenCenter;
uniform vec2 m_Scale;
uniform vec2 m_ScaleIn;
uniform vec4 m_HmdWarpParam;
 
vec2 HmdWarp(vec2 texIn){
    vec2 theta = (texIn - m_LensCenter) * m_ScaleIn;
    float  rSq= theta.x * theta.x + theta.y * theta.y;
    vec2 theta1 = theta * (m_HmdWarpParam.x + m_HmdWarpParam.y * rSq +
    m_HmdWarpParam.z * rSq * rSq + m_HmdWarpParam.w * rSq * rSq * rSq);
    return m_LensCenter + m_Scale * theta1;
}
 
void main(){
    vec2 tc = HmdWarp(texCoord);
    if (any(notEqual(clamp(tc, m_ScreenCenter-vec2(0.5,0.5), m_ScreenCenter+vec2(0.5, 0.5)) - tc, vec2(0.0, 0.0)))){
        discard;
    }
    else{
        gl_FragColor = texture2D(m_Texture, tc);
    }
    //gl_FragColor = texture2D(m_Texture, texCoord);
}