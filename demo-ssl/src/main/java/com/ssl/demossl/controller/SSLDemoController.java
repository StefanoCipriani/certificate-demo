package com.ssl.demossl.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SSLDemoController {

	@GetMapping("/ping")
	public String ping() { 
		return "pong";
	}
	
	//This won't work because we don't have tacos certificate in the truststore
	@GetMapping("/tacosNotWork")
	public String tacosNotWork() throws UnsupportedOperationException, IOException { 
		String url = "https://localhost:8443/tacos";
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		
		HttpResponse response = client.execute(request);
		
		BufferedReader rd = new BufferedReader( new InputStreamReader(response.getEntity().getContent()));
		
		StringBuffer result = new StringBuffer();
		String line="";
		while((line = rd.readLine()) != null) { 
			result.append(line);
		}
		return result.toString();
	}
	
	
	@SuppressWarnings("static-access")
	@GetMapping("/tacos")
	public String tacos() throws UnsupportedOperationException, IOException { 
		SSLSocketFactory  sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		URL url = new URL("https://localhost:8443/tacos");
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setDefaultHostnameVerifier(new HostnameVerifier() {

			@Override
			public boolean verify(String hostname, SSLSession session) {
				if(hostname.equals("localhost")) { 
					return true;
				}
				return false;
			}
			
		});
		
		conn.setSSLSocketFactory(sslSocketFactory);
		
		BufferedReader rd = new BufferedReader( new InputStreamReader(conn.getInputStream()));
		
		StringBuffer result = new StringBuffer();
		String line="";
		while((line = rd.readLine()) != null) { 
			result.append(line);
		}
		return result.toString();
	}
}
