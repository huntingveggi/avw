package de.mannheimer.imd.avw.impl.persistence.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import de.mannheimer.imd.avw.api.MimeTypes;
import de.mannheimer.imd.avw.api.model.Document;
import de.mannheimer.imd.avw.api.model.DocumentContainer;

@Entity
@Table(name = "documents")
@Access(AccessType.PROPERTY)
public class DocumentImpl extends HistoryImpl implements Document {

	private int version = -1;
	private DocumentContainer container;
	String mimeType = "";

	public DocumentImpl(MimeTypes mimetype) {

		super();
		this.mimeType = mimetype.toString();
	}

	public DocumentImpl() {

	}

	@Override
	@ManyToOne(optional = true, cascade = { CascadeType.ALL }, targetEntity = DocumentContainerImpl.class)
	@JoinColumn(name = "container_id")
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

		this.container = container;
	}

	public void setVersion(int version) {

		this.version = version;
	}

	@Override
	@Column(name = "mimetype")
	public String getMimeType() {

		return this.mimeType;
	}

	public void setMimeType(String mimetype) {

		this.mimeType = mimetype;
	}

	@Override
	public String toString() {

		StringBuffer buf = new StringBuffer();
		buf.append(getClass().getSimpleName());
		buf.append("[");
		buf.append("id=" + getId());
		buf.append(", mimetype=" + getMimeType());
		buf.append(", version=" + getVersion());
		if (getContainer() != null) {
			buf.append(", containerId=" + getContainer().getId());
		}
		buf.append("]");
		return buf.toString();
	}

}
