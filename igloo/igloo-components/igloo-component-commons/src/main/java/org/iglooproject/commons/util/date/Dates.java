package org.iglooproject.commons.util.date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

public final class Dates {

	public static final LocalDateTime nowLocalDateTime() {
		return nowLocalDateTime(defaultZoneId());
	}

	public static final LocalDateTime nowLocalDateTime(ZoneId zoneId) {
		return LocalDateTime.now(fallback(zoneId));
	}

	public static final LocalDate nowLocalDate() {
		return nowLocalDate(defaultZoneId());
	}

	public static final LocalDate nowLocalDate(ZoneId zoneId) {
		return LocalDate.now(fallback(zoneId));
	}

	public static final LocalTime nowLocalTime() {
		return nowLocalTime(defaultZoneId());
	}

	public static final LocalTime nowLocalTime(ZoneId zoneId) {
		return LocalTime.now(fallback(zoneId));
	}

	public static final ZonedDateTime nowZonedDateTime() {
		return nowZonedDateTime(defaultZoneId());
	}

	public static final ZonedDateTime nowZonedDateTime(ZoneId zoneId) {
		return ZonedDateTime.now(fallback(zoneId));
	}

	public static final ZoneId defaultZoneId() {
		return ZoneId.systemDefault();
	}

	public static final ZoneId fallback(ZoneId zoneId) {
		return Optional.ofNullable(zoneId).orElse(defaultZoneId());
	}

	/*
	 * Converter from Epoch milli
	 */

	public static final Instant toInstant(long epochMilli) {
		return toInstantOptional(epochMilli).orElse(null);
	}

	public static final Optional<Instant> toInstantOptional(long epochMilli) {
		return Optional.ofNullable(epochMilli)
				.map(Instant::ofEpochMilli);
	}

	public static final ZonedDateTime toZonedDateTime(long epochMilli) {
		return toZonedDateTimeOptional(epochMilli).orElse(null);
	}

	public static final Optional<ZonedDateTime> toZonedDateTimeOptional(long epochMilli) {
		return toZonedDateTimeOptional(epochMilli, defaultZoneId());
	}

	public static final ZonedDateTime toZonedDateTime(long epochMilli, ZoneId zoneId) {
		return toZonedDateTimeOptional(epochMilli, zoneId).orElse(null);
	}

	public static final Optional<ZonedDateTime> toZonedDateTimeOptional(long epochMilli, ZoneId zoneId) {
		return Optional.ofNullable(epochMilli)
				.map(Instant::ofEpochMilli)
				.map((i) -> i.atZone(fallback(zoneId)));
	}

	public static final LocalDateTime toLocalDateTime(long epochMilli) {
		return toLocalDateTimeOptional(epochMilli).orElse(null);
	}

	public static final Optional<LocalDateTime> toLocalDateTimeOptional(long epochMilli) {
		return toLocalDateTimeOptional(epochMilli, defaultZoneId());
	}

	public static final LocalDateTime toLocalDateTime(long epochMilli, ZoneId zoneId) {
		return toLocalDateTimeOptional(epochMilli, zoneId).orElse(null);
	}

	public static final Optional<LocalDateTime> toLocalDateTimeOptional(long epochMilli, ZoneId zoneId) {
		return toLocaleDateTimeOptional(toZonedDateTime(epochMilli, zoneId));
	}

	public static final LocalDate toLocalDate(long epochMilli) {
		return toLocalDateOptional(epochMilli).orElse(null);
	}

	public static final Optional<LocalDate> toLocalDateOptional(long epochMilli) {
		return toLocalDateOptional(epochMilli, defaultZoneId());
	}

	public static final LocalDate toLocalDate(long epochMilli, ZoneId zoneId) {
		return toLocalDateOptional(epochMilli, zoneId).orElse(null);
	}

	public static final Optional<LocalDate> toLocalDateOptional(long epochMilli, ZoneId zoneId) {
		return toLocaleDateOptional(toZonedDateTime(epochMilli, zoneId));
	}

	/*
	 * ZonedDateTime to Instant
	 */

	public static final Optional<Instant> toInstantOptional(ZonedDateTime zonedDateTime) {
		return Optional.ofNullable(zonedDateTime)
				.map(ZonedDateTime::toInstant);
	}

	public static final Instant toInstant(ZonedDateTime localDate) {
		return toInstantOptional(localDate).orElse(null);
	}

	/*
	 * ZonedDateTime to LocaleDateTime
	 */

	public static final Optional<LocalDateTime> toLocaleDateTimeOptional(ZonedDateTime zonedDateTime) {
		return Optional.ofNullable(zonedDateTime)
				.map(ZonedDateTime::toLocalDateTime);
	}

	public static final LocalDateTime toLocaleDateTime(ZonedDateTime zonedDateTime) {
		return toLocaleDateTimeOptional(zonedDateTime).orElse(null);
	}

	/*
	 * ZonedDateTime to LocaleDate
	 */

	public static final Optional<LocalDate> toLocaleDateOptional(ZonedDateTime zonedDateTime) {
		return Optional.ofNullable(zonedDateTime)
				.map(ZonedDateTime::toLocalDate);
	}

	public static final LocalDate toLocaleDate(ZonedDateTime zonedDateTime) {
		return toLocaleDateOptional(zonedDateTime).orElse(null);
	}

	/*
	 * ZonedDateTime to LocaleTime
	 */

	public static final Optional<LocalTime> toLocaleTimeOptional(ZonedDateTime zonedDateTime) {
		return Optional.ofNullable(zonedDateTime)
				.map(ZonedDateTime::toLocalTime);
	}

	public static final LocalTime toLocaleTime(ZonedDateTime zonedDateTime) {
		return toLocaleTimeOptional(zonedDateTime).orElse(null);
	}

	/*
	 * ZonedDateTime to Date
	 */

	public static final Optional<Date> toDateOptional(ZonedDateTime zonedDateTime) {
		return toInstantOptional(zonedDateTime)
				.map(Date::from);
	}

	public static final Date toDate(ZonedDateTime zonedDateTime) {
		return toDateOptional(zonedDateTime).orElse(null);
	}

	/*
	 * LocalDateTime to Instant
	 */

	public static final Optional<Instant> toInstantOptional(LocalDateTime localDateTime) {
		return toInstantOptional(localDateTime, defaultZoneId());
	}

	public static final Instant toInstant(LocalDateTime localDateTime) {
		return toInstant(localDateTime, defaultZoneId());
	}

	public static final Optional<Instant> toInstantOptional(LocalDateTime localDateTime, ZoneId zoneId) {
		return toZonedDateTimeOptional(localDateTime, zoneId)
				.map(ZonedDateTime::toInstant);
	}

	public static final Instant toInstant(LocalDateTime localDateTime, ZoneId zoneId) {
		return toInstantOptional(localDateTime, zoneId).orElse(null);
	}

	/*
	 * LocalDateTime to ZonedDateTime
	 */

	public static final Optional<ZonedDateTime> toZonedDateTimeOptional(LocalDateTime localDateTime) {
		return toZonedDateTimeOptional(localDateTime, defaultZoneId());
	}

	public static final ZonedDateTime toZonedDateTime(LocalDateTime localDateTime) {
		return toZonedDateTime(localDateTime, defaultZoneId());
	}

	public static final Optional<ZonedDateTime> toZonedDateTimeOptional(LocalDateTime localDateTime, ZoneId zoneId) {
		return Optional.ofNullable(localDateTime)
				.map((d) -> d.atZone(fallback(zoneId)));
	}

	public static final ZonedDateTime toZonedDateTime(LocalDateTime localDateTime, ZoneId zoneId) {
		return toZonedDateTimeOptional(localDateTime, zoneId).orElse(null);
	}

	/*
	 * LocalDateTime to LocalDate
	 */

	public static final LocalDate toLocalDate(LocalDateTime localDateTime) {
		return toLocalDateOptional(localDateTime).orElse(null);
	}

	public static final Optional<LocalDate> toLocalDateOptional(LocalDateTime localDateTime) {
		return Optional.ofNullable(localDateTime)
				.map(LocalDateTime::toLocalDate);
	}

	/*
	 * LocalDateTime to LocalTime
	 */

	public static final LocalTime toLocalTime(LocalDateTime localDateTime) {
		return toLocalTimeOptional(localDateTime).orElse(null);
	}

	public static final Optional<LocalTime> toLocalTimeOptional(LocalDateTime localDateTime) {
		return Optional.ofNullable(localDateTime)
				.map(LocalDateTime::toLocalTime);
	}

	/*
	 * LocalDateTime to Date
	 */

	public static final Optional<Date> toDateOptional(LocalDateTime localDateTime) {
		return toDateOptional(localDateTime, defaultZoneId());
	}

	public static final Date toDate(LocalDateTime localDateTime) {
		return toDate(localDateTime, defaultZoneId());
	}

	public static final Optional<Date> toDateOptional(LocalDateTime localDateTime, ZoneId zoneId) {
		return toInstantOptional(localDateTime, zoneId)
				.map(Date::from);
	}

	public static final Date toDate(LocalDateTime localDateTime, ZoneId zoneId) {
		return toDateOptional(localDateTime, zoneId).orElse(null);
	}

	/*
	 * LocalDate to Instant
	 */

	public static final Optional<Instant> toInstantOptional(LocalDate localDate) {
		return toInstantOptional(localDate, defaultZoneId());
	}

	public static final Instant toInstant(LocalDate localDate) {
		return toInstant(localDate, defaultZoneId());
	}

	public static final Optional<Instant> toInstantOptional(LocalDate localDate, ZoneId zoneId) {
		return toZonedDateTimeOptional(localDate, zoneId)
				.map(ZonedDateTime::toInstant);
	}

	public static final Instant toInstant(LocalDate localDate, ZoneId zoneId) {
		return toInstantOptional(localDate, zoneId).orElse(null);
	}

	/*
	 * LocalDate to ZonedDateTime
	 */

	public static final Optional<ZonedDateTime> toZonedDateTimeOptional(LocalDate localDate) {
		return toZonedDateTimeOptional(localDate, defaultZoneId());
	}

	public static final ZonedDateTime toZonedDateTime(LocalDate localDate) {
		return toZonedDateTime(localDate, defaultZoneId());
	}

	public static final Optional<ZonedDateTime> toZonedDateTimeOptional(LocalDate localDate, ZoneId zoneId) {
		return Optional.ofNullable(localDate)
				.map((d) -> d.atStartOfDay(fallback(zoneId)));
	}

	public static final ZonedDateTime toZonedDateTime(LocalDate localDate, ZoneId zoneId) {
		return toZonedDateTimeOptional(localDate, zoneId).orElse(null);
	}

	/*
	 * LocalDate to Date
	 */

	public static final Optional<Date> toDateOptional(LocalDate localDate) {
		return toDateOptional(localDate, defaultZoneId());
	}

	public static final Date toDate(LocalDate localDate) {
		return toDate(localDate, defaultZoneId());
	}

	public static final Optional<Date> toDateOptional(LocalDate localDate, ZoneId zoneId) {
		return toInstantOptional(localDate, zoneId)
				.map(Date::from);
	}

	public static final Date toDate(LocalDate localDate, ZoneId zoneId) {
		return toDateOptional(localDate, zoneId).orElse(null);
	}

	/*
	 * Date to Instant
	 */

	public static final Optional<Instant> toInstantOptional(Date date) {
		return Optional.ofNullable(date)
				.map(Date::toInstant);
	}

	public static final Instant toInstant(Date date) {
		return toInstantOptional(date).orElse(null);
	}

	/*
	 * Date to ZonedDateTime
	 */

	public static final Optional<ZonedDateTime> toZonedDateTimeOptional(Date date) {
		return toZonedDateTimeOptional(date, defaultZoneId());
	}

	public static final ZonedDateTime toZonedDateTime(Date date) {
		return toZonedDateTimeOptional(date).orElse(null);
	}

	public static final Optional<ZonedDateTime> toZonedDateTimeOptional(Date date, ZoneId zoneId) {
		return Optional.ofNullable(date)
				.map(Dates::toInstant)
				.map((i) -> i.atZone(fallback(zoneId)));
	}

	public static final ZonedDateTime toZonedDateTime(Date date, ZoneId zoneId) {
		return toZonedDateTimeOptional(date, zoneId).orElse(null);
	}

	/*
	 * Date to LocalDateTime
	 */

	public static final Optional<LocalDateTime> toLocalDateTimeOptional(Date date) {
		return toLocalDateTimeOptional(date, defaultZoneId());
	}

	public static final LocalDateTime toLocalDateTime(Date date) {
		return toLocalDateTime(date, defaultZoneId());
	}

	public static final Optional<LocalDateTime> toLocalDateTimeOptional(Date date, ZoneId zoneId) {
		return toOptional(date, zoneId, ZonedDateTime::toLocalDateTime);
	}

	public static final LocalDateTime toLocalDateTime(Date date, ZoneId zoneId) {
		return toLocalDateTimeOptional(date, zoneId).orElse(null);
	}

	/*
	 * Date to LocalDate
	 */

	public static final Optional<LocalDate> toLocalDateOptional(Date date) {
		return toLocalDateOptional(date, defaultZoneId());
	}

	public static final LocalDate toLocalDate(Date date) {
		return toLocalDate(date, defaultZoneId());
	}

	public static final Optional<LocalDate> toLocalDateOptional(Date date, ZoneId zoneId) {
		return toOptional(date, zoneId, ZonedDateTime::toLocalDate);
	}

	public static final LocalDate toLocalDate(Date date, ZoneId zoneId) {
		return toLocalDateOptional(date, zoneId).orElse(null);
	}

	/*
	 * Date to LocalTime
	 */

	public static final Optional<LocalTime> toLocalTimeOptional(Date date) {
		return toLocalTimeOptional(date, defaultZoneId());
	}

	public static final LocalTime toLocalTime(Date date) {
		return toLocalTime(date, defaultZoneId());
	}

	public static final Optional<LocalTime> toLocalTimeOptional(Date date, ZoneId zoneId) {
		return toOptional(date, zoneId, ZonedDateTime::toLocalTime);
	}

	public static final LocalTime toLocalTime(Date date, ZoneId zoneId) {
		return toLocalTimeOptional(date, zoneId).orElse(null);
	}

	/*
	 * Date to generic T from ZonedDateTime
	 */

	public static final <T> Optional<T> toOptional(Date date, Function<ZonedDateTime, T> function) {
		return toOptional(date, defaultZoneId(), function);
	}

	public static final <T> T to(Date date, Function<ZonedDateTime, T> function) {
		return toOptional(date, function).orElse(null);
	}

	public static final <T> Optional<T> toOptional(Date date, ZoneId zoneId, Function<ZonedDateTime, T> function) {
		return Optional.ofNullable(date)
				.map((d) -> Dates.toZonedDateTime(date, fallback(zoneId)))
				.map(function);
	}

	public static final <T> T to(Date date, ZoneId zoneId, Function<ZonedDateTime, T> function) {
		return toOptional(date, zoneId, function).orElse(null);
	}

	private Dates() {
	}

}
