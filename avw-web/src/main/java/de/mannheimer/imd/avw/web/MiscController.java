package de.mannheimer.imd.avw.web;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.mannheimer.imd.avw.api.model.Document;
import de.mannheimer.imd.avw.api.model.State;
import de.mannheimer.imd.avw.api.persistence.DocumentDao;
import de.mannheimer.imd.avw.impl.persistence.StateFactory;
import de.mannheimer.imd.avw.web.impl.ResponseMessage;

@Controller
public class MiscController {

	static final Logger logger = LoggerFactory.getLogger(MiscController.class);

	@Inject
	DocumentDao documentDao;

	Document currentDocument;

	@RequestMapping(value = "/states", method = RequestMethod.GET)
	@Transactional
	public String states(ModelMap model) {

		List<State> states = StateFactory.getAvailableStates();

		model.addAttribute("reponse", new ResponseMessage(states));

		return "states";

	}

}
