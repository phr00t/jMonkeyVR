attribute vec4 inPosition;

uniform vec2 m_inUVred;
uniform vec2 m_inUVgreen;
uniform vec2 m_inUVblue;

varying vec2 UVred;
varying vec2 UVgreen;
varying vec2 UVblue;

void main() {     
    vec2 pos = inPosition.xy * 2.0 - 1.0;
    gl_Position = vec4(pos, 0.0, 1.0);    
    UVred = m_inUVred;
    UVgreen = m_inUVgreen;
    UVblue = m_inUVblue;
}