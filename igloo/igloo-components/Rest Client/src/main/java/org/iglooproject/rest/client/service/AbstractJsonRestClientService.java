package org.iglooproject.rest.client.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.iglooproject.rest.client.util.RestClientJsonUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractJsonRestClientService extends AbstractRestClientService {

	protected abstract ObjectMapper getDefaultObjectMapper();

	protected <T> T deserialize(String json, Class<T> beanClass) throws IOException {
		return RestClientJsonUtils.deserialize(json, beanClass, getDefaultObjectMapper());
	}

	protected <T> List<T> deserializeAsList(String json, Class<T> beanClass)
		throws IOException {
		return RestClientJsonUtils.deserializeAsList(json, beanClass, getDefaultObjectMapper());
	}

	protected <K, V> Map<K, V> deserializeAsMap(String json, Class<K> keyClass, Class<V> valueClass)
		throws IOException {
		return RestClientJsonUtils.deserializeAsMap(json, keyClass, valueClass, getDefaultObjectMapper());
	}

	protected String serialize(Object serilizableBean) throws IOException {
		return RestClientJsonUtils.serialize(serilizableBean, getDefaultObjectMapper());
	}

	protected String serialize(Object serilizableBean, Class<?> viewClass)
		throws IOException {
		return RestClientJsonUtils.serialize(serilizableBean, viewClass, getDefaultObjectMapper());
	}

	protected static final HttpEntity toJsonEntity(String json) {
		return new StringEntity(json, ContentType.APPLICATION_JSON);
	}
}
