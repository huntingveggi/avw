package de.mannheimer.imd.avw.api.model;

import java.util.List;

public interface State {

	String getName();

	String getDescription();

	public abstract List<State> getAllowedForwardStatess();

	public abstract List<State> getAllowedBackwardStates();

}
