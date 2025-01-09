package com.myproyect.conversor.api;

import java.net.http.HttpRequest;

import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import java.net.URI;

import java.time.Duration; 

public class Client { 
	private String endpoint;
	private String response;
	
	public Client () {
		this.endpoint = ApiConfig.RESOURCE;
		this.response = " "; 
	}
	
	public void setUri (String uri) {
		this.endpoint = uri; 
	}
	
	public void sendRequest () {
		String url = ApiConfig.HOST_SERVER + ApiConfig.API_KEY + this.endpoint; 
		
		try {
			URI uri = URI.create(url);
			
			HttpRequest request = HttpRequest.newBuilder()
				.GET ()
				.uri(uri)
				.timeout(Duration.ofSeconds(ApiConfig.MAX_TIME_REQUEST))	 
				.build (); 
			
			HttpClient client = HttpClient.newBuilder()
				.version (Version.HTTP_2)
				.followRedirects (Redirect.NEVER)
				.connectTimeout(Duration.ofSeconds(ApiConfig.MAX_TIME_CONNECT))
				.build ();
			
			
				HttpResponse <String> httpResponse = client.send(request, BodyHandlers.ofString ()); 
				this.response = httpResponse.body();
			
		} catch (IllegalArgumentException e) {
				System.out.println("Error de sintaxis en la URL: " + e.getMessage());
		} catch (InterruptedException e) {
			System.out.println("Error de conexión: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error inesperado, verifique su conexión a internet: " + e.getMessage());
		}
	}
	
	public String getResponse () {
		return this.response;
	}
} 
