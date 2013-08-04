package oculusvr.input;

public class HMDInfo {
	protected int HResolution;
	protected int VResolution;
	protected float HScreenSize;
	protected float VScreenSize;
	protected float VScreenCenter;
	protected float EyeToScreenDistance;
	protected float LensSeparationDistance;
	protected float InterpupillaryDistance;
	protected float[] DistortionK = new float[4];
	protected int DesktopX;
	protected int DesktopY;
	protected String DisplayDeviceName = "";
	protected long DisplayId = 0;

    public int getHResolution() {
        return HResolution;
    }

    public void setHResolution(int HResolution) {
        this.HResolution = HResolution;
    }

    public int getVResolution() {
        return VResolution;
    }

    public void setVResolution(int VResolution) {
        this.VResolution = VResolution;
    }

    public float getHScreenSize() {
        return HScreenSize;
    }

    public void setHScreenSize(float HScreenSize) {
        this.HScreenSize = HScreenSize;
    }

    public float getVScreenCenter() {
        return VScreenCenter;
    }

    public void setVScreenCenter(float VScreenCenter) {
        this.VScreenCenter = VScreenCenter;
    }

    public float getEyeToScreenDistance() {
        return EyeToScreenDistance;
    }

    public void setEyeToScreenDistance(float EyeToScreenDistance) {
        this.EyeToScreenDistance = EyeToScreenDistance;
    }

    public float getInterpupillaryDistance() {
        return InterpupillaryDistance;
    }

    public void setInterpupillaryDistance(float InterpupillaryDistance) {
        this.InterpupillaryDistance = InterpupillaryDistance;
    }

    public float[] getDistortionK() {
        return DistortionK;
    }

    public void setDistortionK(float[] DistortionK) {
        this.DistortionK = DistortionK;
    }

    public int getDesktopX() {
        return DesktopX;
    }

    public void setDesktopX(int DesktopX) {
        this.DesktopX = DesktopX;
    }

    public int getDesktopY() {
        return DesktopY;
    }

    public void setDesktopY(int DesktopY) {
        this.DesktopY = DesktopY;
    }

    public String getDisplayDeviceName() {
        return DisplayDeviceName;
    }

    public void setDisplayDeviceName(String DisplayDeviceName) {
        this.DisplayDeviceName = DisplayDeviceName;
    }

    public long getDisplayId() {
        return DisplayId;
    }

    public void setDisplayId(long DisplayId) {
        this.DisplayId = DisplayId;
    }

    public float getVScreenSize() {
        return VScreenSize;
    }

    public void setVScreenSize(float VScreenSize) {
        this.VScreenSize = VScreenSize;
    }

    public float getLensSeparationDistance() {
        return LensSeparationDistance;
    }

    public void setLensSeparationDistance(float LensSeparationDistance) {
        this.LensSeparationDistance = LensSeparationDistance;
    }

    public void createFakeValues(){
        HScreenSize = 0.14976f;
	VScreenSize = 0.0935f;
//	VScreenCenter;
	EyeToScreenDistance = 0.041f;
	LensSeparationDistance= 0.0624f;
	InterpupillaryDistance= 0.0624f;
	DistortionK = new float[]{1f, 0.22f, 0.24f, 0f};
        HResolution = 1280;
	VResolution = 800;
    }
    	
    @Override
    public String toString() {
            return "HMDInfo [HResolution = " + HResolution + 
                    ", VResolution = " + VResolution + 
                    ", HScreenSize = " + HScreenSize + 
                    ", VScreenSize = " + VScreenSize + 
                    ", VScreenCenter = " + VScreenCenter + 
                    ", EyeToScreenDistance = " + EyeToScreenDistance +
                    ", LensSeparationDistance = " + LensSeparationDistance + 
                    ", InterpupillaryDistance = " + InterpupillaryDistance + 
                    ", DistortionK = [" + DistortionK[0] + ", " + DistortionK[1] + ", " + DistortionK[2] + ", " + DistortionK[3] + "] " + 
                    ", DesktopX = " + DesktopX + 
                    ", DesktopY = " + DesktopY + 
                    ", DisplayDeviceName = " + DisplayDeviceName + 
                    ", DisplayId = " + DisplayId + "]";
    }
}
