package de.mannheimer.imd.avw.web;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.mannheimer.imd.avw.api.model.Document;
import de.mannheimer.imd.avw.api.model.MimeType;
import de.mannheimer.imd.avw.api.persistence.DocumentDao;
import de.mannheimer.imd.avw.impl.persistence.MimeTypeFactory;

@Controller
@RequestMapping(value = "/documents")
public class DocumentController {

	static final Logger logger = LoggerFactory
			.getLogger(DocumentController.class);

	@Inject
	DocumentDao documentDao;

	Document currentDocument;

	@RequestMapping(value = "/create/{extension}", method = RequestMethod.GET)
	public String create(@PathVariable String extension, ModelMap model) {

		MimeType type = MimeTypeFactory.getByExtension(extension);

		if (type == null) {
			throw new RuntimeException("No mimetype found for extension \""
					+ extension + "\"");
		}

		currentDocument = documentDao.getNewInstance(type);
		model.addAttribute("model", currentDocument);

		return "doc";

	}
}
