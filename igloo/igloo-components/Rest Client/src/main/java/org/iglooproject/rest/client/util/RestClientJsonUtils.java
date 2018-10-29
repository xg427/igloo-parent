package org.iglooproject.rest.client.util;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.Args;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class RestClientJsonUtils {

	/**
	 * Serialize an object into a JSON string.
	 * 
	 * @param serilizableBean the object to serialize
	 * @param objectMapper the {@link ObjectMapper} used for serialization
	 * @return a valid JSON string representing the given object
	 * @throws IOException
	 */
	public static String serialize(Object serilizableBean, ObjectMapper objectMapper) throws IOException {
		return serialize(serilizableBean, null, objectMapper);
	}

	/**
	 * Serialize an object into a JSON string using a specific Jackson {@link JsonView}.
	 * 
	 * @param serilizableBean the object to serialize
	 * @param viewClass the view class to use
	 * @param objectMapper the {@link ObjectMapper} used for serialization
	 * @return a valid JSON string representing the given object in the given view
	 * @throws IOException
	 */
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

	/**
	 * Deserialize a valid JSON string into a corresponding object.
	 * 
	 * @param json the valid JSON string
	 * @param beanClass the targeted object class
	 * @param objectMapper the {@link ObjectMapper} used for deserialization
	 * @return the deserialized objects
	 * @throws IOException
	 */
	public static <T> T deserialize(String json, Class<T> beanClass, ObjectMapper objectMapper) throws IOException {
		return deserializeWithJavaType(json, objectMapper.getTypeFactory().constructType(beanClass), objectMapper);
	}

	/**
	 * Deserialize a valid JSON string into a list of corresponding object.
	 * 
	 * @param json the valid JSON string representing a list
	 * @param beanClass the targeted object class contained in the list
	 * @param objectMapper the {@link ObjectMapper} used for deserialization
	 * @return the deserialized list of objects
	 * @throws IOException
	 */
	public static <T> List<T> deserializeAsList(String json, Class<T> beanClass, ObjectMapper objectMapper)
		throws IOException {
		return deserializeWithJavaType(json,
			objectMapper.getTypeFactory().constructCollectionType(List.class, beanClass), objectMapper);
	}

	/**
	 * Deserialize a valid JSON string into a map of corresponding key/value objects.
	 * 
	 * @param json the valid JSON string representing a map
	 * @param keyClass the targeted key object class
	 * @param valueClass the targeted value object class
	 * @param objectMapper the {@link ObjectMapper} used for deserialization
	 * @return the deserialized map of objects
	 * @throws IOException
	 */
	public static <K, V> Map<K, V> deserializeAsMap(String json, Class<K> keyClass, Class<V> valueClass,
		ObjectMapper objectMapper) throws IOException {
		JavaType mapType = objectMapper.getTypeFactory().constructMapType(
			LinkedHashMap.class,
			objectMapper.constructType(keyClass),
			objectMapper.constructType(valueClass)
		);
		return deserializeWithJavaType(json, mapType, objectMapper);
	}

	/**
	 * Deserialize a valid JSON string into an object.
	 * 
	 * @param json the valid JSON string representing a map
	 * @param type the {@link JavaType} to deserialize to
	 * @return the deserialized object
	 * @throws IOException
	 */
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

	private RestClientJsonUtils() { }
}
