package jopenvr;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;
/**
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class VRControllerState_t extends Structure {
	public int unPacketNum;
	public NativeLong ulButtonPressed;
	public NativeLong ulButtonTouched;
	/**
	 * VRControllerAxis_t[5]<br>
	 * C type : VRControllerAxis_t[5]
	 */
	public VRControllerAxis_t[] rAxis = new VRControllerAxis_t[5];
	public VRControllerState_t() {
		super();
	}
	protected List<? > getFieldOrder() {
		return Arrays.asList("unPacketNum", "ulButtonPressed", "ulButtonTouched", "rAxis");
	}
	/**
	 * @param rAxis VRControllerAxis_t[5]<br>
	 * C type : VRControllerAxis_t[5]
	 */
	public VRControllerState_t(int unPacketNum, NativeLong ulButtonPressed, NativeLong ulButtonTouched, VRControllerAxis_t rAxis[]) {
		super();
		this.unPacketNum = unPacketNum;
		this.ulButtonPressed = ulButtonPressed;
		this.ulButtonTouched = ulButtonTouched;
		if ((rAxis.length != this.rAxis.length)) 
			throw new IllegalArgumentException("Wrong array size !");
		this.rAxis = rAxis;
	}
	public VRControllerState_t(Pointer peer) {
		super(peer);
	}
	public static class ByReference extends VRControllerState_t implements Structure.ByReference {
		
	};
	public static class ByValue extends VRControllerState_t implements Structure.ByValue {
		
	};
}
