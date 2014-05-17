package de.mannheimer.imd.avw.api.persistence;

import java.util.List;

import de.mannheimer.imd.avw.api.model.Document;
import de.mannheimer.imd.avw.api.model.DocumentContainer;

public interface DocumentContainerDao {

	List<Document> findBy(DocumentContainer container);

	List<DocumentContainer> findContainersByName(String name);

}
