package de.mannheimer.imd.avw.web.impl;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class ResponseMessage {

	int statusCode = 0;
	String message = "Ok";
	Object responseModel;

	public ResponseMessage() {

	}

	public ResponseMessage(int statusCode, String message, Object responseModel) {

		super();
		this.statusCode = statusCode;
		this.message = message;
		this.responseModel = responseModel;
	}

	@XmlAttribute
	public int getStatusCode() {

		return statusCode;
	}

	public void setStatusCode(int statusCode) {

		this.statusCode = statusCode;
	}

	@XmlAttribute
	public String getMessage() {

		return message;
	}

	public void setMessage(String message) {

		this.message = message;
	}

	@XmlElement(name = "model")
	public Object getModel() {

		return responseModel;
	}

	public void setModel(Object responseModel) {

		this.responseModel = responseModel;
	}

}