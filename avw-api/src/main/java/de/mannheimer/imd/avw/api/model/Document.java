package de.mannheimer.imd.avw.api.model;

public interface Document extends Entity, History {

	public int getVersion();

	public DocumentContainer getContainer();

	public void setContainer(DocumentContainer container);

	public abstract MimeType getMimeType();

}