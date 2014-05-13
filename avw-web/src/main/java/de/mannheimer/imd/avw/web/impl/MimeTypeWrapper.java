package de.mannheimer.imd.avw.web.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import de.mannheimer.imd.avw.api.model.MimeType;
import de.mannheimer.imd.avw.impl.persistence.model.MimeTypeImpl;

@XmlRootElement(name = "mimetypes")
public class MimeTypeWrapper {

	Collection<MimeType> objects = new ArrayList<MimeType>();

	public MimeTypeWrapper(Collection<MimeType> objects) {

		super();
		this.objects = objects;
	}

	public MimeTypeWrapper() {

	}

	@XmlElement(name = "mimetype", type = MimeTypeImpl.class)
	public Collection<MimeType> getObjects() {

		return this.objects;
	}

	public void setObjects(Collection<MimeType> objects) {

		this.objects = objects;
	}

}
