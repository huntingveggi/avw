package de.mannheimer.imd.avw.impl.persistence.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

import de.mannheimer.imd.avw.api.model.DocumentContainer;

@Entity
@Table(name = "containers")
@Access(AccessType.PROPERTY)
public class DocumentContainerImpl extends HistoryImpl implements
		DocumentContainer {

}
