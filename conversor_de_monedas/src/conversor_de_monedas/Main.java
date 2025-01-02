package conversor_de_monedas;

import java.util.Scanner;

/*
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import java.net.URI;

import java.time.Duration; 

import api.ApiConfig;
*/

import api.Resource;


public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		Client client = new Client ();
		
		Resource.printAllDescriptions();
		
		System.out.println ("\nIngrese el numero correspondiente al recurso solicitado:");
		enterResource (scanner, client);
		
	/*
		final String URL = ApiConfig.HOST_SERVER + ApiConfig.API_KEY + ApiConfig.RESOURCE; 
		
		System.out.println ("Mensaje de bienvenida y descripcion breve del proyecto");
		
		
		
		System.out.println ("Se selecciono codes. Cargando...");
		
		String body_json = sendRequest (URL);
		Coins coins_list = new Coins (body_json); 
		System.out.println(coins_list.toString()); 
		
		System.out.println ("Listos para imprimir funcionalidades dependiendo del recurso elegido");
		
	}
	
	public static String sendRequest (String URL) {	
		String json_response = ""; 
		
		HttpRequest request = HttpRequest.newBuilder()
				.GET ()
				.uri(URI.create(URL))
				.timeout(Duration.ofSeconds(ApiConfig.MAX_TIME_REQUEST))	 
				.build (); 
		 
		HttpClient client = HttpClient.newBuilder()
				.version (Version.HTTP_2)
				.followRedirects (Redirect.NEVER)
				.connectTimeout(Duration.ofSeconds(ApiConfig.MAX_TIME_CONNECT))
				.build ();
	
		try {
			HttpResponse <String> response = client.send(request, BodyHandlers.ofString ()); 
			json_response = response.body();
			
		} catch (Exception exc) {
			
		}
		
		return json_response;
	}
	*/
	}
	
	public static void enterResource (Scanner scanner, Client client) {
		int option = 0;
		
		while (!(option > 0 && option < 5)) {
			if (scanner.hasNextInt()) { 
				option = scanner.nextInt(); 
				
			if (option > 0 && option < 5) {
				System.out.println("\nOpcion elegida: " + Resource.getResource (option));	
				client.setResource(Resource.getResource (option));
				
				if (option == 1 || option == 3) {
					Resource.listOfRequest (client.getResource());
					enterRequest (scanner, client, option);
				}
				
			} else {
				System.out.println("Opcion incorrecta, el valor numerico debe ser entre 1 y 5.\nIntente nuevamente...");				
			  }	
			
			} else {
				System.out.println("Error de entrada, el valor ingresado debe ser un numero entero.\nIntente nuevamente...");     				
				scanner.next();
			}
		}	
	
		scanner.close(); 
	}
	
	
	public static void enterRequest (Scanner scanner, Client client, int optionOfResource) {
		int optionOfRequest = 0;
		
		while (!(optionOfRequest > 0 && optionOfRequest < 3)) {
			if (scanner.hasNextInt()) { 
				optionOfRequest = scanner.nextInt(); 
					
				if (optionOfRequest > 0 && optionOfRequest < 3) { 
					enterParameters (scanner, client, optionOfResource, optionOfRequest);
					
				} else {
					System.out.println("Opcion incorrecta, el valor numerico debe ser entre 1 y 5.\nIntente nuevamente...");				
				}	
			} else {
				System.out.println("Error de entrada, el valor ingresado debe ser un numero entero.\nIntente nuevamente...");     				
				scanner.next();
			}
		}	
	
		scanner.close(); 
	}
	
	public static void enterParameters (Scanner scanner, Client client, int optionOfResource, int optionOfRequest) {
		String parameters = ""; 
		
		switch (optionOfResource) {
			case 1: 
				if (optionOfRequest == 1) {
				//si eligio 1 es un solo parametro (codigo de moneda)
				} else if (optionOfRequest == 2) {
				//si eligio 2 son dos parametros (codigo de moneda, monto)
				}
			break; 
			
			case 2: 
				if (optionOfRequest == 1) {
					// si eligio 1 son dos parametros (codigo 1, codigo 2)
				} else if (optionOfRequest == 2) {
					// si eligio 2 son tres parametros (codigo1, codigo2, monto)
				}
			break;
		}
	}
}
