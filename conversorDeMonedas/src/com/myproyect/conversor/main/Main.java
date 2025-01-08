package com.myproyect.conversor.main;

import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

import com.myproyect.conversor.api.*;
import com.myproyect.conversor.data.*;

public class Main {
	private static final String CONTINUE_EXECUTE = "1";
	private static final String EXIT_EXECUTE = "0";
	
	public static void main (String [] args) {
		Scanner scanner = new Scanner (System.in);
		
		Client client = new Client ();
		client.sendRequest(); 
		
		Coins coins = new Coins (client.getResponse());
		printCodeCoins (coins.printResponse());
		
		boolean execute = true;
		
		while (execute) {
			String instruction = instruction ();	
			System.out.println (instruction);
			
			String resource = enterResource (scanner, client);
			System.out.println("\nOpcion elegida: " + resource + " \ncargando...\n");		
			int optionRequest = enterRequest (scanner, resource);
		
			String response = sendRequest (scanner, client, coins, resource, optionRequest);
			printResponse (resource, response);
			
			execute = continueExecution (scanner);
		}
		
		scanner.close();
	}

	public static String enterResource (Scanner scanner, Client client) {
		String resource = " "; 
		
		while (true) {
			resource = scanner.next().toLowerCase(); 
			
			if (Resource.isValid(resource)) {
				client.setResource(resource);
				return resource; 
			} else {
				System.out.println (invalidValue ());
				scanner.nextLine(); 
			}
		}
	}
	
	public static int enterRequest (Scanner scanner, String resource) {
		int optionRequest = 0;
		
		if (!(Resource.listOfRequest(resource).isEmpty())) {
			System.out.println (Resource.listOfRequest(resource));
			
			while (!(optionRequest > 0 && optionRequest < 3)) {				
				if (scanner.hasNextInt()) { 
					optionRequest = scanner.nextInt(); 
					
					if (optionRequest > 0 && optionRequest < 3) { 
						return optionRequest; 
					} else {
						System.out.println (invalidValue ());
						scanner.nextLine();
					}
				}
			}
		}
		
		return optionRequest;
	}
	
	public static String sendRequest (Scanner scanner, Client client,  Coins coins, String resource, int optionRequest) {
		String response = "";
		
		if (Resource.posResource(resource) == 1 || Resource.posResource(resource) == 3) {
			response = enterParameters (scanner, client, coins, resource, optionRequest);
			return response; 
	
		} else if (Resource.posResource(resource) == 2) {
			System.out.println (requestCodeCoins ());
		} else if (Resource.posResource(resource) == 4) {
			client.sendRequest();
			Quota quota = new Quota (client.getResponse()); 
			System.out.println (successfulRequest ());
			response = quota.printResponse();
			
			return response;
		}
		
		return response; 
	}
	
	public static String enterParameters (Scanner scanner, Client client, Coins coins, String resource, int optionRequest) {
		String parameters = "";
		String response = "";
		String requestCode = "Ingrese el codigo de la moneda que desea evaluar.";
		
		if (Resource.posResource(resource) == 1) {
			String coin = " ";	
			System.out.println (requestCodeCoins ());
			System.out.println (requestCode);
			
			while (!(coins.codeCoins_isValid(coin))) {
				coin = scanner.next().toUpperCase();
				
				if (coins.codeCoins_isValid(coin)) {
					parameters = coin; 
					client.setParameters(parameters);
					client.sendRequest();
					
					Convertion convertion = new Convertion (client.getResponse());	
					System.out.println (successfulRequest ());
					response = convertion.printAllExchange();	
					
					return response;  
					
				} else {
					System.out.println (invalidValue ());
					scanner.nextLine();
				}
			}
		} else if (Resource.posResource(resource) == 3) {
			String coin1 = " ";
			String coin2 = " ";
			
			System.out.println (requestCodeCoins ());
			
			while (!(coins.codeCoins_isValid(coin1) && coins.codeCoins_isValid(coin2))) {	
				System.out.println (requestCode);
				coin1 = scanner.next();
			
				System.out.println (requestCode);
				coin2 = scanner.next();
				
				if (coins.codeCoins_isValid(coin1) && coins.codeCoins_isValid(coin2)) {
					parameters += coin1 + "/" + coin2;
					
					if (optionRequest == 1) {			
						System.out.println ("cargando...");
						
						client.setParameters(parameters);		
						client.sendRequest();
						
						Convertion convertion = new Convertion (client.getResponse());
						System.out.println (successfulRequest ());
						response = convertion.printExchange();	
						return response;
						
					} else if (optionRequest == 2) {
						double amount = 0;
					
						while (amount == 0) {
							System.out.println ("Ingrese el monto que desea convertir.");
						
							if (scanner.hasNextDouble ()) {	
								amount = scanner.nextDouble ();
								parameters +=  "/" + String.valueOf((int) amount);
								
								client.setParameters(parameters);
								client.sendRequest();
							
								Convertion convertion = new Convertion (client.getResponse(), amount);	
								System.out.println (successfulRequest ());
								response = convertion.printExchange() + convertion.convertion();	
							
								return response; 
								
							} else {
								System.out.println(invalidValue ());
								scanner.nextLine();
							  }
						}
					  }
				} else {
					System.out.println(invalidValue ());
					scanner.nextLine();
				  }	
			}	
		}
		
		return response; 
	}
	
	//Mensajes para el usuario
	
	public static String instruction () {
        String message = """
            BIENVENIDOS AL CONVERSOR DE MONEDAS EN TIEMPO REAL MAS COMPLETO DEL MERCADO!\n
            En este conversor de monedas el usuario dispone de una gran variedad de información relacionada con el mercado actual como por ejemplo tasas de cambio, conversiones monetarias, actividad económica de cada moneda, etc.\n
            IMPORTANTE!\n
            \tSi es su primera vez utilizando el conversor de monedas se recomienda elegir el recurso CODES o visitar el archivo Readme.md para mantenerse informado sobre las funcionalidades disponibles de la App.\n
            \tSi desea realizar múltiples operaciones se recomienza utilizar el recurso Quota para consultar el limite disponible y evitar bloqueos inesperados.\n
            MUCHAS GRACIAS POR SU VISITA, QUE LO DISFRUTE!\n
            __________________________________________________________________________________________________________________________________________________________________________________________________________________________\n
            Para comenzar a operar seleccione la opción correspondiente al recurso que desea solicitar:\n
            """ 
            + Resource.printAllDescriptions();
  
        return message; 
	}
	
	public static String invalidValue () {
		return "Opción incorrecta, intente nuevamente...";
	}
	
	public static String successfulRequest () {
		return "\nSolicitud enviada con exito, visite el archivo userData/response.txt para ver los resultados obtenidos:\n";						
	}
	
	public static String requestCodeCoins () {
		return "\nVisite el archivo userData/codeCoins.txt para ver los codigos de identificación de cada moneda del mundo:\n";
	}
	
	public static void printCodeCoins(String printResponse) {
		try (FileWriter writer = new FileWriter("userData/codeCoins.txt")) {
			 writer.write(printResponse); 
		}  catch (IOException e) {
			 System.err.println("Error de escritura en el archivo: " + e.getMessage());
		 }
	}
	
	public static void printResponse(String resource, String response) {
		try (FileWriter writer = new FileWriter("userData/response.txt")) {
			 writer.write("Recurso elegido: " + resource + "\n");
			 writer.write( "\n" + response);
		}  catch (IOException e) {
			 System.err.println("Error de escritura en el archivo: " + e.getMessage());
		 }
	}
	
	public static boolean continueExecution(Scanner scanner) {
	    while (true) {
	        System.out.println("Para seguir operando ingrese " + CONTINUE_EXECUTE + " ,para finalizar ingrese " + EXIT_EXECUTE + "\n");
	        String execution = scanner.next();

	        if (execution.equals(CONTINUE_EXECUTE)) {
	            return true; 
	        } else if (execution.equals(EXIT_EXECUTE)) {
	            return false; 
	        } else {
	            System.out.println("Entrada no válida. Por favor ingrese " + CONTINUE_EXECUTE + " para continuar o " + EXIT_EXECUTE + " para finalizar.\n"); 
	        }
	    }
	}
}