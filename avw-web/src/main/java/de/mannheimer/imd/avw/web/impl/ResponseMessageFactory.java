package de.mannheimer.imd.avw.web.impl;

public class ResponseMessageFactory {

	public static ResponseMessage createResponseMessage(int status,
			String message, Object model) {

		return new ResponseMessage(status, message, model);
	}

	public static ResponseMessage createResponseMessage(Object model) {

		return new ResponseMessage(0, "Ok", model);
	}

	public static ResponseMessage createResponseMessage(String message,
			Object model) {

		return new ResponseMessage(0, message, model);
	}

}
