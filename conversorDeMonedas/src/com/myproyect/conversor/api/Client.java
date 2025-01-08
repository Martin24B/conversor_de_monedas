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
	private String resource; 
	private String parameters; 
	private String url;
	private String response;
	
	public Client () {
		this.resource = ApiConfig.RESOURCE;
		this.parameters = " ";
		this.response = " "; 
	}

	public void setResource (String resource) {
		this.resource += resource + "/"; 
	}
	
	public void setParameters (String parameters) {
		this.parameters += parameters + "/"; 
	}
	
	public void sendRequest () {
		this.url = (ApiConfig.HOST_SERVER + ApiConfig.API_KEY + this.resource + this.parameters).replace(" ", ""); 
		
		this.resource = "";
		this.parameters = "";
		
		try {
			URI uri = URI.create(this.url);
			
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
			System.out.println("Error inesperado: " + e.getMessage());
		}
	}
	
	public String getResponse () {
		return this.response;
	}
} 
