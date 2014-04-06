package de.mannheimer.imd.avw.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import de.mannheimer.imd.avw.api.MimeTypes;
import de.mannheimer.imd.avw.api.model.Document;
import de.mannheimer.imd.avw.api.persistence.DocumentDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/avw-impl/*-context.xml" })
@TransactionConfiguration(defaultRollback = true)
public class DocumentDaoImplTest {

	Logger logger = LoggerFactory.getLogger(DocumentDaoImplTest.class);

	@Inject
	DocumentDao documentDao = null;
	Document currentDocument;
	String currentDocumentId;
	static InputStream currentDocumentInputStream;

	@Before
	public void setUp() throws Exception {

		logger.info("------ SET UP -----------------------------------------------------");

		currentDocumentInputStream = new ClassPathResource("test.pdf")
				.getInputStream();

		assertNotNull(currentDocumentInputStream);

		currentDocument = documentDao.getNewInstance(MimeTypes.APPLICATION_PDF);

		assertNotNull(currentDocument);

		documentDao.persist(currentDocument, currentDocumentInputStream);

		currentDocumentId = currentDocument.getId();

		logger.info("-------------------------");

	}

	@After
	public void tearDown() throws Exception {

		if (currentDocument != null) {
			documentDao.delete(currentDocument);
		}
		File docFile = new File("temp/" + currentDocumentId + ".pdf");
		assertTrue("Document " + docFile.getAbsolutePath()
				+ " still exists after delete!", !docFile.exists());
		currentDocument = null;
		currentDocumentId = null;

		logger.info("------ TEAR DOWN -----------------------------------------------------");
	}

	@Test
	public void findAllTest() {

		List<Document> documents = documentDao.findAll();
		assertTrue(documents.size() > 0);

	}

	public void findByDocumentContainerTest() {

		assertTrue("Not yet implemented", false);
	}

	@Test
	public void findByIdTest() {

		Document doc = documentDao.findById(currentDocumentId);
		assertNotNull(doc);
		assertNotNull(doc.getId());

	}

	public void getNewInstanceTest() {

		Document doc = null;
		try {
			doc = documentDao.getNewInstance(MimeTypes.APPLICATION_PDF);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			fail(e.getMessage());
		}
		assertNotNull(doc);
		assertNotNull(doc.getId());
		assertNotNull(doc.getContainer());

	}

	@Test
	public void updateTest() {

		Document persistedDoc = documentDao.findById(currentDocumentId);

		String updatedProp = "Max Mustermann";
		persistedDoc.setLastChangeBy(updatedProp);
		documentDao.update(persistedDoc);

		Document updatedDoc = documentDao.findById(currentDocumentId);

		assertTrue(updatedDoc.getLastChangeBy().equals(updatedProp));

	}

	@Test
	public void findStreamTest() {

		InputStream stream = null;
		try {
			stream = documentDao.findStream(currentDocument);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			fail(e.getMessage());
		} finally {
			if (stream != null) {
				IOUtils.closeQuietly(stream);
			}
		}
		assertNotNull(stream);
	}

}
