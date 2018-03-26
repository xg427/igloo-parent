package org.iglooproject.functional.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class StringLocalDateConverter extends SerializableConverter2<String, LocalDate> {

	private static final long serialVersionUID = -6541233676567863013L;

	private static final StringLocalDateConverter INSTANCE = new StringLocalDateConverter();

	private static final String PATTERN = "yyyy-MM-dd";

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN, Locale.ROOT);

	public static StringLocalDateConverter get() {
		return INSTANCE;
	}

	protected StringLocalDateConverter() {
	}

	@Override
	protected LocalDate doForward(String a) {
		return LocalDate.parse(a, FORMATTER);
	}

	@Override
	protected String doBackward(LocalDate b) {
		return b.format(FORMATTER);
	}

	/**
	 * Workaround sonar/findbugs - https://github.com/google/guava/issues/1858
	 * Guava Converter overrides only equals to add javadoc, but findbugs warns about non coherent equals/hashcode
	 * possible issue.
	 */
	@Override
	public boolean equals(Object object) {
		return super.equals(object);
	}

	/**
	 * Workaround sonar/findbugs - see #equals(Object)
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
