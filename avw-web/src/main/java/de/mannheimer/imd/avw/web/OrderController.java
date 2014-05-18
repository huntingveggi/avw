package de.mannheimer.imd.avw.web;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.mannheimer.imd.avw.api.model.Order;
import de.mannheimer.imd.avw.api.persistence.OrderDao;
import de.mannheimer.imd.avw.impl.persistence.model.OrderImpl;
import de.mannheimer.imd.avw.web.impl.ResponseMessage;

@Controller
@RequestMapping(value = "/orders")
@javax.transaction.Transactional
public class OrderController {

	static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Inject
	OrderDao orderDao;

	Order currentOrder;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String overview(ModelMap model) {

		List<Order> orders = orderDao.findAll();

		new ResponseMessage(orders).build(model);

		return "orders/overview";

	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(ModelMap model) {

		this.currentOrder = orderDao.getNewInstance();

		new ResponseMessage(this.currentOrder).build(model);

		return "orders/create";

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String orderById(@PathVariable String id, ModelMap model) {

		this.currentOrder = orderDao.findById(id);

		new ResponseMessage(this.currentOrder).build(model);

		return "orders/details";

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@Transactional
	public String update(@RequestBody OrderImpl order, ModelMap model) {

		orderDao.update(order);
		this.currentOrder = order;

		new ResponseMessage(this.currentOrder).build(model);

		logger.info(order.toString());

		return "redirect:/orders/" + this.currentOrder.getId();

	}

}
