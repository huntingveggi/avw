package de.mannheimer.imd.avw.api.persistence;

import java.io.IOException;
import java.io.InputStream;

import de.mannheimer.imd.avw.api.MimeTypes;
import de.mannheimer.imd.avw.api.model.Document;

public interface DocumentDao extends CrudDao<Document>, DocumentContainerDao {

	void persist(Document doc, InputStream stream) throws IOException;

	// FIXME remove exception here!!!
	public Document getNewInstance(MimeTypes mimetype) throws IOException;

	InputStream findStream(Document doc) throws IOException;

}