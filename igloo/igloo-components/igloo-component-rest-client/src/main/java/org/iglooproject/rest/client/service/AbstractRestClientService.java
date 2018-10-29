package org.iglooproject.rest.client.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.iglooproject.rest.client.util.HttpGetWithEntity;

import com.google.common.collect.ImmutableMap;

public abstract class AbstractRestClientService {

	/**
	 * Execute a <code>GET</code> HTTP request, with no headers and default client connection parameters
	 * 
	 * @param uri the targeted resource URI
	 * @return the HTTP response
	 * @throws IOException
	 */
	protected CloseableHttpResponse executeGet(String uri) throws IOException {
		return executeGet(uri, ImmutableMap.of());
	}

	/**
	 * Execute a <code>GET</code> HTTP request with default client connection parameters
	 * 
	 * @param uri the targeted resource URI
	 * @param headers a map containing request headers
	 * @return the HTTP response
	 * @throws IOException
	 */
	protected CloseableHttpResponse executeGet(String uri, Map<String, String> headers) throws IOException {
		return executeGet(uri, headers, getDefaultHttpClient());
	}

	/**
	 * Execute a <code>GET</code> HTTP request
	 * 
	 * @param uri the targeted resource URI
	 * @param headers a map containing request headers
	 * @param httpClient the client used to execute the request
	 * @return the HTTP response
	 * @throws IOException
	 */
	protected CloseableHttpResponse executeGet(String uri, Map<String, String> headers, CloseableHttpClient httpClient)
		throws IOException {
		return executeHttpRequest(new HttpGet(uri), headers, httpClient);
	}

	/**
	 * Execute a <code>GET</code> HTTP request, with no headers and default client connection parameters.
	 * An entity is sent in the request body
	 * 
	 * @param uri the targeted resource URI
	 * @param entity the entity sent with the request
	 * @return the HTTP response
	 * @throws IOException
	 */
	protected CloseableHttpResponse executeGetWithEntity(String uri, HttpEntity entity) throws IOException {
		return executeGetWithEntity(uri, entity, ImmutableMap.of());
	}

	/**
	 * Execute a <code>GET</code> HTTP request with default client connection parameters
	 * An entity is sent in the request body
	 * 
	 * @param uri the targeted resource URI
	 * @param entity the entity sent with the request
	 * @param headers a map containing request headers
	 * @return the HTTP response
	 * @throws IOException
	 */
	protected CloseableHttpResponse executeGetWithEntity(String uri, HttpEntity entity, Map<String, String> headers)
		throws IOException {
		return executeGetWithEntity(uri, entity, headers, getDefaultHttpClient());
	}

	/**
	 * Execute a <code>GET</code> HTTP request
	 * An entity is sent in the request body
	 * 
	 * @param uri the targeted resource URI
	 * @param entity the entity sent with the request
	 * @param headers a map containing request headers
	 * @param httpClient the client used to execute the request
	 * @return the HTTP response
	 * @throws IOException
	 */
	protected CloseableHttpResponse executeGetWithEntity(String uri, HttpEntity entity, Map<String, String> headers,
		CloseableHttpClient httpClient) throws IOException {
		HttpGetWithEntity httpGetWithEntity = new HttpGetWithEntity(uri);
		if (entity != null) {
			httpGetWithEntity.setEntity(entity);
		}
		
		return executeHttpRequest(httpGetWithEntity, headers, httpClient);
	}

	/**
	 * Execute a <code>POST</code> HTTP request, with no headers and default client connection parameters
	 * 
	 * @param uri the targeted resource URI
	 * @param entity the entity sent with the request
	 * @return the HTTP response
	 * @throws IOException
	 */
	protected CloseableHttpResponse executePost(String uri, HttpEntity entity) throws IOException {
		return executePost(uri, entity, ImmutableMap.of());
	}

	/**
	 * Execute a <code>POST</code> HTTP request with default client connection parameters
	 * 
	 * @param uri the targeted resource URI
	 * @param entity the entity sent with the request
	 * @param headers a map containing request headers
	 * @return the HTTP response
	 * @throws IOException
	 */
	protected CloseableHttpResponse executePost(String uri, HttpEntity entity, Map<String, String> headers)
		throws IOException {
		return executePost(uri, entity, headers, getDefaultHttpClient());
	}

	/**
	 * Execute a <code>POST</code> HTTP request
	 * 
	 * @param uri the targeted resource URI
	 * @param entity the entity sent with the request
	 * @param headers a map containing request headers
	 * @param httpClient the client used to execute the request
	 * @return the HTTP response
	 * @throws IOException
	 */
	protected CloseableHttpResponse executePost(String uri, HttpEntity entity, Map<String, String> headers,
		CloseableHttpClient httpClient) throws IOException {
		HttpPost httpPost = new HttpPost(uri);
		if (entity != null) {
			httpPost.setEntity(entity);
		}
		
		return executeHttpRequest(httpPost, headers, httpClient);
	}

	/**
	 * Execute a <code>PUT</code> HTTP request, with no headers and default client connection parameters
	 * 
	 * @param uri the targeted resource URI
	 * @param entity the entity sent with the request
	 * @return the HTTP response
	 * @throws IOException
	 */
	protected CloseableHttpResponse executePut(String uri, HttpEntity entity) throws IOException {
		return executePut(uri, entity, ImmutableMap.of());
	}

	/**
	 * Execute a <code>PUT</code> HTTP request with default client connection parameters
	 * 
	 * @param uri the targeted resource URI
	 * @param entity the entity sent with the request
	 * @param headers a map containing request headers
	 * @return the HTTP response
	 * @throws IOException
	 */
	protected CloseableHttpResponse executePut(String uri, HttpEntity entity, Map<String, String> headers)
		throws IOException {
		return executePut(uri, entity, headers, getDefaultHttpClient());
	}

	/**
	 * Execute a <code>PUT</code> HTTP request
	 * 
	 * @param uri the targeted resource URI
	 * @param entity the entity sent with the request
	 * @param headers a map containing request headers
	 * @param httpClient the client used to execute the request
	 * @return the HTTP response
	 * @throws IOException
	 */
	protected CloseableHttpResponse executePut(String uri, HttpEntity entity, Map<String, String> headers,
		CloseableHttpClient httpClient) throws IOException {
		HttpPut httpPut = new HttpPut(uri);
		if (entity != null) {
			httpPut.setEntity(entity);
		}
		
		return executeHttpRequest(httpPut, headers, httpClient);
	}

	/**
	 * Execute a <code>DELETE</code> HTTP request, with no headers and default client connection parameters
	 * 
	 * @param uri the targeted resource URI
	 * @return the HTTP response
	 * @throws IOException
	 */
	protected CloseableHttpResponse executeDelete(String uri) throws IOException {
		return executeDelete(uri, ImmutableMap.of());
	}


	/**
	 * Execute a <code>DELETE</code> HTTP request with default client connection parameters
	 * 
	 * @param uri the targeted resource URI
	 * @param headers a map containing request headers
	 * @return the HTTP response
	 * @throws IOException
	 */
	protected CloseableHttpResponse executeDelete(String uri, Map<String, String> headers) throws IOException {
		return executeDelete(uri, headers, getDefaultHttpClient());
	}

	/**
	 * Execute a <code>DELETE</code> HTTP request
	 * 
	 * @param uri the targeted resource URI
	 * @param headers a map containing request headers
	 * @param httpClient the client used to execute the request
	 * @return the HTTP response
	 * @throws IOException
	 */
	protected CloseableHttpResponse executeDelete(String uri, Map<String, String> headers,
		CloseableHttpClient httpClient) throws IOException {
		return executeHttpRequest(new HttpDelete(uri), headers, httpClient);
	}

	/**
	 * Execute a HTTP request
	 * 
	 * @param requestBase the request to execute
	 * @param headers a map containing request headers
	 * @param httpClient the client used to execute the request
	 * @return the HTTP response
	 * @throws IOException
	 */
	protected CloseableHttpResponse executeHttpRequest(HttpRequestBase requestBase, Map<String, String> headers,
		CloseableHttpClient httpClient) throws IOException {
		
		for (Entry<String, String> header : headers.entrySet()) {
			requestBase.addHeader(header.getKey(), header.getValue());
		}
		
		return httpClient.execute(requestBase);
	}

	/**
	 * Build an HTTP client with default connection parameters
	 * @return the HTTP client
	 */
	protected abstract CloseableHttpClient getDefaultHttpClient();

	/**
	 * Build an HTTP client with custom connection parameters
	 * @param connectionRequestTimeout timeout for connection request in ms
	 * @param connectTimeout timeout for connect operation in ms
	 * @param socketTimeout timeout on request duration in ms
	 * @param cookies a list of {@link Cookie} to send with the request
	 * @return the HTTP client
	 */
	protected CloseableHttpClient buildHttpClient(int connectionRequestTimeout, int connectTimeout,
		int socketTimeout, List<Cookie> cookies) {
		HttpClientBuilder builder = HttpClientBuilder.create()
			.setDefaultRequestConfig(
				RequestConfig.custom()
					.setConnectionRequestTimeout(connectionRequestTimeout)
					.setConnectTimeout(connectTimeout)
					.setSocketTimeout(socketTimeout)
					.build()
			);
		
		// Adding cookies if need be
		if (!cookies.isEmpty()) {
			CookieStore cookieStore = new BasicCookieStore();
			for (Cookie cookie : cookies) {
				cookieStore.addCookie(cookie);
			}
			builder.setDefaultCookieStore(cookieStore);
		}
		
		return builder.build();
	}
}
