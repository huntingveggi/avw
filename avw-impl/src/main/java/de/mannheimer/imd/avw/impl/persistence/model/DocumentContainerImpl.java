package de.mannheimer.imd.avw.impl.persistence.model;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import de.mannheimer.imd.avw.api.model.Document;
import de.mannheimer.imd.avw.api.model.DocumentContainer;

@Entity
@Table(name = "containers")
@Access(AccessType.PROPERTY)
public class DocumentContainerImpl extends HistoryImpl implements
		DocumentContainer {

	private List<Document> documents;

	@Override
	@ManyToOne(targetEntity = DocumentImpl.class, cascade = { CascadeType.ALL })
	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

}
