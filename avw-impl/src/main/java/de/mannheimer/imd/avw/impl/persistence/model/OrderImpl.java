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
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.context.ApplicationContext;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.ahaus.dennis.javautils.impl.helper.Assert;
import de.mannheimer.imd.avw.api.model.Document;
import de.mannheimer.imd.avw.api.model.Message;
import de.mannheimer.imd.avw.api.model.Order;
import de.mannheimer.imd.avw.api.model.State;
import de.mannheimer.imd.avw.impl.ApplicatonContextHelper;

@Entity
@Table(name = "orders")
@Access(AccessType.PROPERTY)
public class OrderImpl extends HistoryImpl implements Order {

	private List<Document> documents = new ArrayList<Document>();
	private State state = null;
	private List<Message> messages = new ArrayList<Message>();
	private String comment = "";
	ApplicationContext context;

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

		Assert.notNull(
				"Error while trying to set documents list: Parameter for documents list ist null!",
				documents);
		this.documents = documents;
	}

	@Override
	@Transient
	public List<Message> getMessages() {

		return messages;
	}

	public void setMessages(List<Message> messages) {

		Assert.notNull(
				"Error while trying to set message list: Parameter for message list is null!",
				messages);
		this.messages = messages;
	}

	@Override
	@Transient
	public State getState() {

		return state;
	}

	public void setState(State state) {

		Assert.notNull(
				"Error while setting state object: Parameter for state is null!",
				state);

		this.state = state;
	}

	@Column(name = "state_name")
	public String getStateName() {

		if (this.getState() != null) {
			return this.getState().getName();
		}
		return null;
	}

	public void setStateName(String name) {

		Assert.notNull(
				"Error while trying to set state name: Parametername is null",
				name);

		State state = getContext().getBean(name, State.class);

		Assert.notNull(
				"Error while trying to set state name: There is no state with name\""
						+ name + "\"", state);

		setState(state);
	}

	@Override
	public void setComment(String comment) {

		Assert.notNull(
				"Error while trying to set comment: Parameter for comment is null!",
				comment);

		this.comment = comment;
	}

	@Transient
	@XmlTransient
	@JsonIgnore
	public ApplicationContext getContext() {

		if (this.context == null) {
			ApplicationContext ctx = ApplicatonContextHelper.getContext();
			setContext(ctx);
		}

		return context;
	}

	public void setContext(ApplicationContext context) {

		Assert.notNull(
				"Errir while trying to set context: Parameter for context is null!",
				context);

		this.context = context;
	}

}
