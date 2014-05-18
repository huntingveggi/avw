package de.mannheimer.imd.avw.web.impl;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.ui.ModelMap;

@XmlRootElement(name = "response")
public class ResponseMessage {

	int statusCode = 0;
	String message = "Ok";
	Object model;

	public ResponseMessage() {

	}

	public void build(ModelMap map) {

		build("response", map);
	}

	public void build(String modelName, ModelMap map) {

		map.addAttribute(modelName, this);
	}

	public ResponseMessage(int statusCode, String message, Object responseModel) {

		super();
		this.statusCode = statusCode;
		this.message = message;
		this.model = responseModel;
	}

	public ResponseMessage(Object model) {

		setModel(model);
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

		return model;
	}

	public void setModel(Object responseModel) {

		this.model = responseModel;
	}

}
