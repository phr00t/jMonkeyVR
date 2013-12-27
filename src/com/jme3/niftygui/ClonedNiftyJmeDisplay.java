/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jme3.niftygui;

import com.jme3.renderer.ViewPort;

/**
 *
 * @author Rickard
 */
public class ClonedNiftyJmeDisplay extends NiftyJmeDisplay{
    
    public ClonedNiftyJmeDisplay(NiftyJmeDisplay niftyDisplay, final ViewPort viewport2){
        this.nifty = niftyDisplay.nifty;
        this.assetManager = niftyDisplay.assetManager;
        this.renderManager = niftyDisplay.renderManager;
        this.inputManager = niftyDisplay.inputManager;
        this.renderDev = niftyDisplay.renderDev;
        this.batchRendererBackend = niftyDisplay.batchRendererBackend;
        this.inputSys = niftyDisplay.inputSys;
        this.soundDev = niftyDisplay.soundDev;
        this.renderer = niftyDisplay.renderer;
        this.vp = viewport2;
        this.w = vp.getCamera().getWidth();
        this.h = vp.getCamera().getHeight();
        this.resourceLocation = niftyDisplay.resourceLocation;
    }
}
