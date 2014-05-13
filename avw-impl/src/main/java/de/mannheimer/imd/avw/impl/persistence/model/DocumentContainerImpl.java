package de.mannheimer.imd.avw.impl.persistence.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import de.mannheimer.imd.avw.api.model.DocumentContainer;

@Entity
@Table(name = "containers")
@Access(AccessType.PROPERTY)
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class DocumentContainerImpl extends HistoryImpl implements
		DocumentContainer {

	private String name = "";

	@Override
	@Column(name = "name", nullable = false, unique = true)
	@XmlAttribute
	public String getName() {

		return this.name;
	}

	public void setName(String name) {

		this.name = name;
	}

}
