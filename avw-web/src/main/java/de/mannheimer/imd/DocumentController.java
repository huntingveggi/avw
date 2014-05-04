package de.mannheimer.imd;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.mannheimer.imd.avw.api.MimeTypes;
import de.mannheimer.imd.avw.api.model.Document;
import de.mannheimer.imd.avw.api.persistence.DocumentDao;

@Controller
@RequestMapping(value = "/documents")
public class DocumentController {

	private static final Logger logger = LoggerFactory
			.getLogger(DocumentController.class);

	@Inject
	DocumentDao documentDao;

	Document currentDocument;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public Document create() {

		MimeTypes.
		currentDocument = documentDao.getNewInstance(mimetype)
	}
}
