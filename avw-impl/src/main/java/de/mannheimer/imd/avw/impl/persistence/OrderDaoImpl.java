package de.mannheimer.imd.avw.impl.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import de.mannheimer.imd.avw.api.model.Order;
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

		return super.findAll(Order.class);
	}

	@Override
	public Order getNewInstance() {

		OrderImpl order = new OrderImpl();
		order.setId(getGenerator().createUniqueId());
		return order;
	}

}
