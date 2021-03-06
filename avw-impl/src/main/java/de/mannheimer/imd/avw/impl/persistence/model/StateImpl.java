package de.mannheimer.imd.avw.impl.persistence.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import de.mannheimer.imd.avw.api.model.State;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class StateImpl implements State {

	private String name;
	private String description;
	private List<State> allowedBackwardsStates = new ArrayList<State>();
	private List<State> allowedForwardStates = new ArrayList<State>();

	@Override
	@XmlAttribute
	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	@Override
	@XmlElement
	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	@Override
	public List<State> getAllowedBackwardStates() {

		return allowedBackwardsStates;
	}

	public void setAllowedBackwardsStates(List<State> allowedBackwardsStates) {

		this.allowedBackwardsStates = allowedBackwardsStates;
	}

	@Override
	public List<State> getAllowedForwardStates() {

		return allowedForwardStates;
	}

	public void setAllowedForwardStates(List<State> allowedForwardStatess) {

		this.allowedForwardStates = allowedForwardStatess;
	}

}
