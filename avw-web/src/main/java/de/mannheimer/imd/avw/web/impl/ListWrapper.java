package de.mannheimer.imd.avw.web.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ListWrapper {

	List<RequestMappingMethod> methods = new ArrayList<RequestMappingMethod>();

	public List<RequestMappingMethod> getMethods() {

		return methods;
	}

	public void setMethods(List<RequestMappingMethod> methods) {

		this.methods = methods;
	}

}
