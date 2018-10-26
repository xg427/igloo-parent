package org.iglooproject.test.rest.jersey2.client;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.assertj.core.util.Lists;
import org.iglooproject.rest.client.exception.RestClientCommunicationException;
import org.iglooproject.rest.client.service.AbstractJsonRestClientService;
import org.iglooproject.rest.client.util.HttpHeaderBuilder;
import org.iglooproject.rest.client.util.RestClientServiceUtils;
import org.iglooproject.test.rest.jersey2.business.person.model.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PersonRestClientService extends AbstractJsonRestClientService {

	private static final ObjectMapper OBJECT_MAPPER;

	@Value("${jersey.mock.http.port}")
	private Integer httpPort;

	private static final int CLIENT_CONNECTION_REQUEST_TIMEOUT = 30 * 1000;
	private static final int CLIENT_CONNECT_TIMEOUT = 30 * 1000;
	private static final int CLIENT_SOCKET_TIMEOUT = 300 * 1000;

	static {
		OBJECT_MAPPER = new ObjectMapper();
	}

	public List<Person> listPersons() throws RestClientCommunicationException {
		CloseableHttpResponse httpResponse = null;
		
		try {
			String uri = new URIBuilder()
				.setScheme("http")
				.setHost("localhost")
				.setPort(httpPort)
				.setPath("/api/rest/person")
				.build()
				.toString();
			
			Map<String, String> headers = HttpHeaderBuilder.start()
				.connDirectiveClose()
				.build();
			
			httpResponse = executeGet(uri, headers);
			RestClientServiceUtils.checkSuccess(httpResponse);
			
			return deserializeAsList(
				IOUtils.toString(httpResponse.getEntity().getContent(), Charsets.UTF_8),
				Person.class
			);
		} catch (URISyntaxException e) {
			throw new RestClientCommunicationException("Error while calling list person REST service", e);
		} catch (IOException e) {
			throw new RestClientCommunicationException("Error while building list person URI", e);
		} finally {
			HttpClientUtils.closeQuietly(httpResponse);
		}
	}

	public Person getPerson(Long id) throws RestClientCommunicationException {
		CloseableHttpResponse httpResponse = null;
		
		try {
			String uri = new URIBuilder()
				.setScheme("http")
				.setHost("localhost")
				.setPort(httpPort)
				.setPath(String.format("/api/rest/person/%d", id))
				.build()
				.toString();
			
			Map<String, String> headers = HttpHeaderBuilder.start()
				.connDirectiveClose()
				.build();
			
			httpResponse = executeGet(uri, headers);
			RestClientServiceUtils.checkSuccess(httpResponse);
			
			return deserialize(
				IOUtils.toString(httpResponse.getEntity().getContent(), Charsets.UTF_8),
				Person.class
			);
		} catch (URISyntaxException e) {
			throw new RestClientCommunicationException("Error while calling get person REST service", e);
		} catch (IOException e) {
			throw new RestClientCommunicationException("Error while building get person URI", e);
		} finally {
			HttpClientUtils.closeQuietly(httpResponse);
		}
	}

	public Person createPerson(Person person) throws RestClientCommunicationException {
		CloseableHttpResponse httpResponse = null;
		
		try {
			String uri = new URIBuilder()
				.setScheme("http")
				.setHost("localhost")
				.setPort(httpPort)
				.setPath("/api/rest/person")
				.build()
				.toString();
			
			Map<String, String> headers = HttpHeaderBuilder.start()
				.connDirectiveClose()
				.contentTypeJson() // Matches the '@Consume("application/json")' on the server side
				.build();
			
			httpResponse = executePut(uri, toJsonEntity(serialize(person)), headers);
			RestClientServiceUtils.checkSuccess(httpResponse);
			
			return deserialize(
				IOUtils.toString(httpResponse.getEntity().getContent(), Charsets.UTF_8),
				Person.class
			);
		} catch (URISyntaxException e) {
			throw new RestClientCommunicationException("Error while calling create person REST service", e);
		} catch (IOException e) {
			throw new RestClientCommunicationException("Error while building create person URI", e);
		} finally {
			HttpClientUtils.closeQuietly(httpResponse);
		}
	}

	public Person updatePerson(Person person) throws RestClientCommunicationException {
		CloseableHttpResponse httpResponse = null;
		
		try {
			String uri = new URIBuilder()
				.setScheme("http")
				.setHost("localhost")
				.setPort(httpPort)
				.setPath(String.format("/api/rest/person/%d", person.getId()))
				.build()
				.toString();
			
			Map<String, String> headers = HttpHeaderBuilder.start()
				.connDirectiveClose()
				.contentTypeJson() // Matches the '@Consume("application/json")' on the server side
				.build();
			
			httpResponse = executePost(uri, toJsonEntity(serialize(person)), headers);
			RestClientServiceUtils.checkSuccess(httpResponse);
			
			return deserialize(
				IOUtils.toString(httpResponse.getEntity().getContent(), Charsets.UTF_8),
				Person.class
			);
		} catch (URISyntaxException e) {
			throw new RestClientCommunicationException("Error while calling update person REST service", e);
		} catch (IOException e) {
			throw new RestClientCommunicationException("Error while building update person URI", e);
		} finally {
			HttpClientUtils.closeQuietly(httpResponse);
		}
	}

	public void deletePerson(Long id) throws RestClientCommunicationException {
		CloseableHttpResponse httpResponse = null;
		
		try {
			String uri = new URIBuilder()
				.setScheme("http")
				.setHost("localhost")
				.setPort(httpPort)
				.setPath(String.format("/api/rest/person/%d", id))
				.build()
				.toString();
			
			Map<String, String> headers = HttpHeaderBuilder.start()
				.connDirectiveClose()
				.build();
			
			httpResponse = executeDelete(uri, headers);
			RestClientServiceUtils.checkSuccess(httpResponse);
		} catch (URISyntaxException e) {
			throw new RestClientCommunicationException("Error while calling delete person REST service", e);
		} catch (IOException e) {
			throw new RestClientCommunicationException("Error while building delete person URI", e);
		} finally {
			HttpClientUtils.closeQuietly(httpResponse);
		}
	}

	@Override
	protected CloseableHttpClient getDefaultHttpClient() {
		return buildHttpClient(CLIENT_CONNECTION_REQUEST_TIMEOUT, CLIENT_CONNECT_TIMEOUT, CLIENT_SOCKET_TIMEOUT,
			Lists.newArrayList());
	}

	@Override
	protected ObjectMapper getDefaultObjectMapper() {
		return OBJECT_MAPPER;
	}
}
