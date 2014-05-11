package de.mannheimer.imd.avw.web.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import de.mannheimer.imd.avw.impl.persistence.model.OrderImpl;

@XmlRootElement(name = "elements")
@XmlSeeAlso(OrderImpl.class)
public class AbstractListWrapper<I> {

	List<I> objects = new ArrayList<I>();

	@XmlElement(name = "elements")
	public List<I> getObjects() {

		return objects;
	}

	public void setObjects(List<I> objects) {

		this.objects = objects;
	}

}
