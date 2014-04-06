package de.mannheimer.imd.avw.api.persistence;

import java.util.List;

public interface CrudDao<T> {

	public abstract void persist(T obj);

	public abstract void delete(T obj);

	public abstract void update(T obj);

	public abstract T findById(String id);

	public abstract List<T> findAll();

}
