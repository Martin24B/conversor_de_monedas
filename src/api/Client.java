package api;

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
	}

	public void setResource (String resource) {
		this.resource += resource + "/"; 
	}
	
	public String getResource () {
		return this.resource; 
	}
	
	public void setParameters (String parameters) {
		this.parameters += parameters + "/"; 
	}
	
	public String getParameters () {
		return this.parameters; 
	}
	
	public void sendRequest () {
		this.url = ApiConfig.HOST_SERVER + ApiConfig.API_KEY + this.resource + this.parameters; 
		
		HttpRequest request = HttpRequest.newBuilder()
			.GET ()
			.uri(URI.create(this.url))
			.timeout(Duration.ofSeconds(ApiConfig.MAX_TIME_REQUEST))	 
			.build (); 
		
		HttpClient client = HttpClient.newBuilder()
			.version (Version.HTTP_2)
			.followRedirects (Redirect.NEVER)
			.connectTimeout(Duration.ofSeconds(ApiConfig.MAX_TIME_CONNECT))
			.build ();
		
		try {
			HttpResponse <String> response = client.send(request, BodyHandlers.ofString ()); 
			this.response = response.body();
			
		} catch (Exception exc) {
			System.out.println ("Error de conexion " + exc);
		}
	}
	
	public String getResponse () {
		return this.response;
	}
} 
