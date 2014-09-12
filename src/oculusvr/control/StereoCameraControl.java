/*
 * Copyright (c) 2009-2012 jMonkeyEngine
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'jMonkeyEngine' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package oculusvr.control;

import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.CameraControl;
import com.jme3.scene.control.Control;
import com.jme3.util.TempVars;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import oculusvr.input.OculusRift;
import oculusvr.util.OculusGuiNode.POSITIONING_MODE;

/**
 * This Control maintains a reference to a Camera,
 * which will be synched with the position (worldTranslation)
 * of the current spatial.
 * @author tim, reden
 */
public class StereoCameraControl extends CameraControl {

    private Quaternion lookDirection = new Quaternion();
    protected Camera camera2;
    private float camHalfDistance = 0f;
    private Vector3f cameraOffset = new Vector3f(), spatialOffset, tempf = new Vector3f();

    public StereoCameraControl(){
        super();
        cameraOffset.setX(camHalfDistance);
    }
    
    public StereoCameraControl(Camera camera, Camera camera2) {
        super(camera);
        this.camera2 = camera2;
        cameraOffset.setX(camHalfDistance);
    }
    
    public StereoCameraControl(Camera camera, Camera camera2, ControlDirection controlDir) {
        super(camera, controlDir);
        this.camera2 = camera2;
        cameraOffset.setX(camHalfDistance);
    }
    
    public void setSpatialOffset(Vector3f pos) {
        if( spatialOffset == null ) spatialOffset = new Vector3f();
        spatialOffset.x = pos.x;
        spatialOffset.y = pos.y;
        spatialOffset.z = pos.z;
    }
    
    public void setSpatialOffset(float x, float y, float z) {
        if( spatialOffset == null ) spatialOffset = new Vector3f();
        spatialOffset.x = x;
        spatialOffset.y = y;
        spatialOffset.z = z;
    }
    
    public void swapCameras() {
        Camera mycam = getCamera();
        setCamera(camera2);
        setCamera2(mycam);
    }
    
    public void setView(float tpf, Vector3f pos, Quaternion look) {
        setView(tpf, pos, look, false);
    }
    
    public void setView(float tpf, Vector3f pos, Quaternion look, boolean useOffset) {
        TempVars vars = TempVars.get();
        Camera camera = getCamera();
        
        lookDirection.set(OculusRift.getOrientation());                    
        vars.quat1.set(look).multLocal(lookDirection);
        
        // positional tracking
        vars.vect4.set(OculusRift.getPosition());
        look.mult(vars.vect4, vars.vect4);
        vars.vect4.addLocal(pos);
        if( spatialOffset != null && useOffset ) {
            vars.vect4.x += spatialOffset.x;
            vars.vect4.y += spatialOffset.y;
            vars.vect4.z += spatialOffset.z;
        }
        
        camera.setRotation(vars.quat1); //spatial.getWorldRotation().mult(lookDirection));
        vars.vect6.set(vars.quat1.mult(cameraOffset, vars.vect2)).addLocal(vars.vect4);
        camera.setLocation(vars.vect6);

        // negate cameraOffset
        vars.vect3.x = -cameraOffset.x;
        vars.vect3.y = -cameraOffset.y;
        vars.vect3.z = -cameraOffset.z;

        vars.vect5.set(camera.getRotation().mult(vars.vect3, vars.vect2)).addLocal(vars.vect4);
        camera2.setLocation(vars.vect5);
        camera2.setRotation(camera.getRotation());
        
        vars.release();
        
        // update gui node?
        if( OculusRift.getAppState() != null && OculusRift.getAppState().getGuiNode() != null &&
            OculusRift.getAppState().getGuiNode().getPositioningMode() != POSITIONING_MODE.MANUAL ) {
            OculusRift.getAppState().getGuiNode().positionGui();
        }
    }

    // fields used, when inversing ControlDirection:
    @Override
    protected void controlUpdate(float tpf) {
        Camera camera = getCamera();
        ControlDirection controlDir = this.getControlDir();
                
        if (spatial != null && camera != null) {
            switch (controlDir) {
                case SpatialToCamera:
                    setView(tpf, spatial.getWorldTranslation(), spatial.getWorldRotation(), true);
                    break;
                case CameraToSpatial:
                    // set the localtransform, so that the worldtransform would be equal to the camera's transform.
                    // Location:

                    TempVars vars = TempVars.get();
                    Vector3f vecDiff = vars.vect1.set(camera.getLocation()).subtractLocal(spatial.getWorldTranslation());
                    spatial.setLocalTranslation(vecDiff.addLocal(spatial.getLocalTranslation()));

                    // Rotation:
                    Quaternion worldDiff = vars.quat1.set(camera.getRotation()).subtractLocal(spatial.getWorldRotation());
                    spatial.setLocalRotation(worldDiff.addLocal(spatial.getLocalRotation()));
                    vars.release();
                    break;
            }
        }else if (spatial == null){
            throw new NullPointerException("Spatial can't be null!");
        } 
    }

    public Quaternion getLookDirection() {
        return getCamera().getRotation();
    }
    
    public Vector3f getForwardDirection() {
        return getLookDirection().getRotationColumn(2, tempf);
    }

    private static final String CONTROL_DIR_NAME = "controlDir";
    private static final String CAMERA_NAME = "camera";
    
    
    public void increaseDistance(){
        camHalfDistance += 0.001f;
        cameraOffset.setX(camHalfDistance);
        Logger.getLogger(StereoCameraControl.class.getName()).log(Level.INFO, "Cam offset: " + camHalfDistance);
    }
    
    public void decreaseDistance(){
        camHalfDistance -= 0.001f;
        cameraOffset.setX(camHalfDistance);
        Logger.getLogger(StereoCameraControl.class.getName()).log(Level.INFO, "Cam offset: " + camHalfDistance);
    }

    public float getCamHalfDistance() {
        return camHalfDistance;
    }

    public void setCamHalfDistance(float camHalfDistance) {
        this.camHalfDistance = camHalfDistance;
        cameraOffset.setX(camHalfDistance);
    }

    public Camera getCamera2(){
        return camera2;
    }
    
    public void setCamera2(Camera cam2){
        this.camera2 = cam2;
    }
    
    @Override
    public Control cloneForSpatial(Spatial newSpatial) {
        StereoCameraControl control = new StereoCameraControl(getCamera(), camera2, getControlDir());
        control.setSpatial(newSpatial);
        control.setEnabled(isEnabled());
        return control;
    }
    
    @Override
    public void read(JmeImporter im) throws IOException {
        super.read(im);
        InputCapsule ic = im.getCapsule(this);
        setControlDir(ic.readEnum(CONTROL_DIR_NAME, ControlDirection.class, ControlDirection.SpatialToCamera));
        setCamera((Camera)ic.readSavable(CAMERA_NAME, null));
    }

    @Override
    public void write(JmeExporter ex) throws IOException {
        super.write(ex);
        OutputCapsule oc = ex.getCapsule(this);
        oc.write(getControlDir(), CONTROL_DIR_NAME, ControlDirection.SpatialToCamera);
        oc.write(getCamera(), CAMERA_NAME, null);
    }
    
}