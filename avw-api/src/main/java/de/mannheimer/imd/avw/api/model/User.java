package de.mannheimer.imd.avw.api.model;

import java.util.List;

public interface User {

	String getName();

	String getFullName();

	List<Role> getRoles();

}