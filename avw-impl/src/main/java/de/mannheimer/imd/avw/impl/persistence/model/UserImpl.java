package de.mannheimer.imd.avw.impl.persistence.model;

import java.util.List;

import javax.persistence.Column;

import de.mannheimer.imd.avw.api.model.Role;
import de.mannheimer.imd.avw.api.model.User;

public class UserImpl extends AbstractEntity implements User {

	private String name;
	private List<Role> roles;

	@Override
	@Column(name = "full_name")
	public String getFullName() {
		return this.getName();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<Role> getRoles() {
		return roles;
	}

	public void setName(String name) {
		this.name = name;
	}

}
