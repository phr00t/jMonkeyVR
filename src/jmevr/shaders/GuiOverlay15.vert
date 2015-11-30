// import the following for VR instancing
#import "jmevr/shaders/InstanceVR.glsllib"

in vec3 inPosition;
in vec2 inTexCoord;
out vec2 texCoord1;

void main(){
    texCoord1 = inTexCoord;
    vec4 modelSpacePos = vec4(inPosition, 1.0);
    gl_Position = TransformWorldViewProjectionVR(modelSpacePos);
}