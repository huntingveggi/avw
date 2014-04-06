package de.mannheimer.imd.avw.api.model;

import java.util.List;

public interface Order extends Entity, History {

	List<Document> getDocuments();

	State getState();

	List<Message> getMessages();

	String getComment();

	void setComment(String comment);

}