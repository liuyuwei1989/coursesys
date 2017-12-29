package com.bwf.courses.utils;

/**
 * 用于同步账户多处登录的操作;
 */
public class SessionAddLock {
	private static SessionAddLock sessionAddLock = new SessionAddLock();
	
	private SessionAddLock(){
		
	}

	public static SessionAddLock getSessionAddLock() {
		return sessionAddLock;
	}
}
