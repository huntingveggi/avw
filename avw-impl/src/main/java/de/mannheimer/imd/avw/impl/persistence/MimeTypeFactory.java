package de.mannheimer.imd.avw.impl.persistence;

import java.util.Collection;

import org.slf4j.Logger;

import de.mannheimer.imd.avw.api.model.MimeType;
import de.mannheimer.imd.avw.impl.context.ApplicatonContextHelper;

public abstract class MimeTypeFactory {

	static Logger logger = org.slf4j.LoggerFactory
			.getLogger(MimeTypeFactory.class);

	public static MimeType getByMimeType(String mimeType) {

		Collection<MimeType> mimetypes = getAvailableMimeTypes();

		logger.debug("Found mimetypes: " + mimetypes.toString());

		for (MimeType mt : mimetypes) {
			if (mt.getMimeType().equalsIgnoreCase(mimeType)) {
				return mt;
			}
		}
		return null;

	}

	public static MimeType getByExtension(String extension) {

		Collection<MimeType> mimetypes = getAvailableMimeTypes();

		logger.debug("Found mimetypes: " + mimetypes.toString());

		for (MimeType mt : mimetypes) {
			if (mt.getExtension().equalsIgnoreCase(extension)) {
				return mt;
			}
		}
		return null;

	}

	public static Collection<MimeType> getAvailableMimeTypes() {

		Collection<MimeType> mimetypes = ApplicatonContextHelper.getContext()
				.getBeansOfType(MimeType.class).values();

		return mimetypes;

	}
}
