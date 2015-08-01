/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmevr.util;

import com.fourthskyinteractive.dx4j.d3d11.D3D11;
import static com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D_DRIVER_TYPE.D3D_DRIVER_TYPE_WARP;
import com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D_FEATURE_LEVEL;
import static com.fourthskyinteractive.dx4j.d3d11.D3D11.D3D_FEATURE_LEVEL.D3D_FEATURE_LEVEL_10_0;
import com.fourthskyinteractive.dx4j.d3d11.core.D3D11_VIEWPORT;
import com.fourthskyinteractive.dx4j.d3d11.core.ID3D11Device;
import com.fourthskyinteractive.dx4j.d3d11.core.ID3D11DeviceContext;
import com.fourthskyinteractive.dx4j.d3d11.resources.ID3D11Texture2D;
import com.fourthskyinteractive.dx4j.d3d11.resources.views.ID3D11RenderTargetView;
import com.fourthskyinteractive.dx4j.dxgi.DXGI.DXGI_FORMAT;
import com.fourthskyinteractive.dx4j.dxgi.DXGI.DXGI_SWAP_CHAIN_FLAG;
import com.fourthskyinteractive.dx4j.dxgi.DXGI.DXGI_SWAP_EFFECT;
import com.fourthskyinteractive.dx4j.dxgi.DXGI.DXGI_USAGE;
import com.fourthskyinteractive.dx4j.dxgi.IDXGIFactory1;
import com.fourthskyinteractive.dx4j.dxgi.adapter.IDXGIAdapter;
import com.fourthskyinteractive.dx4j.dxgi.adapter.IDXGIAdapter1;
import com.fourthskyinteractive.dx4j.dxgi.adapter.IDXGIOutput;
import com.fourthskyinteractive.dx4j.dxgi.device.DXGI_SWAP_CHAIN_DESC;
import com.fourthskyinteractive.dx4j.dxgi.device.IDXGIDevice1;
import com.fourthskyinteractive.dx4j.dxgi.device.IDXGISwapChain;
import com.fourthskyinteractive.dx4j.windows.HMODULE;
import com.fourthskyinteractive.dx4j.windows.HWND;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HINSTANCE;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.WNDCLASSEX;
import com.sun.jna.platform.win32.WinUser.WindowProc;
import java.nio.IntBuffer;
import java.util.List;
import jmevr.input.OpenVR;
import jopenvr.JOpenVRLibrary;

/**
 *
 * @author Phr00t
 */
public class DirectVR implements WindowProc {
    
    // https://github.com/jherico/OculusRiftInAction/blob/562a2e7e465e31a385c47313d44b946ccaddf15d/examples/cpp/Example_X_Direct3dInterop.cpp
    
    private static DirectVR instance;
    private static ID3D11Texture2D backBuffer;
    
    private static ID3D11Device directVRDevice;
    
    /*
        tries to init DirectX 11 direct mode
    */
    public static boolean initDirectVR(int width, int height, long window) {
        // if we have no VRSystem, just return false
        if( OpenVR.getVRSystemInstance() == null ) return false;
        // first get the device ID for DirectX 11 from OpenVR
        IntBuffer pnAdapterIndex = IntBuffer.allocate(1);
        IntBuffer pnAdapterOutputIndex = IntBuffer.allocate(1);
        JOpenVRLibrary.VR_IVRSystem_GetDXGIOutputInfo(OpenVR.getVRSystemInstance(), pnAdapterIndex, pnAdapterOutputIndex);
        // if there was an error, quit now
        if( pnAdapterIndex.get(0) == -1 && pnAdapterOutputIndex.get(0) == -1 ) return false;
        // got Direct mode data! let's try using it...
        D3D_FEATURE_LEVEL featureLevel = D3D_FEATURE_LEVEL.D3D_FEATURE_LEVEL_11_1;        
        try {
            // create device
            directVRDevice = D3D11.CreateDevice(D3D_DRIVER_TYPE_WARP, 0, new D3D_FEATURE_LEVEL[] { featureLevel });
            final ID3D11DeviceContext immediateContext = directVRDevice.GetImmediateContext();

            IDXGIDevice1 DXGIDevice = directVRDevice.QueryInterface(IDXGIDevice1.class);
            IDXGIAdapter1 Adapter = DXGIDevice.GetParent(IDXGIAdapter1.class);
            IDXGIFactory1 DXGIFactory= Adapter.GetParent(IDXGIFactory1.class);

            // swap chain information, taken from jherico link at top
            DXGI_SWAP_CHAIN_DESC scDesc = new DXGI_SWAP_CHAIN_DESC();
            
            // create a window for us to use, or use window
            if( window == 0 ) {
                WinDef.HWND Window = createWindow();                
                User32.INSTANCE.ShowWindow(Window, User32.SW_SHOWMAXIMIZED);
                scDesc.OutputWindow(new HWND(Pointer.nativeValue(Window.getPointer())));
            } else {
                scDesc.OutputWindow(new HWND(window));                
            }
            
            scDesc.BufferUsage(DXGI_USAGE.DXGI_USAGE_RENDER_TARGET_OUTPUT);
            scDesc.SwapEffect(DXGI_SWAP_EFFECT.DXGI_SWAP_EFFECT_SEQUENTIAL);
            scDesc.BufferCount(2);
            scDesc.Flags((int)DXGI_SWAP_CHAIN_FLAG.DXGI_SWAP_CHAIN_FLAG_ALLOW_MODE_SWITCH.value());
            scDesc.Windowed(0);
            scDesc.BufferDesc().Format(DXGI_FORMAT.DXGI_FORMAT_R8G8B8A8_UNORM);
            scDesc.BufferDesc().Height(height);
            scDesc.BufferDesc().Width(width);
            scDesc.BufferDesc().RefreshRate().Denominator(1);
            scDesc.BufferDesc().RefreshRate().Numerator(0);
            scDesc.SampleDesc().Count(1);
            scDesc.SampleDesc().Quality(0);
            
            IDXGISwapChain swapChain = null;
            
            try {
                // this might fail if fullscreen fails...
                swapChain = DXGIFactory.CreateSwapChain(DXGIDevice, scDesc);
            } catch(Exception e) {
                System.out.println("SwapChain exception: " + e.toString());
            }

            if( swapChain == null ) {
                // try creating it non-fullscreen
                System.out.println("Can't DirectX fullscreen, fallback to windowed...");
                scDesc.Windowed(1);            
                swapChain = DXGIFactory.CreateSwapChain(DXGIDevice, scDesc);
            }
            
            backBuffer = swapChain.GetBuffer(0, ID3D11Texture2D.class);
            final ID3D11RenderTargetView rtView = directVRDevice.CreateRenderTargetView(backBuffer, null);
            
            // not sure what these do
            immediateContext.OMSetRenderTargets(rtView, null);
            immediateContext.RSSetViewports(new D3D11_VIEWPORT(width, height));
            
            // this gets the IDXIOuput of the virtual reality device
            IDXGIOutput useOut = DXGIFactory.EnumAdapters().get(pnAdapterIndex.get(0)).EnumOutputs().get(pnAdapterOutputIndex.get(0));
            
            if( scDesc.Windowed() == 0 ) {
                // try and set fullscreen mode
                swapChain.SetFullscreenState(1, useOut);
            }
            return true;
        } catch(Exception e) {
            System.out.println("DirectVR exception: " + e.toString());
            return false;
        }
    }
    
    private static WinDef.HWND createWindow() {
        WString windowClass = new WString("MyWindowClass");
        WinDef.HMODULE hInst = Kernel32.INSTANCE.GetModuleHandle("");

        WNDCLASSEX wClass = new WNDCLASSEX();
        wClass.hInstance = hInst;
        if( instance == null ) instance = new DirectVR();
        wClass.lpfnWndProc = instance;
        wClass.lpszClassName = windowClass;

        // register window class
        User32.INSTANCE.RegisterClassEx(wClass);

        // create new window
        return User32.INSTANCE
                        .CreateWindowEx(
                                        User32.WS_EX_TOPMOST,
                                        windowClass,
                                        "VR Output",
                                        User32.WS_POPUP | User32.WS_VISIBLE, 0, 0, 0, 0,
                                        null, // WM_DEVICECHANGE contradicts parent=WinUser.HWND_MESSAGE
                                        null, hInst, null);        
    }
    
    public static void destroyDirectVR() {
        if( directVRDevice != null ) {
            directVRDevice.Release();
        }
        if( backBuffer != null ) {
            backBuffer.Release();
        }
    }

    @Override
    public WinDef.LRESULT callback(WinDef.HWND hwnd, int uMsg, WinDef.WPARAM wParam, WinDef.LPARAM lParam) {
        switch (uMsg) {
        case WinUser.WM_CREATE: {
                //onCreate(wParam, lParam);
                return new LRESULT(0);
        }
        case WinUser.WM_DESTROY: {
                User32.INSTANCE.PostQuitMessage(0);
                return new LRESULT(0);
        }
        case WinUser.WM_SESSION_CHANGE: {
                //this.onSessionChange(wParam, lParam);
                return new LRESULT(0);
        }
        case WinUser.WM_DEVICECHANGE: {
                //LRESULT lResult = this.onDeviceChange(wParam, lParam);
                //return lResult != null ? lResult :
                        //User32.INSTANCE.DefWindowProc(hwnd, uMsg, wParam, lParam);
            return new LRESULT(0);
        }
        default:
                return User32.INSTANCE.DefWindowProc(hwnd, uMsg, wParam, lParam);
        }        
    }
    
}
