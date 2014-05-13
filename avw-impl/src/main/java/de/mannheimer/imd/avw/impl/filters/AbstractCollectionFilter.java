package de.mannheimer.imd.avw.impl.filters;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractCollectionFilter<E> {

	protected List<E> doFilter(List<E> objects, Filter<E> filter) {

		List<E> result = new LinkedList<E>();
		for (E element : objects) {
			if (filter.accept(element)) {
				result.add(element);
			}
		}
		return result;
	}

}
