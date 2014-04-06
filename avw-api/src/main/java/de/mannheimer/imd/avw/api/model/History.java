package de.mannheimer.imd.avw.api.model;

import java.util.Date;

public interface History {

	public Date getLastChangeDate();

	public void setLastChangeDate(Date date);

	public Date getCreationDate();

	public String getCreatedBy();

	public void setCreatedBy(String name);

	public String getLastChangeBy();

	public void setLastChangeBy(String user);

}
