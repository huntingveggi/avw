package de.mannheimer.imd.avw.impl.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;

import de.mannheimer.imd.avw.api.model.State;
import de.mannheimer.imd.avw.impl.context.ApplicatonContextHelper;

public abstract class StateFactory {

	static Logger logger = org.slf4j.LoggerFactory
			.getLogger(StateFactory.class);

	public static List<State> getAvailableStates() {

		Collection<State> states = ApplicatonContextHelper.getContext()
				.getBeansOfType(State.class).values();

		logger.debug("Found states: " + states.toString());

		return new ArrayList<State>(states);

	}

	public static State getStateByName(String name) {

		return ApplicatonContextHelper.getContext().getBean(name, State.class);

	}

}
