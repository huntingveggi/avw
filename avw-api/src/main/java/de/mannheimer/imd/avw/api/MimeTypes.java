package de.mannheimer.imd.avw.api;

public enum MimeTypes {

	APPLICATION_PDF {

		@Override
		public String toString() {

			return "application/pdf";
		}

		@Override
		public String getExtension() {

			return "pdf";
		}

	},
	APPLICATION_MS_WORD_DOCX {

		@Override
		public String toString() {

			return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
		}

		@Override
		public String getExtension() {

			return "docx";
		}

	},
	APPLICATION_MS_WORD_DOC {

		@Override
		public String toString() {

			return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
		}

		@Override
		public String getExtension() {

			return "docx";
		}

	};
	;

	public abstract String toString();

	public abstract String getExtension();

}
