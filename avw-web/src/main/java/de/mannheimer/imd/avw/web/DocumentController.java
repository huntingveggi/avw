package de.mannheimer.imd.avw.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import de.mannheimer.imd.avw.api.model.Document;
import de.mannheimer.imd.avw.api.model.DocumentContainer;
import de.mannheimer.imd.avw.api.model.MimeType;
import de.mannheimer.imd.avw.api.persistence.DocumentDao;
import de.mannheimer.imd.avw.impl.persistence.MimeTypeFactory;
import de.mannheimer.imd.avw.web.impl.MimeTypeWrapper;
import de.mannheimer.imd.avw.web.impl.ResponseMessage;
import de.mannheimer.imd.avw.web.impl.ResponseMessageFactory;

@Controller
@RequestMapping(value = "/documents")
public class DocumentController {

	static final Logger logger = LoggerFactory
			.getLogger(DocumentController.class);

	@Inject
	DocumentDao documentDao;

	Document currentDocument;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	@Transactional
	public String create(ModelMap model) {

		this.currentDocument = documentDao.getNewInstance();

		ResponseMessage msg = new ResponseMessage(this.currentDocument);
		model.addAttribute(msg);

		return "documents/create";

	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@Transactional
	public String upload(@RequestParam MultipartFile file,
			@RequestParam String containerName, @RequestParam String mimetype,
			ModelMap model) {

		this.currentDocument = documentDao.getNewInstance();

		logger.info(file.toString());
		logger.info("Container: {}", containerName);
		logger.info("mimetype: {}", mimetype);

		MimeType type = MimeTypeFactory.getByExtension(mimetype);

		if (type != null) {
			this.currentDocument.setMimeType(type);
		} else {
			// TODO throw exception
		}
		DocumentContainer container = documentDao
				.getContainerInstance(containerName);
		this.currentDocument.setContainer(container);

		try {
			documentDao.persist(this.currentDocument, file.getInputStream());
		} catch (IOException e) {
			// todo handle exception
			e.printStackTrace();
		}

		model.addAttribute(new ResponseMessage(this.currentDocument));

		return "redirect:/documents/" + this.currentDocument.getId()
				+ "/details";

	}

	@RequestMapping(value = "/mimetypes", method = RequestMethod.GET)
	public String extensions(ModelMap model) {

		Collection<MimeType> types = MimeTypeFactory.getAvailableMimeTypes();

		ResponseMessage response = ResponseMessageFactory
				.createResponseMessage(new MimeTypeWrapper(types));

		model.addAttribute(response);

		return "mimetypes";

	}

	@RequestMapping(value = "/{documentId}/details", method = RequestMethod.GET)
	public String details(@PathVariable String documentId, ModelMap model) {

		this.currentDocument = documentDao.findById(documentId);

		model.addAttribute(new ResponseMessage(this.currentDocument));

		return "documents/details";

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String overview(ModelMap model) {

		List<Document> documents = documentDao.findAll();

		model.addAttribute(new ResponseMessage(documents));

		return "documents/overview";

	}

	@RequestMapping(value = "/{documentId}", method = RequestMethod.GET)
	public void download(@PathVariable String documentId, ModelMap model,
			HttpServletResponse response) throws IOException {

		Document doc = documentDao.findById(documentId);

		logger.debug("Try to get document stream for document {}", doc);

		if (doc != null) {

			OutputStream output = response.getOutputStream();
			logger.debug("OutputStream is {}", output);
			InputStream input = documentDao.findStream(doc);
			logger.debug("Inputsream is {}", input);

			response.setContentType("application/pdf");
			response.setContentLength(input.available());
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ doc.getContainer().getName() + "_" + doc.getVersion()
					+ ".pdf" + "\"");

			IOUtils.copy(input, output);
		}

	}
}
