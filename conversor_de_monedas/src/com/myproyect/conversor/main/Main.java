package com.myproyect.conversor.main;

import java.util.Scanner;

import com.myproyect.conversor.api.*;
import com.myproyect.conversor.data.*;

public class Main {
	private static final char CONTINUE_EXECUTE = '1';
	private static final char EXIT_EXECUTE = '0';
	private static final char SEP_URI = '/'; 
	
	public static void main (String [] args) {
		Scanner scanner = new Scanner (System.in);
		Client client = new Client ();
		
		System.out.println ("Iniciando aplicación...\n");
		client.sendRequest(); 
		Coins coins = new Coins (client.getResponse());
		FileWrite.printCodeCoins(coins.printResponse());
		
		boolean execute = true;
		
		while (execute) {
			String resource = enterResource (scanner);
			String parameters = enterParameters (scanner, resource); 
			String response = sendRequest (client, resource, parameters);
			
			FileWrite.printResponse(resource, response);
			
			execute = continueExecution (scanner);
		}
		scanner.close();
	}

	private static String enterResource (Scanner scanner) {
		String resource = ""; 
		System.out.println(Resource.instruction());
		System.out.println(Resource.printAllDescriptions());
		
		while (true) {
			resource = scanner.next().toLowerCase(); 
			
			if (Resource.resourceValid(resource)) {
				return resource.toLowerCase(); 
			} else {
				System.out.println ("El recurso solicitado no corresponde a ninguno de los publicados, intente nuevamente...");
				scanner.nextLine(); 
			}
		}
	}
	
	private static String enterParameters (Scanner scanner, String resource) {
		String parameters = ""; 
		
		if (Resource.posResource(resource) == 1 || Resource.posResource(resource) == 3) {
			System.out.println ("Visite el archivo 'codeCoins.txt' dentro de la carpeta 'userData' para conocer todas las monedas que estan actualmente activas en el mercado.\n");
			if (Resource.posResource(resource) == 1) {
				System.out.println ("Ingrese el codigo de la moneda que desea evaluar.");
				String codeCoin = enterCodeCoin (scanner);
				parameters = codeCoin;
				return parameters; 
				
			} else if (Resource.posResource(resource) == 3) {
				System.out.println (Resource.listOfRequest(resource));
				String option = enterOption (scanner);
				
				System.out.println ("Ingrese el codigo de la primer moneda que desea evaluar.");
				String codeCoin_1 = enterCodeCoin (scanner);
				System.out.println ("Ingrese el codigo de la segunda moneda que desea evaluar.");
				String codeCoin_2 = enterCodeCoin (scanner);
				
				if (option.equals("2")) {
					System.out.println ("Ingrese el monto que desea evaluar.");
					String amount = enterAmount (scanner);
					parameters = codeCoin_1 + SEP_URI + codeCoin_2 + SEP_URI + amount; 
					return parameters; 
				}
				parameters = codeCoin_1 + SEP_URI + codeCoin_2;
				return parameters; 
			}
		}
		
		return parameters; 
	}
	
	private static String enterOption (Scanner scanner) {
		String option = "";
		
		while (true) {
			option = scanner.next(); 
			
			if (Resource.optionValid(option)) {
				return option; 
			} else {
				System.out.println ("El recurso solicitado no corresponde a ninguno de los publicados, intente nuevamente...");
				scanner.next(); 
			}
		}
	}
	
	private static String enterCodeCoin (Scanner scanner) {
		String codeCoin = "";
		
		while (true) {
			codeCoin = scanner.next().toUpperCase(); 
			
			if (Coins.codeCoinValid(codeCoin)) {
				return codeCoin; 
			} else {
				System.out.println ("El recurso solicitado no corresponde a ninguno de los publicados, intente nuevamente...");
				scanner.nextLine(); 
			}
		}
	}
	
	private static String sendRequest(Client client, String resource, String parameters) {
		String response = ""; 
		
		client.setUri(parameters);
				
		if (Resource.posResource(resource) == 1) {
			client.setUri(resource + SEP_URI + parameters);
			client.sendRequest();
			Convertion convertion = new Convertion (client.getResponse());	
			System.out.println (successfulRequest ());
			response = convertion.printAllExchange();	
			
			return response;
		} else if (Resource.posResource(resource) == 2) {
			System.out.println (successfulRequest ());
			System.out.println ("Visite el archivo 'codeCoins.txt' dentro de la carpeta 'userData' para conocer todas las monedas que estan actualmente activas en el mercado.\n");
		
		} else if (Resource.posResource(resource) == 3) {
			client.setUri(resource + SEP_URI + parameters);
			client.sendRequest();
			
			if (countParameters (parameters) == 1) {
				Convertion convertion = new Convertion (client.getResponse(), 1);	
				System.out.println (successfulRequest ());
				response = convertion.printExchange();	
				
				return response;
			} else if (countParameters (parameters) == 2) {
				Convertion convertion = new Convertion (client.getResponse(), 2);	
				System.out.println (successfulRequest ());
				response = (convertion.printExchange() + convertion.convertion());	
				
				return response;
			}
			
		} else if (Resource.posResource(resource) == 4) {
			client.setUri(resource);
			client.sendRequest();
			Quota quota = new Quota (client.getResponse()); 
			System.out.println (successfulRequest ());
			response = quota.printResponse();
		
			return response;
		}
		
		return response;
	}
	
	private static String enterAmount (Scanner scanner) {
		double amount = 0;
		
		while (true) {
			if (scanner.hasNextDouble()) {
				amount = scanner.nextDouble(); 
				return String.valueOf((int)amount); 
			} else {
				System.out.println ("El valor ingresado debe ser numérico, intente nuevamente...");
				scanner.nextLine(); 
			}
		}
	}
	
	private static boolean continueExecution(Scanner scanner) {
	    while (true) {
	        System.out.println("Ingrese '" + CONTINUE_EXECUTE + "' para seguir operando, y '" + EXIT_EXECUTE + "' para finalizar\n");
	        char execution = scanner.next().charAt(0); 

	        if (execution == CONTINUE_EXECUTE) {
	            return true; 
	        } else if (execution == (EXIT_EXECUTE)) {
	            return false; 
	        } else {
	            System.out.println("Entrada no válida. Por favor ingrese " + CONTINUE_EXECUTE + " para continuar o " + EXIT_EXECUTE + " para finalizar.\n"); 
	            scanner.nextLine();
	        }
	    }
	}
	
	private static String successfulRequest () {
		return "\nSolicitud enviada con exito, visite el archivo userData/response.txt para ver los resultados obtenidos:\n";						
	}
	
	
	private static int countParameters (String parameters) {
		int count = 0;
		
		for (int i = 0; i < parameters.length(); i++) {
			if (parameters.charAt(i) == SEP_URI) {
				count++;
			}
		} 
		
		return count;
	}
}