package de.mannheimer.imd.avw.web;

import java.util.List;

import javax.inject.Inject;

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
import de.mannheimer.imd.avw.web.impl.OrderWrapper;

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
		for (Order order : orders) {
			order.getDocuments();
		}
		OrderWrapper wrapper = new OrderWrapper();
		wrapper.setOrders(orders);
		model.addAttribute("order", wrapper);

		return "order/order-overview";

	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(ModelMap model) {

		currentOrder = orderDao.getNewInstance();

		model.addAttribute("order", currentOrder);

		return "order";

	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable String id, ModelMap model) {

		Order order = orderDao.findById(id);

		logger.debug("Found order " + order);

		if (order != null) {
			orderDao.delete(order);
		}

		model.addAttribute("order", null);

		return "order-overview";

	}

	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String save(ModelMap model) {

		if (currentOrder != null) {
			orderDao.persist(this.currentOrder);
		}

		model.addAttribute("order", currentOrder);

		return "order";

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public String update(@RequestBody OrderImpl order, ModelMap model) {

		orderDao.update(order);
		
		model.addAttribute("order", order);

		logger.info(order.toString());

		return "order";

	}

	@RequestMapping(value = "/current", method = RequestMethod.GET)
	public String getCurrent(ModelMap model) {

		model.addAttribute("order", currentOrder);

		return "order";

	}
}
