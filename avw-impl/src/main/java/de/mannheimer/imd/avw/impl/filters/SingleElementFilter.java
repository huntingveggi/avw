package de.mannheimer.imd.avw.impl.filters;

import java.util.List;

public class SingleElementFilter<E> extends AbstractCollectionFilter<E> {

	public E filter(List<E> objects, Filter<E> filter) {

		List<E> result = super.doFilter(objects, filter);
		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}

}
