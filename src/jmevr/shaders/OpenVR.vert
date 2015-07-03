attribute vec4 inPosition;

attribute vec2 inTexCoord;   // m_inUVred
attribute vec2 inTexCoord2;  // m_inUVgreen
attribute vec2 inTexCoord3;  // m_inUVblue

varying vec2 UVred;
varying vec2 UVgreen;
varying vec2 UVblue;

void main() {     
    vec2 pos = inPosition.xy * 2.0 - 1.0;
    gl_Position = vec4(pos, 0.0, 1.0);    
    UVred = inTexCoord;
    UVgreen = inTexCoord2;
    UVblue = inTexCoord3;
}