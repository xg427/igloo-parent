package org.iglooproject.functional.converter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

import com.google.common.collect.Lists;

public class StringLocalTimeConverter extends SerializableConverter2<String, LocalTime> {

	private static final long serialVersionUID = -6541233676567863013L;

	private static final StringLocalTimeConverter INSTANCE = new StringLocalTimeConverter();

	private static final String PATTERN = "HH:mm";
	private static final String PATTERN_FULL = "HH:mm:ss";

	private static final DateTimeFormatter FORWARD_FORMATTER = Lists.newArrayList(PATTERN, PATTERN_FULL).stream()
			.map((p) -> DateTimeFormatter.ofPattern(p, Locale.ROOT))
			.reduce(
					new DateTimeFormatterBuilder(), 
					DateTimeFormatterBuilder::appendOptional,
					(f1, f2) -> f1.append(f2.toFormatter())
			)
			.toFormatter();

	private static final DateTimeFormatter BACKWARD_FORMATTER = DateTimeFormatter.ofPattern(PATTERN_FULL, Locale.ROOT);

	public static StringLocalTimeConverter get() {
		return INSTANCE;
	}

	protected StringLocalTimeConverter() {
	}

	@Override
	protected LocalTime doForward(String a) {
		return LocalTime.parse(a, FORWARD_FORMATTER);
	}

	@Override
	protected String doBackward(LocalTime b) {
		return b.format(BACKWARD_FORMATTER);
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
