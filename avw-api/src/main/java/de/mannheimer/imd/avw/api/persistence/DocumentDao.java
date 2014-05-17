package de.mannheimer.imd.avw.api.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import de.mannheimer.imd.avw.api.model.Document;
import de.mannheimer.imd.avw.api.model.DocumentContainer;
import de.mannheimer.imd.avw.api.model.MimeType;

public interface DocumentDao extends CrudDao<Document>, DocumentContainerDao {

	void persist(Document doc, InputStream stream) throws IOException;

	public Document getNewInstance(MimeType mimetype, String containerName);

	public Document getNewInstance();

	InputStream findStream(Document doc) throws IOException;

	public abstract DocumentContainer findSingleContainerByName(String name);

	public abstract DocumentContainer getContainerInstance(String name);

	public abstract List<DocumentContainer> findAllContainers();

}