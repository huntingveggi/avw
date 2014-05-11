package de.mannheimer.imd.avw.web.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import de.mannheimer.imd.avw.api.model.Order;
import de.mannheimer.imd.avw.impl.persistence.model.OrderImpl;

@XmlRootElement(name = "orders")
public class OrderWrapper {

	List<Order> orders = new ArrayList<Order>();

	@XmlElement(name = "order", type = OrderImpl.class)
	public List<Order> getObjects() {

		return this.orders;
	}

	public void setOrders(List<Order> orders) {

		this.orders = orders;
	}

}
