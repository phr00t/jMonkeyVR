package com.fourthskyinteractive.dx4j.d3dx10;

import org.bridj.BridJ;
import org.bridj.CRuntime;
import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;

import com.fourthskyinteractive.dx4j.d3d11.core.D3D11_VIEWPORT;

@Library("d3dx10_43")
@Runtime(CRuntime.class)
public class D3DX10 {
	static {
		BridJ.register();
	}

	/**
	 * Vector methods
	 */	
	public static native Pointer<D3DXVECTOR2 > D3DXVec2Normalize(Pointer<D3DXVECTOR2 > pOut, Pointer<D3DXVECTOR2 > pV);
	public static native Pointer<D3DXVECTOR2 > D3DXVec2Hermite(Pointer<D3DXVECTOR2 > pOut, Pointer<D3DXVECTOR2 > pV1, Pointer<D3DXVECTOR2 > pT1, Pointer<D3DXVECTOR2 > pV2, Pointer<D3DXVECTOR2 > pT2, float s);
	public static native Pointer<D3DXVECTOR2 > D3DXVec2CatmullRom(Pointer<D3DXVECTOR2 > pOut, Pointer<D3DXVECTOR2 > pV0, Pointer<D3DXVECTOR2 > pV1, Pointer<D3DXVECTOR2 > pV2, Pointer<D3DXVECTOR2 > pV3, float s);
	public static native Pointer<D3DXVECTOR2 > D3DXVec2BaryCentric(Pointer<D3DXVECTOR2 > pOut, Pointer<D3DXVECTOR2 > pV1, Pointer<D3DXVECTOR2 > pV2, Pointer<D3DXVECTOR2 > pV3, float f, float g);
	public static native Pointer<D3DXVECTOR4 > D3DXVec2Transform(Pointer<D3DXVECTOR4 > pOut, Pointer<D3DXVECTOR2 > pV, Pointer<D3DXMATRIX > pM);
	public static native Pointer<D3DXVECTOR2 > D3DXVec2TransformCoord(Pointer<D3DXVECTOR2 > pOut, Pointer<D3DXVECTOR2 > pV, Pointer<D3DXMATRIX > pM);
	public static native Pointer<D3DXVECTOR2 > D3DXVec2TransformNormal(Pointer<D3DXVECTOR2 > pOut, Pointer<D3DXVECTOR2 > pV, Pointer<D3DXMATRIX > pM);
	public static native Pointer<D3DXVECTOR4 > D3DXVec2TransformArray(Pointer<D3DXVECTOR4 > pOut, int OutStride, Pointer<D3DXVECTOR2 > pV, int VStride, Pointer<D3DXMATRIX > pM, int n);
	public static native Pointer<D3DXVECTOR2 > D3DXVec2TransformCoordArray(Pointer<D3DXVECTOR2 > pOut, int OutStride, Pointer<D3DXVECTOR2 > pV, int VStride, Pointer<D3DXMATRIX > pM, int n);
	public static native Pointer<D3DXVECTOR2 > D3DXVec2TransformNormalArray(Pointer<D3DXVECTOR2 > pOut, int OutStride, Pointer<D3DXVECTOR2 > pV, int VStride, Pointer<D3DXMATRIX > pM, int n);
	public static native Pointer<D3DXVECTOR3 > D3DXVec3Normalize(Pointer<D3DXVECTOR3 > pOut, Pointer<D3DXVECTOR3 > pV);
	public static native Pointer<D3DXVECTOR3 > D3DXVec3Hermite(Pointer<D3DXVECTOR3 > pOut, Pointer<D3DXVECTOR3 > pV1, Pointer<D3DXVECTOR3 > pT1, Pointer<D3DXVECTOR3 > pV2, Pointer<D3DXVECTOR3 > pT2, float s);
	public static native Pointer<D3DXVECTOR3 > D3DXVec3CatmullRom(Pointer<D3DXVECTOR3 > pOut, Pointer<D3DXVECTOR3 > pV0, Pointer<D3DXVECTOR3 > pV1, Pointer<D3DXVECTOR3 > pV2, Pointer<D3DXVECTOR3 > pV3, float s);
	public static native Pointer<D3DXVECTOR3 > D3DXVec3BaryCentric(Pointer<D3DXVECTOR3 > pOut, Pointer<D3DXVECTOR3 > pV1, Pointer<D3DXVECTOR3 > pV2, Pointer<D3DXVECTOR3 > pV3, float f, float g);
	public static native Pointer<D3DXVECTOR4 > D3DXVec3Transform(Pointer<D3DXVECTOR4 > pOut, Pointer<D3DXVECTOR3 > pV, Pointer<D3DXMATRIX > pM);
	public static native Pointer<D3DXVECTOR3 > D3DXVec3TransformCoord(Pointer<D3DXVECTOR3 > pOut, Pointer<D3DXVECTOR3 > pV, Pointer<D3DXMATRIX > pM);
	public static native Pointer<D3DXVECTOR3 > D3DXVec3TransformNormal(Pointer<D3DXVECTOR3 > pOut, Pointer<D3DXVECTOR3 > pV, Pointer<D3DXMATRIX > pM);
	public static native Pointer<D3DXVECTOR4 > D3DXVec3TransformArray(Pointer<D3DXVECTOR4 > pOut, int OutStride, Pointer<D3DXVECTOR3 > pV, int VStride, Pointer<D3DXMATRIX > pM, int n);
	public static native Pointer<D3DXVECTOR3 > D3DXVec3TransformCoordArray(Pointer<D3DXVECTOR3 > pOut, int OutStride, Pointer<D3DXVECTOR3 > pV, int VStride, Pointer<D3DXMATRIX > pM, int n);
	public static native Pointer<D3DXVECTOR3 > D3DXVec3TransformNormalArray(Pointer<D3DXVECTOR3 > pOut, int OutStride, Pointer<D3DXVECTOR3 > pV, int VStride, Pointer<D3DXMATRIX > pM, int n);
	public static native Pointer<D3DXVECTOR3 > D3DXVec3Project(Pointer<D3DXVECTOR3 > pOut, Pointer<D3DXVECTOR3 > pV, Pointer<D3D11_VIEWPORT > pViewport, Pointer<D3DXMATRIX > pProjection, Pointer<D3DXMATRIX > pView, Pointer<D3DXMATRIX > pWorld);
	public static native Pointer<D3DXVECTOR3 > D3DXVec3Unproject(Pointer<D3DXVECTOR3 > pOut, Pointer<D3DXVECTOR3 > pV, Pointer<D3D11_VIEWPORT > pViewport, Pointer<D3DXMATRIX > pProjection, Pointer<D3DXMATRIX > pView, Pointer<D3DXMATRIX > pWorld);
	public static native Pointer<D3DXVECTOR3 > D3DXVec3ProjectArray(Pointer<D3DXVECTOR3 > pOut, int OutStride, Pointer<D3DXVECTOR3 > pV, int VStride, Pointer<D3D11_VIEWPORT > pViewport, Pointer<D3DXMATRIX > pProjection, Pointer<D3DXMATRIX > pView, Pointer<D3DXMATRIX > pWorld, int n);
	public static native Pointer<D3DXVECTOR3 > D3DXVec3UnprojectArray(Pointer<D3DXVECTOR3 > pOut, int OutStride, Pointer<D3DXVECTOR3 > pV, int VStride, Pointer<D3D11_VIEWPORT > pViewport, Pointer<D3DXMATRIX > pProjection, Pointer<D3DXMATRIX > pView, Pointer<D3DXMATRIX > pWorld, int n);
	public static native Pointer<D3DXVECTOR4 > D3DXVec4Cross(Pointer<D3DXVECTOR4 > pOut, Pointer<D3DXVECTOR4 > pV1, Pointer<D3DXVECTOR4 > pV2, Pointer<D3DXVECTOR4 > pV3);
	public static native Pointer<D3DXVECTOR4 > D3DXVec4Normalize(Pointer<D3DXVECTOR4 > pOut, Pointer<D3DXVECTOR4 > pV);
	public static native Pointer<D3DXVECTOR4 > D3DXVec4Hermite(Pointer<D3DXVECTOR4 > pOut, Pointer<D3DXVECTOR4 > pV1, Pointer<D3DXVECTOR4 > pT1, Pointer<D3DXVECTOR4 > pV2, Pointer<D3DXVECTOR4 > pT2, float s);
	public static native Pointer<D3DXVECTOR4 > D3DXVec4CatmullRom(Pointer<D3DXVECTOR4 > pOut, Pointer<D3DXVECTOR4 > pV0, Pointer<D3DXVECTOR4 > pV1, Pointer<D3DXVECTOR4 > pV2, Pointer<D3DXVECTOR4 > pV3, float s);
	public static native Pointer<D3DXVECTOR4 > D3DXVec4BaryCentric(Pointer<D3DXVECTOR4 > pOut, Pointer<D3DXVECTOR4 > pV1, Pointer<D3DXVECTOR4 > pV2, Pointer<D3DXVECTOR4 > pV3, float f, float g);
	public static native Pointer<D3DXVECTOR4 > D3DXVec4Transform(Pointer<D3DXVECTOR4 > pOut, Pointer<D3DXVECTOR4 > pV, Pointer<D3DXMATRIX > pM);
	public static native Pointer<D3DXVECTOR4 > D3DXVec4TransformArray(Pointer<D3DXVECTOR4 > pOut, int OutStride, Pointer<D3DXVECTOR4 > pV, int VStride, Pointer<D3DXMATRIX > pM, int n);
	
	/**
	 * Matrix methods
	 */
	public static final Pointer<D3DXMATRIX> D3DXMatrixIdentity(Pointer<D3DXMATRIX> pOut) {
		pOut.get()._11(1.0f)._12(0.0f)._13(0.0f)._14(0.0f)
				  ._21(0.0f)._22(1.0f)._23(0.0f)._24(0.0f)
				  ._31(0.0f)._32(0.0f)._33(1.0f)._34(0.0f)
				  ._41(0.0f)._42(0.0f)._43(0.0f)._44(1.0f);
		
		return pOut;
	}
	
	public static final native float D3DXMatrixDeterminant(Pointer<D3DXMATRIX > pM);
	public static final native int D3DXMatrixDecompose(Pointer<D3DXVECTOR3 > pOutScale, Pointer<D3DXQUATERNION > pOutRotation, Pointer<D3DXVECTOR3 > pOutTranslation, Pointer<D3DXMATRIX > pM);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixTranspose(Pointer<D3DXMATRIX > pOut, Pointer<D3DXMATRIX > pM);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixMultiply(Pointer<D3DXMATRIX > pOut, Pointer<D3DXMATRIX > pM1, Pointer<D3DXMATRIX > pM2);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixMultiplyTranspose(Pointer<D3DXMATRIX > pOut, Pointer<D3DXMATRIX > pM1, Pointer<D3DXMATRIX > pM2);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixInverse(Pointer<D3DXMATRIX > pOut, Pointer<Float > pDeterminant, Pointer<D3DXMATRIX > pM);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixScaling(Pointer<D3DXMATRIX > pOut, float sx, float sy, float sz);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixTranslation(Pointer<D3DXMATRIX > pOut, float x, float y, float z);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixRotationX(Pointer<D3DXMATRIX > pOut, float Angle);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixRotationY(Pointer<D3DXMATRIX > pOut, float Angle);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixRotationZ(Pointer<D3DXMATRIX > pOut, float Angle);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixRotationAxis(Pointer<D3DXMATRIX > pOut, Pointer<D3DXVECTOR3 > pV, float Angle);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixRotationQuaternion(Pointer<D3DXMATRIX > pOut, Pointer<D3DXQUATERNION > pQ);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixRotationYawPitchRoll(Pointer<D3DXMATRIX > pOut, float Yaw, float Pitch, float Roll);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixTransformation(Pointer<D3DXMATRIX > pOut, Pointer<D3DXVECTOR3 > pScalingCenter, Pointer<D3DXQUATERNION > pScalingRotation, Pointer<D3DXVECTOR3 > pScaling, Pointer<D3DXVECTOR3 > pRotationCenter, Pointer<D3DXQUATERNION > pRotation, Pointer<D3DXVECTOR3 > pTranslation);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixTransformation2D(Pointer<D3DXMATRIX > pOut, Pointer<D3DXVECTOR2 > pScalingCenter, float ScalingRotation, Pointer<D3DXVECTOR2 > pScaling, Pointer<D3DXVECTOR2 > pRotationCenter, float Rotation, Pointer<D3DXVECTOR2 > pTranslation);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixAffineTransformation(Pointer<D3DXMATRIX > pOut, float Scaling, Pointer<D3DXVECTOR3 > pRotationCenter, Pointer<D3DXQUATERNION > pRotation, Pointer<D3DXVECTOR3 > pTranslation);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixAffineTransformation2D(Pointer<D3DXMATRIX > pOut, float Scaling, Pointer<D3DXVECTOR2 > pRotationCenter, float Rotation, Pointer<D3DXVECTOR2 > pTranslation);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixLookAtRH(Pointer<D3DXMATRIX > pOut, Pointer<D3DXVECTOR3 > pEye, Pointer<D3DXVECTOR3 > pAt, Pointer<D3DXVECTOR3 > pUp);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixLookAtLH(Pointer<D3DXMATRIX > pOut, Pointer<D3DXVECTOR3 > pEye, Pointer<D3DXVECTOR3 > pAt, Pointer<D3DXVECTOR3 > pUp);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixPerspectiveRH(Pointer<D3DXMATRIX > pOut, float w, float h, float zn, float zf);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixPerspectiveLH(Pointer<D3DXMATRIX > pOut, float w, float h, float zn, float zf);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixPerspectiveFovRH(Pointer<D3DXMATRIX > pOut, float fovy, float Aspect, float zn, float zf);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixPerspectiveFovLH(Pointer<D3DXMATRIX > pOut, float fovy, float Aspect, float zn, float zf);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixPerspectiveOffCenterRH(Pointer<D3DXMATRIX > pOut, float l, float r, float b, float t, float zn, float zf);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixPerspectiveOffCenterLH(Pointer<D3DXMATRIX > pOut, float l, float r, float b, float t, float zn, float zf);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixOrthoRH(Pointer<D3DXMATRIX > pOut, float w, float h, float zn, float zf);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixOrthoLH(Pointer<D3DXMATRIX > pOut, float w, float h, float zn, float zf);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixOrthoOffCenterRH(Pointer<D3DXMATRIX > pOut, float l, float r, float b, float t, float zn, float zf);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixOrthoOffCenterLH(Pointer<D3DXMATRIX > pOut, float l, float r, float b, float t, float zn, float zf);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixShadow(Pointer<D3DXMATRIX > pOut, Pointer<D3DXVECTOR4 > pLight, Pointer<D3DXPLANE > pPlane);
	public static final native Pointer<D3DXMATRIX > D3DXMatrixReflect(Pointer<D3DXMATRIX > pOut, Pointer<D3DXPLANE > pPlane);
	
	/**
	 * Quaternion methods
	 */
	public static final Pointer<D3DXQUATERNION> D3DXQuaternionIdentity(Pointer<D3DXQUATERNION> pOut ) {
		pOut.get().x(0.0f).y(0.0f).z(0.0f).w(1.0f);
		return pOut;
	}
	
	public static final float D3DXQuaternionLength(Pointer<D3DXQUATERNION > pQ) {
		D3DXQUATERNION q = pQ.get();
		return (float) Math.sqrt(q.x() * q.x() + q.y() * q.y() + q.z() * q.z() + q.w() * q.w());
	}
	
	public static final float D3DXQuaternionLengthSq(Pointer<D3DXQUATERNION > pQ) {
//		if(pQ == null)
//			return 0.0f;
		
		D3DXQUATERNION q = pQ.get();
		return q.x() * q.x() + q.y() * q.y() + q.z() * q.z() + q.w() * q.w();
	}
	
	public static final float D3DXQuaternionDot(Pointer<D3DXQUATERNION > pQ1, Pointer<D3DXQUATERNION > pQ2)	{
//		if(pQ1 == null || pQ2 == null)
//			return 0.0f;
		
		D3DXQUATERNION q1 = pQ1.get();
		D3DXQUATERNION q2 = pQ2.get();
		return q1.x() * q2.x() + q1.y() * q2.y() + q1.z() * q2.z() + q1.w() * q2.w();
	}
	
	public static final Pointer<D3DXQUATERNION > D3DXQuaternionConjugate(Pointer<D3DXQUATERNION > pOut, Pointer<D3DXQUATERNION > pQ) {
//		if(pOut == null || pQ == null)
//			return null;
		
		D3DXQUATERNION q = pQ.get();
		D3DXQUATERNION qO = pOut.get();
		qO.x(-q.x());
		qO.y(-q.y());
		qO.z(-q.z());
		qO.w(-q.w());
		return pOut;
	}
	
	public static final native void D3DXQuaternionToAxisAngle(Pointer<D3DXQUATERNION > pQ, Pointer<D3DXVECTOR3 > pAxis, Pointer<Float > pAngle);
	public static final native Pointer<D3DXQUATERNION > D3DXQuaternionRotationMatrix(Pointer<D3DXQUATERNION > pOut, Pointer<D3DXMATRIX > pM);
	public static final native Pointer<D3DXQUATERNION > D3DXQuaternionRotationAxis(Pointer<D3DXQUATERNION > pOut, Pointer<D3DXVECTOR3 > pV, float Angle);
	public static final native Pointer<D3DXQUATERNION > D3DXQuaternionRotationYawPitchRoll(Pointer<D3DXQUATERNION > pOut, float Yaw, float Pitch, float Roll);
	public static final native Pointer<D3DXQUATERNION > D3DXQuaternionMultiply(Pointer<D3DXQUATERNION > pOut, Pointer<D3DXQUATERNION > pQ1, Pointer<D3DXQUATERNION > pQ2);
	public static final native Pointer<D3DXQUATERNION > D3DXQuaternionNormalize(Pointer<D3DXQUATERNION > pOut, Pointer<D3DXQUATERNION > pQ);
	public static final native Pointer<D3DXQUATERNION > D3DXQuaternionInverse(Pointer<D3DXQUATERNION > pOut, Pointer<D3DXQUATERNION > pQ);
	public static final native Pointer<D3DXQUATERNION > D3DXQuaternionLn(Pointer<D3DXQUATERNION > pOut, Pointer<D3DXQUATERNION > pQ);
	public static final native Pointer<D3DXQUATERNION > D3DXQuaternionExp(Pointer<D3DXQUATERNION > pOut, Pointer<D3DXQUATERNION > pQ);
	public static final native Pointer<D3DXQUATERNION > D3DXQuaternionSlerp(Pointer<D3DXQUATERNION > pOut, Pointer<D3DXQUATERNION > pQ1, Pointer<D3DXQUATERNION > pQ2, float t);
	public static final native Pointer<D3DXQUATERNION > D3DXQuaternionSquad(Pointer<D3DXQUATERNION > pOut, Pointer<D3DXQUATERNION > pQ1, Pointer<D3DXQUATERNION > pA, Pointer<D3DXQUATERNION > pB, Pointer<D3DXQUATERNION > pC, float t);
	public static final native void D3DXQuaternionSquadSetup(Pointer<D3DXQUATERNION > pAOut, Pointer<D3DXQUATERNION > pBOut, Pointer<D3DXQUATERNION > pCOut, Pointer<D3DXQUATERNION > pQ0, Pointer<D3DXQUATERNION > pQ1, Pointer<D3DXQUATERNION > pQ2, Pointer<D3DXQUATERNION > pQ3);
	public static final native Pointer<D3DXQUATERNION > D3DXQuaternionBaryCentric(Pointer<D3DXQUATERNION > pOut, Pointer<D3DXQUATERNION > pQ1, Pointer<D3DXQUATERNION > pQ2, Pointer<D3DXQUATERNION > pQ3, float f, float g);
	
}
