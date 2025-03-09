package com.point.byon.exception;

public class NotEnoughPointsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 생성자정의
	 */
	public NotEnoughPointsException() {
		super();
	}
	
	/**
	 * 생성자정의
	 * @param message 상황에 따른 예외 메세지
	 */
	public NotEnoughPointsException(String message) {
		super(message);
	}
}
