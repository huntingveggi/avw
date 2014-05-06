package de.mannheimer.imd.avw.web;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.mannheimer.imd.avw.api.model.Order;
import de.mannheimer.imd.avw.api.persistence.OrderDao;
import de.mannheimer.imd.avw.impl.persistence.model.OrderImpl;

@Controller
@RequestMapping(value = "/orders")
public class OrderController {

	static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Inject
	OrderDao orderDao;

	Order currentOrder;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(ModelMap model) {

		currentOrder = orderDao.getNewInstance();

		model.addAttribute("order", currentOrder);

		return "order";

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public String add(@RequestBody OrderImpl order, ModelMap model) {

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
