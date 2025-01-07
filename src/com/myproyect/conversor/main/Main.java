package com.myproyect.conversor.main;

import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

import com.myproyect.conversor.api.*;
import com.myproyect.conversor.data.*;

public class Main {

	public static void main (String [] args) {
		Scanner scanner = new Scanner (System.in);
	
		String instruction = instruction ();
		System.out.println (instruction + Resource.printAllDescriptions());
		
		//comienza el while 
		String resource = enterResource (scanner);
		System.out.println("\nOpcion elegida: " + resource + " \ncargando...\n" + Resource.listOfRequest(resource));
		int optionRequest = enterRequest (scanner, resource);
		
		Client client = new Client ();
		client.sendRequest(); 
		Coins coins = new Coins (client.getResponse());
		
		String parameters = enterParameters (scanner, coins, resource, optionRequest);
		String response = enterRequest (resource, parameters, client, coins);
		
		System.out.println (response);
		
	//	printResponse (instruction, resource, Resource.listOfRequest(resource), optionRequest, response);	

		// termina el while
		
		scanner.close();
	}


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
            """;
        
        return message; 
	}
	
/*	public static void printResponse (String instruction, String resource, String listOfRequest, int optionRequest, response) {
		
		try (FileWriter writer = new FileWriter("src/instruction.txt")) {
			writer.write(instruction);
			writer.write (Resource.printAllDescriptions() + "\n");
			writer.write("Recurso elegido: " + resource + "\n");
			writer.write (Resource.listOfRequest(resource) + "\n"); 	
			
			if (optionRequest != 0)
				writer.write ("Opción elegida: " + optionRequest + "\n"); 	
			
		 } catch (IOException e) {
			 System.err.println("Error de escritura en el archivo: " + e.getMessage());
		 }
	} */
	
	public static String enterResource (Scanner scanner) {
		String resource = " "; 
		
		while (true) {
			resource = scanner.next(); 
			
			if (Resource.isValid(resource)) {
				return resource; 
			} else {
				System.out.println ("El valor ingresado no se corresponde con ninguno de los recursos de la lista, intente nuevamente...");
				scanner.nextLine(); 
			}
		}
	}
	
	public static int enterRequest (Scanner scanner, String resource) {
		int optionRequest = 0;
		
		if (Resource.listOfRequest(resource) != null) {
			
			while (!(optionRequest > 0 && optionRequest < 3)) {
				if (scanner.hasNextInt()) { 
					optionRequest = scanner.nextInt(); 
					
					if (optionRequest > 0 && optionRequest < 3) { 
						return optionRequest; 
					} else {
						System.out.println ("El numero ingresado no se corresponde con ninguna opción disponible, intente nuevamente...");
						scanner.nextLine();
					}
				}
			}
		}
		
		return optionRequest;
	}
	
	private static String enterParameters (Scanner scanner,  Coins coins, String resource, int optionRequest) {
		String parameters = "";
		
		if (optionRequest != 0) {
			System.out.println ("\nOpción elegida: " + optionRequest + "\n");
			System.out.println ("__________________________________________________________________________________________________________________________________________________________________________________________________________________________\n"
					            );
			
			System.out.println ("Codigos de identificación para cada moneda del mundo:\n");
			coins.printResponse();
			System.out.println ("__________________________________________________________________________________________________________________________________________________________________________________________________________________________\n"
			                    );
				
			if (Resource.posResource(resource) == 1) {
				System.out.println ("Ingrese el codigo de la moneda que desea evaluar.");
				String coin = scanner.next();
				
				while (coins.codeCoins_isValid(coin)) {
					if (coins.codeCoins_isValid(coin)) {
						parameters = coin; 
						return parameters; 
					} else {
						System.out.println ("El codigo de la moneda asignada no existe o es incorrecto intente nuevamente.");
					}
				}
				
			} else if (Resource.posResource(resource) == 3) {
				System.out.println ("Ingrese el codigo de la primer moneda que desea evaluar.");
				String coin_1 = scanner.next();
				System.out.println ("Ingrese el codigo de la segunda moneda que desea evaluar.");
				String coin_2 = scanner.next();
				
				while (coins.codeCoins_isValid(coin_1) && coins.codeCoins_isValid(coin_2)) {
					
						if (coins.codeCoins_isValid(coin_1) && coins.codeCoins_isValid(coin_2)) {
							parameters += coin_1 + "/" + coin_2 + "/";	
						
							if (optionRequest == 1) {
								return parameters; 
								
							} else if (optionRequest == 2) {
								
								if (scanner.hasNextDouble()) { 
									double amount = scanner.nextDouble();
									parameters +=  String.valueOf(amount) + "/";						
									return parameters; 
									
								} else {
									System.out.println("Error de entrada, el valor ingresado debe ser un valor numerico.\nIntente nuevamente...");     				
									scanner.next();
								}
								
							  } else {
								  	System.out.println ("El codigo de las monedas asignadas no existe o es incorrecto intente nuevamente...");
							   }	
							
					} else {
							System.out.println ("El codigo de las monedas asignadas no existe o es incorrecto intente nuevamente...");
					  }				
				}
				
			}			
		}

		return parameters; 
	}
	
	private static String enterRequest(String resource, String parameters, Client client, Coins coins) {
		String response = ""; 
		
		client.setResource(resource);
		
		if (parameters != null) {
			client.setParameters(parameters);
			client.sendRequest();
			
			if (Resource.posResource(resource) == 1) {
				Convertion convertion = new Convertion (client.getResponse());						
			//	response = convertion.printTasasDeCambio();		
				
			} else if (Resource.posResource(resource) == 3) {
				Convertion convertion = new Convertion (client.getResponse());						
				//response = convertion.equivalenciaMonetaria();		
			}
			
		} else {
			client.sendRequest();
			if (Resource.posResource(resource) == 2) {
			//	response = coins.printResponse();
				
			} else if (Resource.posResource(resource) == 4) {
				Quota quota = new Quota (client.getResponse()); 
				//response = quota.printResponse();
			}
		}
		return response; 
	}
	
}


