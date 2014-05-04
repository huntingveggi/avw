package de.mannheimer.imd.avw.impl.persistence;

import java.util.Collection;

import de.mannheimer.imd.avw.api.model.MimeType;
import de.mannheimer.imd.avw.impl.context.ApplicatonContextHelper;

public abstract class MimeTypeFactory {

	public static MimeType getByMimeType(String mimeType) {

		Collection<MimeType> mimetypes = ApplicatonContextHelper.getContext()
				.getBeansOfType(MimeType.class).values();

		for (MimeType mt : mimetypes) {
			if (mt.getMimeType().equalsIgnoreCase(mimeType)) {
				mt.s
				return mt;
			}
		}
		return null;

	}

	public static MimeType getByExtension(String extension) {

		Collection<MimeType> mimetypes = ApplicatonContextHelper.getContext()
				.getBeansOfType(MimeType.class).values();

		for (MimeType mt : mimetypes) {
			if (mt.getExtension().equalsIgnoreCase(extension)) {
				return mt;
			}
		}
		return null;

	}
}
