package de.mannheimer.imd.avw.web;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.mannheimer.imd.avw.api.model.Document;
import de.mannheimer.imd.avw.api.model.DocumentContainer;
import de.mannheimer.imd.avw.api.persistence.DocumentDao;
import de.mannheimer.imd.avw.web.impl.ResponseMessage;

@Controller
@RequestMapping(value = "/containers")
public class ContainerController {

	static final Logger logger = LoggerFactory
			.getLogger(ContainerController.class);

	@Inject
	DocumentDao documentDao;

	DocumentContainer currentContainer;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String find(ModelMap model) {

		List<DocumentContainer> containers = documentDao.findAllContainers();

		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setModel(containers);
		model.addAttribute(responseMessage);

		return "containers/overview";

	}

	@RequestMapping(value = "/{containerName}/details", method = RequestMethod.GET)
	public String details(@PathVariable String containerName, ModelMap model) {

		DocumentContainer container = documentDao
				.findSingleContainerByName(containerName);
		List<Document> documents = documentDao.findBy(container);

		ResponseMessage containerResponseMessage = new ResponseMessage(
				container);
		model.addAttribute("container", containerResponseMessage);

		ResponseMessage documentsResponseMessage = new ResponseMessage(
				documents);
		model.addAttribute("documents", documentsResponseMessage);

		return "containers/details";

	}

}
