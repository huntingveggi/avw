package de.mannheimer.imd.avw.impl.persistence.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import de.mannheimer.imd.avw.api.model.Document;
import de.mannheimer.imd.avw.api.model.Message;
import de.mannheimer.imd.avw.api.model.Order;
import de.mannheimer.imd.avw.api.model.State;

@Entity
@Table(name = "orders")
@Access(AccessType.PROPERTY)
public class OrderImpl extends HistoryImpl implements Order {

	private List<Document> documents = new ArrayList<Document>();
	private State state;
	private List<Message> messages = new ArrayList<Message>();
	private String comment;

	@Override
	@Column(name = "comment")
	public String getComment() {

		return comment;
	}

	@Override
	@OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = false, fetch = FetchType.LAZY, targetEntity = DocumentImpl.class)
	@JoinColumn(name = "document_id")
	public List<Document> getDocuments() {

		return documents;
	}

	public void setDocuments(List<Document> documents) {

		this.documents = documents;
	}

	@Override
	@Transient
	public List<Message> getMessages() {

		return messages;
	}

	@Override
	@Transient
	public State getState() {

		return state;
	}

	@Override
	public void setComment(String comment) {

		this.comment = comment;
	}

}
