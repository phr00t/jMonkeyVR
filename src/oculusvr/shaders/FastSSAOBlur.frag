uniform sampler2D m_Texture;
//uniform sampler2D m_DepthTexture;
uniform sampler2D m_SSAOMap;
//uniform vec2 g_Resolution;
//uniform bool m_UseOnlyAo;
//uniform bool m_UseAo;
//uniform bool m_UseSmoothing;
//uniform bool m_SmoothMore;
//uniform float m_XScale;
//uniform float m_YScale;
//uniform vec2 m_FrustumNearFar;
//const float epsilon = 0.005;
varying vec2 texCoord;

//float random (vec4 seed4) {
//float dot_product = dot(seed4, vec4(12.9898,78.233,45.164,94.673));
//return fract(sin(dot_product) * 43758.5453);
//}

void main(){
    /*vec4 ssao = vec4(0.0);//texture2D( m_SSAOMap,texCoord);
    if (m_UseSmoothing) {
        for (int i = 0; i < 4; i++) {
            ssao += texture2D(m_SSAOMap,texCoord + (random(vec4(texCoord,-texCoord)*vec4(float(-i)))/200.0));
            if (m_SmoothMore) ssao += texture2D(m_SSAOMap,texCoord + (random(vec4(-texCoord,texCoord)*vec4(float(i)))/100.0));
        }
        if (m_SmoothMore) {
            ssao /= 8.0;
        } else {
            ssao /= 4.0;
        }
    } else { */
        //ssao = texture2D(m_SSAOMap,texCoord);
    //}

    gl_FragColor = texture2D(m_Texture,texCoord) * texture2D(m_SSAOMap,texCoord);

    //if (!m_UseAo && !m_UseOnlyAo)
    //gl_FragColor = color;
    //else if (m_UseAo && m_UseOnlyAo)
    //gl_FragColor = ssao;
    //else
    //gl_FragColor = color*ssao;
}
