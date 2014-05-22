package de.mannheimer.imd.avw.impl.persistence.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import de.mannheimer.imd.avw.api.commons.Assert;
import de.mannheimer.imd.avw.api.model.Document;
import de.mannheimer.imd.avw.api.model.DocumentContainer;
import de.mannheimer.imd.avw.api.model.MimeType;

@Entity
@Table(name = "documents")
@Access(AccessType.PROPERTY)
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class DocumentImpl extends HistoryImpl implements Document {

	private int version = -1;
	private DocumentContainer container;
	MimeType mimeType;

	public DocumentImpl(MimeType mimetype) {

		super();
		this.mimeType = mimetype;
	}

	public DocumentImpl() {

	}

	@Override
	@ManyToOne(optional = true, cascade = { CascadeType.ALL }, targetEntity = DocumentContainerImpl.class)
	@JoinColumn(name = "container_id")
	@XmlElement(name = "container", type = DocumentContainerImpl.class)
	public DocumentContainer getContainer() {

		return container;
	}

	@Override
	@Column(name = "version")
	public int getVersion() {

		return version;
	}

	@Override
	public void setContainer(DocumentContainer container) {

		Assert.notNull(container);
		Assert.notEmpty("Container name is emtpty", container.getName());
		this.container = container;
	}

	public void setVersion(int version) {

		this.version = version;
	}

	@Override
	@OneToOne(cascade = { CascadeType.ALL }, optional = false, orphanRemoval = true, targetEntity = MimeTypeImpl.class)
	@JoinColumn(name = "mimetype_id")
	@XmlElement(name = "mimeType", type = MimeTypeImpl.class)
	public MimeType getMimeType() {

		return this.mimeType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.mannheimer.imd.avw.api.model.Document#setMimeType(de.mannheimer.imd
	 * .avw.api.model.MimeType)
	 */
	@Override
	public void setMimeType(MimeType mimetype) {

		this.mimeType = mimetype;
	}

	@Override
	public String toString() {

		StringBuffer buf = new StringBuffer();
		buf.append(getClass().getSimpleName());
		buf.append("[");
		buf.append("id=" + getId());
		if (getMimeType() != null) {
			buf.append(", mimetype=" + getMimeType().getMimeType());
		}
		buf.append(", version=" + getVersion());
		if (getContainer() != null) {
			buf.append(", containerId=" + getContainer().getId());
		}
		buf.append("]");
		return buf.toString();
	}

}
