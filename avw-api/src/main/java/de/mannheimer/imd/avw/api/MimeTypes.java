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

	};

	public abstract String toString();

	public abstract String getExtension();

}
