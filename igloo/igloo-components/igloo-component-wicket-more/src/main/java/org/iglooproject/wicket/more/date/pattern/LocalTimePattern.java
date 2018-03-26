package org.iglooproject.wicket.more.date.pattern;

public enum LocalTimePattern implements ILocalTimePattern {

	TIME("date.format.time", null);

	private String javaPatternKey;

	private String javascriptPatternKey;

	private boolean capitalize;

	private LocalTimePattern(String javaPatternKey, String javascriptPatternKey) {
		this(javaPatternKey, javascriptPatternKey, false);
	}

	private LocalTimePattern(String javaPatternKey, String javascriptPatternKey, boolean capitalize) {
		this.javaPatternKey = javaPatternKey;
		this.javascriptPatternKey = javascriptPatternKey;
		this.capitalize = capitalize;
	}

	@Override
	public String getJavaPatternKey() {
		if (javaPatternKey != null) {
			return javaPatternKey;
		} else {
			throw new IllegalStateException("Java format not supported for this format");
		}
	}

	@Override
	public String getJavascriptPatternKey() {
		if (javascriptPatternKey != null) {
			return javascriptPatternKey;
		} else {
			throw new IllegalStateException("Javascript format not supported for this format");
		}
	}

	@Override
	public boolean capitalize() {
		return capitalize;
	}

}