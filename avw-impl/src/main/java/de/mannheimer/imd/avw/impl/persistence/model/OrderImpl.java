package de.mannheimer.imd.avw.impl.persistence.model;

import java.util.List;

import de.mannheimer.imd.avw.api.model.Document;
import de.mannheimer.imd.avw.api.model.Message;
import de.mannheimer.imd.avw.api.model.Order;
import de.mannheimer.imd.avw.api.model.State;

//@Entity
//@Table(name = "orders")
//@Access(AccessType.PROPERTY)
public class OrderImpl extends HistoryImpl implements Order {

	private List<Document> documents;
	private State state;
	private List<Message> messages;
	private String comment;

	@Override
	public String getComment() {
		return comment;
	}

	@Override
	public List<Document> getDocuments() {
		return documents;
	}

	@Override
	public List<Message> getMessages() {
		return messages;
	}

	@Override
	public State getState() {
		return state;
	}

	@Override
	public void setComment(String comment) {
		this.comment = comment;
	}

}
