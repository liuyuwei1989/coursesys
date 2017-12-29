package com.bwf.courses.utils;

/**
 * 用于同步锁定选课操作;
 * @author hasee
 *
 */
public class ChooseLock {
	private static ChooseLock updateLock = new ChooseLock();
	
	private ChooseLock(){
		
	}

	public static ChooseLock getUpdateLock() {
		return updateLock;
	}
}
