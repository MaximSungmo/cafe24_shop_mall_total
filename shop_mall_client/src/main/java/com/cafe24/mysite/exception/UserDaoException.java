package com.cafe24.mysite.exception;

public class UserDaoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserDaoException(){
		super("UserDaoException Occurs");
	}

	public UserDaoException(String message){
		super(message);
	}

}
