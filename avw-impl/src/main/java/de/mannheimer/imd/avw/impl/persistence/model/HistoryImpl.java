package de.mannheimer.imd.avw.impl.persistence.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import de.mannheimer.imd.avw.api.model.History;

@MappedSuperclass
public abstract class HistoryImpl extends AbstractEntity implements History {

	private Date creationDate = null;
	private Date lastChangeDate = null;
	private String createdByUser;
	private String lastChangeByUser;

	@Override
	@Column(name = "created_by_user")
	public String getCreatedBy() {
		return createdByUser;
	}

	@Override
	@Column(name = "creation_date")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreationDate() {
		if (creationDate == null) {
			creationDate = new Date();
		}
		return creationDate;
	}

	@Override
	@Column(name = "last_change_by_user")
	public String getLastChangeBy() {
		return lastChangeByUser;
	}

	@Override
	@Column(name = "last_change_date")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastChangeDate() {
		if (lastChangeDate == null) {
			lastChangeDate = new Date();
		}
		return lastChangeDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.mannheimer.imd.avw.api.model.History#setCreatedBy(java.lang.String)
	 */
	@Override
	public void setCreatedBy(String createdByUser) {
		this.createdByUser = createdByUser;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public void setLastChangeBy(String user) {
		lastChangeByUser = user;
	}

	@Override
	public void setLastChangeDate(Date date) {
		lastChangeDate = date;
	}

}
