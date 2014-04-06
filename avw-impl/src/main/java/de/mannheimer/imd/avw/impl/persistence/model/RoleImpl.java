package de.mannheimer.imd.avw.impl.persistence.model;

import de.mannheimer.imd.avw.api.model.Role;

public class RoleImpl implements Role {

	private String name;
	private String description;

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

}
