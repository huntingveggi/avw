package de.mannheimer.imd.avw.api.persistence;

import java.io.IOException;
import java.io.InputStream;

import de.mannheimer.imd.avw.api.model.Document;
import de.mannheimer.imd.avw.api.model.MimeType;

public interface DocumentDao extends CrudDao<Document>, DocumentContainerDao {

	void persist(Document doc, InputStream stream) throws IOException;

	// FIXME remove exception here!!!
	public Document getNewInstance(MimeType mimetype) throws IOException;

	InputStream findStream(Document doc) throws IOException;

}