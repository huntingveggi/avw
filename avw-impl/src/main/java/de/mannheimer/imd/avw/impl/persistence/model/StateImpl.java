package de.mannheimer.imd.avw.impl.persistence.model;

import java.util.List;

import de.mannheimer.imd.avw.api.model.State;

public class StateImpl implements State {

	private String name;
	private String description;
	private List<State> allowedBackwardsStates;
	private List<State> allowedForwardStatess;

	@Override
	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	@Override
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
	public List<State> getAllowedForwardStatess() {

		return allowedForwardStatess;
	}

	public void setAllowedForwardStatess(List<State> allowedForwardStatess) {

		this.allowedForwardStatess = allowedForwardStatess;
	}

}
