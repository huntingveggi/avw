package de.mannheimer.imd.avw.impl.persistence.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

import de.mannheimer.imd.avw.api.model.MimeType;

/**
 * @author Dennis Ahaus
 * 
 */
@Entity
@Table(name = "mimetypes")
@Access(AccessType.PROPERTY)
public class MimeTypeImpl extends AbstractEntity implements MimeType {

	private String mimeType;
	private String description;
	private String extension;

	@Override
	public String getMimeType() {

		return this.mimeType;
	}

	@Override
	public String getDescription() {

		return this.description;
	}

	@Override
	public String getExtension() {

		return this.extension;
	}

	public void setMimeType(String mimeType) {

		this.mimeType = mimeType;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public void setExtension(String extension) {

		this.extension = extension;
	}

	@Override
	public String toString() {

		StringBuffer buf = new StringBuffer();
		buf.append(getClass().getSimpleName());
		buf.append("[");
		buf.append("id=" + getId());
		buf.append(", mimetype=" + getMimeType());
		buf.append(", extension=" + getExtension());
		buf.append(", description=" + getDescription());
		buf.append("]");
		return buf.toString();
	}

}
