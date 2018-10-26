package org.iglooproject.rest.client.util;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.Args;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class RestClientJsonUtils {

	public static <T> T deserialize(String json, Class<T> beanClass, ObjectMapper objectMapper) throws IOException {
		return deserializeWithJavaType(json, objectMapper.getTypeFactory().constructType(beanClass), objectMapper);
	}

	public static <T> List<T> deserializeAsList(String json, Class<T> beanClass, ObjectMapper objectMapper)
		throws IOException {
		return deserializeWithJavaType(json,
			objectMapper.getTypeFactory().constructCollectionType(List.class, beanClass), objectMapper);
	}

	public static <K, V> Map<K, V> deserializeAsMap(String json, Class<K> keyClass, Class<V> valueClass,
		ObjectMapper objectMapper) throws IOException {
		JavaType mapType = objectMapper.getTypeFactory().constructMapType(
			LinkedHashMap.class,
			objectMapper.constructType(keyClass),
			objectMapper.constructType(valueClass)
		);
		return deserializeWithJavaType(json, mapType, objectMapper);
	}

	public static <T> T deserializeWithJavaType(String json, JavaType type, ObjectMapper objectMapper)
		throws IOException {
		if (!StringUtils.isNotEmpty(json)) {
			throw new IOException("Error on JSON deserialization : JSON is empty");
		}
		
		try {
			return objectMapper.readerFor(type).readValue(json);
		} catch (IOException e) {
			throw new IOException("Error while deserializing JSON bean of type " + type.getTypeName(), e);
		}
	}

	public static String serialize(Object serilizableBean, ObjectMapper objectMapper) throws IOException {
		return serialize(serilizableBean, null, objectMapper);
	}

	public static String serialize(Object serilizableBean, Class<?> viewClass, ObjectMapper objectMapper)
		throws IOException {
		Args.notNull(serilizableBean, "serilizableBean");
		try {
			if (viewClass != null) {
				return objectMapper.writerWithView(viewClass).writeValueAsString(serilizableBean);
			} else {
				return objectMapper.writeValueAsString(serilizableBean);
			}
		} catch (JsonProcessingException e) {
			throw new IOException(
				"Error while serializing JSON bean of type " + serilizableBean.getClass().getSimpleName(), e);
		}
	}

	private RestClientJsonUtils() { }
}
