package de.mannheimer.imd.avw.impl.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import de.mannheimer.imd.avw.api.model.Order;
import de.mannheimer.imd.avw.api.model.State;
import de.mannheimer.imd.avw.api.persistence.OrderDao;
import de.mannheimer.imd.avw.impl.persistence.model.OrderImpl;

@Repository
@Scope("prototype")
public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {

	@Override
	@Transactional
	public Order findById(String id) {

		return super.findById(id, Order.class);
	}

	@Override
	@Transactional
	public List<Order> findAll() {

		List<Order> orders = super.findAll(Order.class);
		return new ArrayList<Order>(orders);
	}

	@Override
	public Order getNewInstance() {

		OrderImpl order = new OrderImpl();
		order.setId(getGenerator().createUniqueId());
		State initialState = getContext().getBean("initial", State.class);
		order.setState(initialState);
		return order;
	}

	@Override
	public void doLazyInitialize(Order order) {

		if (order != null) {
			order.getDocuments().size();
			order.getMessages().toString();
			order.getState().toString();
		}

	}

}
