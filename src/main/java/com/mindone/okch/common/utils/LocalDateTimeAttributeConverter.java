package com.mindone.okch.common.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime locDateTime) {
		//System.out.println("convertToDatabaseColumn");
		return locDateTime == null ? null : Timestamp.valueOf(locDateTime);
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp sqlTimestamp) {
		//System.out.println("convertToEntityAttribute");
		return sqlTimestamp == null ? null : sqlTimestamp.toLocalDateTime();
	}
}