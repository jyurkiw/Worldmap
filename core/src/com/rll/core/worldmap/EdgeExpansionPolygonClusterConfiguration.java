package com.rll.core.worldmap;

/**
 * Configuration object for an EdgeExpansionPolygonClusterWorldMap object.
 * @author jyurkiw
 * @version 1.0
 */
public class EdgeExpansionPolygonClusterConfiguration {
	private int PRE_HULL_POINT_COUNT = 36 * 2;
	private int NUM_POLYS = 50;
	private int MIN_DIST_FROM_MID = 50, MAX_DIST_FROM_MID = 200;
	private int MAX_DIST_FROM_MID2 = MAX_DIST_FROM_MID * MAX_DIST_FROM_MID;
	private int MAX_EX_ATTEMPTS = 100, MAX_PT_ATTEMPTS = 100;
	
	private float INSIDE_NOISE_OFFSET = 1f, PARTIAL_NOISE_OFFSET = 0f, OUTSIDE_NOISE_OFFSET = -1000f;

	/**
	 * @return the pRE_HULL_POINT_COUNT
	 */
	public int getPRE_HULL_POINT_COUNT() {
		return PRE_HULL_POINT_COUNT;
	}

	/**
	 * @param pRE_HULL_POINT_COUNT the pRE_HULL_POINT_COUNT to set
	 */
	public void setPRE_HULL_POINT_COUNT(int pRE_HULL_POINT_COUNT) {
		PRE_HULL_POINT_COUNT = pRE_HULL_POINT_COUNT;
	}

	/**
	 * @return the nUM_POLYS
	 */
	public int getNUM_POLYS() {
		return NUM_POLYS;
	}

	/**
	 * @param nUM_POLYS the nUM_POLYS to set
	 */
	public void setNUM_POLYS(int nUM_POLYS) {
		NUM_POLYS = nUM_POLYS;
	}

	/**
	 * @return the mIN_DIST_FROM_MID
	 */
	public int getMIN_DIST_FROM_MID() {
		return MIN_DIST_FROM_MID;
	}

	/**
	 * @param mIN_DIST_FROM_MID the mIN_DIST_FROM_MID to set
	 */
	public void setMIN_DIST_FROM_MID(int mIN_DIST_FROM_MID) {
		MIN_DIST_FROM_MID = mIN_DIST_FROM_MID;
	}

	/**
	 * @return the mAX_DIST_FROM_MID
	 */
	public int getMAX_DIST_FROM_MID() {
		return MAX_DIST_FROM_MID;
	}

	/**
	 * @param mAX_DIST_FROM_MID the mAX_DIST_FROM_MID to set
	 */
	public void setMAX_DIST_FROM_MID(int mAX_DIST_FROM_MID) {
		MAX_DIST_FROM_MID = mAX_DIST_FROM_MID;
	}

	/**
	 * @return the mAX_DIST_FROM_MID2
	 */
	public int getMAX_DIST_FROM_MID2() {
		return MAX_DIST_FROM_MID2;
	}

	/**
	 * @param mAX_DIST_FROM_MID2 the mAX_DIST_FROM_MID2 to set
	 */
	public void setMAX_DIST_FROM_MID2(int mAX_DIST_FROM_MID2) {
		MAX_DIST_FROM_MID2 = mAX_DIST_FROM_MID2;
	}

	/**
	 * @return the mAX_EX_ATTEMPTS
	 */
	public int getMAX_EX_ATTEMPTS() {
		return MAX_EX_ATTEMPTS;
	}

	/**
	 * @param mAX_EX_ATTEMPTS the mAX_EX_ATTEMPTS to set
	 */
	public void setMAX_EX_ATTEMPTS(int mAX_EX_ATTEMPTS) {
		MAX_EX_ATTEMPTS = mAX_EX_ATTEMPTS;
	}

	/**
	 * @return the mAX_PT_ATTEMPTS
	 */
	public int getMAX_PT_ATTEMPTS() {
		return MAX_PT_ATTEMPTS;
	}

	/**
	 * @param mAX_PT_ATTEMPTS the mAX_PT_ATTEMPTS to set
	 */
	public void setMAX_PT_ATTEMPTS(int mAX_PT_ATTEMPTS) {
		MAX_PT_ATTEMPTS = mAX_PT_ATTEMPTS;
	}

	/**
	 * @return the iNSIDE_NOISE_OFFSET
	 */
	public float getINSIDE_NOISE_OFFSET() {
		return INSIDE_NOISE_OFFSET;
	}

	/**
	 * @param iNSIDE_NOISE_OFFSET the iNSIDE_NOISE_OFFSET to set
	 */
	public void setINSIDE_NOISE_OFFSET(float iNSIDE_NOISE_OFFSET) {
		INSIDE_NOISE_OFFSET = iNSIDE_NOISE_OFFSET;
	}

	/**
	 * @return the pARTIAL_NOISE_OFFSET
	 */
	public float getPARTIAL_NOISE_OFFSET() {
		return PARTIAL_NOISE_OFFSET;
	}

	/**
	 * @param pARTIAL_NOISE_OFFSET the pARTIAL_NOISE_OFFSET to set
	 */
	public void setPARTIAL_NOISE_OFFSET(float pARTIAL_NOISE_OFFSET) {
		PARTIAL_NOISE_OFFSET = pARTIAL_NOISE_OFFSET;
	}

	/**
	 * @return the oUTSIDE_NOISE_OFFSET
	 */
	public float getOUTSIDE_NOISE_OFFSET() {
		return OUTSIDE_NOISE_OFFSET;
	}

	/**
	 * @param oUTSIDE_NOISE_OFFSET the oUTSIDE_NOISE_OFFSET to set
	 */
	public void setOUTSIDE_NOISE_OFFSET(float oUTSIDE_NOISE_OFFSET) {
		OUTSIDE_NOISE_OFFSET = oUTSIDE_NOISE_OFFSET;
	}
}
