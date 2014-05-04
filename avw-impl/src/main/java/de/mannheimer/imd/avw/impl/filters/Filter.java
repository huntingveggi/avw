package de.mannheimer.imd.avw.impl.filters;

public interface Filter<E> {

	public boolean accept(E object);
}
