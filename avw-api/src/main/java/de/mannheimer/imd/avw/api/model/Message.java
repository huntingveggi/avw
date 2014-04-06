package de.mannheimer.imd.avw.api.model;

import java.util.Date;

public interface Message {

	String getCreator();

	Date getCreationDate();

	String getText();

	Order getOrder();

}
