package de.mannheimer.imd.avw.impl.persistence.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@MappedSuperclass
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AbstractEntity {

	private String id = null;

	@Id
	@Column(name = "id")
	@XmlAttribute
	public String getId() {

		if (this.id == null) {
			this.id = UUID.randomUUID().toString();
		}
		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

}
