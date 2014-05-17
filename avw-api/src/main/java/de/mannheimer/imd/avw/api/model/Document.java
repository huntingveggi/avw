package de.mannheimer.imd.avw.api.model;

public interface Document extends Entity, History {

	public int getVersion();

	public void setVersion(int version);

	public DocumentContainer getContainer();

	public void setContainer(DocumentContainer container);

	public abstract MimeType getMimeType();

	public abstract void setMimeType(MimeType mimeType);

}