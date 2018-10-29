package org.iglooproject.rest.client.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.iglooproject.rest.client.util.RestClientJsonUtils;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractJsonRestClientService extends AbstractRestClientService {

	protected abstract ObjectMapper getDefaultObjectMapper();

	/**
	 * Serialize an object into a JSON string.<br/>
	 * Uses the {@link ObjectMapper} given by {@link getDefaultObjectMapper()}
	 * 
	 * @param serilizableBean the object to serialize
	 * @return a valid JSON string representing the given object
	 * @throws IOException
	 */
	protected String serialize(Object serilizableBean) throws IOException {
		return RestClientJsonUtils.serialize(serilizableBean, getDefaultObjectMapper());
	}

	/**
	 * Serialize an object into a JSON string using a specific Jackson {@link JsonView}.<br/>
	 * Uses the {@link ObjectMapper} given by {@link getDefaultObjectMapper()}
	 * 
	 * @param serilizableBean the object to serialize
	 * @param viewClass the view class to use
	 * @return a valid JSON string representing the given object in the given view
	 * @throws IOException
	 */
	protected String serialize(Object serilizableBean, Class<?> viewClass)
		throws IOException {
		return RestClientJsonUtils.serialize(serilizableBean, viewClass, getDefaultObjectMapper());
	}

	/**
	 * Deserialize a valid JSON string into a corresponding object.<br/>
	 * Uses the {@link ObjectMapper} given by {@link getDefaultObjectMapper()}
	 * 
	 * @param json the valid JSON string
	 * @param beanClass the targeted object class
	 * @return the deserialized objects
	 * @throws IOException
	 */
	protected <T> T deserialize(String json, Class<T> beanClass) throws IOException {
		return RestClientJsonUtils.deserialize(json, beanClass, getDefaultObjectMapper());
	}

	/**
	 * Deserialize a valid JSON string into a list of corresponding object.<br/>
	 * Uses the {@link ObjectMapper} given by {@link getDefaultObjectMapper()}
	 * 
	 * @param json the valid JSON string representing a list
	 * @param beanClass the targeted object class contained in the list
	 * @return the deserialized list of objects
	 * @throws IOException
	 */
	protected <T> List<T> deserializeAsList(String json, Class<T> beanClass)
		throws IOException {
		return RestClientJsonUtils.deserializeAsList(json, beanClass, getDefaultObjectMapper());
	}

	/**
	 * Deserialize a valid JSON string into a map of corresponding key/value objects.<br/>
	 * Uses the {@link ObjectMapper} given by {@link getDefaultObjectMapper()}
	 * 
	 * @param json the valid JSON string representing a map
	 * @param keyClass the targeted key object class
	 * @param valueClass the targeted value object class
	 * @return the deserialized map of objects
	 * @throws IOException
	 */
	protected <K, V> Map<K, V> deserializeAsMap(String json, Class<K> keyClass, Class<V> valueClass)
		throws IOException {
		return RestClientJsonUtils.deserializeAsMap(json, keyClass, valueClass, getDefaultObjectMapper());
	}

	/**
	 * Turns a string into a string {@link HttpEntity} with JSON content type.
	 */
	protected static final HttpEntity toJsonEntity(String json) {
		return new StringEntity(json, ContentType.APPLICATION_JSON);
	}
}
