package conversor_de_monedas;

import java.util.Scanner;

import api.*;
import data.*;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		Client client = new Client ();
		
		instruction ();
		enterResource (scanner, client);
	}
	
	public static void instruction () {
		System.out.println("BIENVENIDOS AL CONVERSOR DE MONEDAS EN TIEMPO REAL MAS COMPLETO DEL MERCADO!\n");
		System.out.println ("En este conversor de monedas el usuario dispone de una gran variedad de informacion relacionada con el mercado actual como por ejemplo: tasas de cambio, equivalencias monetarias, etc.\n");
		System.out.println ("Para comenzar a operar seleccione la opcion del recurso que desea solicitar:\n"
							+ "\tSi es la primera vez que va a utilizar el conversor de monedas se recomienda comenzar con el recurso CODES o visitar el archivo Readme.md para mantenerse informado sobre las funcionalidades disponibles de la app.\n"
							+ "\tSi desea realizar multiples operaciones se recomienza utilizar el recurso Quota para consultar el limite disponible y evitar bloqueos inesperados.\n");
		Resource.printAllDescriptions();
	}
	
	public static void enterResource (Scanner scanner, Client client) {
		int option = 0;
		
		while (!(option > 0 && option < 5)) {
			if (scanner.hasNextInt()) { 
				option = scanner.nextInt(); 
				
			if (option > 0 && option < 5) {
				System.out.println("\nOpcion elegida: " + Resource.getResource (option).toLowerCase() + " \ncargando...\n");	
				client.setResource(Resource.getResource (option));
				
				if (option == 1 || option == 3) {
					Resource.listOfRequest (client.getResource());
					enterRequest (scanner, client, option);
					
				} else if (option == 2) {	
					client.sendRequest();
					Coins gson = new Coins (client.getResponse()); 
					gson.printResponse();
				} else if (option == 4) {
					client.sendRequest();
					Quota quota = new Quota (client.getResponse()); 
					quota.printResponse();
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
		
		while (!(optionOfRequest > 0 && optionOfRequest < 5)) {
			if (scanner.hasNextInt()) { 
				optionOfRequest = scanner.nextInt(); 
					
				if (optionOfRequest > 0 && optionOfRequest < 5) { 
					enterParameters (scanner, client, optionOfResource, optionOfRequest);
					
				} else {
					System.out.println("Opcion incorrecta, el valor numerico debe ser entre 1 y 4.\nIntente nuevamente...");				
				}	
			} else {
				System.out.println("Error de entrada, el valor ingresado debe ser un numero entero.\nIntente nuevamente...");     				
				scanner.next();
			}
		}	
	
		scanner.close(); 
	}
	
	public static void enterParameters (Scanner scanner, Client client, int optionOfResource, int optionOfRequest) {
		Coins coins = new Coins ();
		
		switch (optionOfResource) {
			case 1: 
				if (optionOfRequest == 1) {
					System.out.println ("Ingrese el codigo de la moneda que desea evaluar.");
					coins.getCoins(); 
					String coin = scanner.next(); 
					
					if (coins.codeCoins_isValid(coin)) {
						client.setParameters(coin);
						client.sendRequest();
						
						System.out.print(client.getResponse());
						
									
			//		Coins gson = new Coins (client.getResponse()); 
				//	gson.printResponse();
					
						
					} else {
						System.out.println ("El codigo de la moneda solicitada no existe o es incorrecto intente nuevamente.");
					}
					
				} else if (optionOfRequest == 2) {
					System.out.println ("Ingrese el codigo de la moneda que desea evaluar.");
					coins.getCoins(); 
					String coin = scanner.next();
					
					if (coins.codeCoins_isValid(coin)) {
						client.setParameters(coin);
						
						System.out.println ("Ingrese el valor del monto de la moneda elegida " + coin); 
						double amount = scanner.nextDouble(); 
						client.setParameters(String.valueOf(amount));
						client.sendRequest();
		
						System.out.print(client.getResponse());
						
					} else {
						System.out.println ("El codigo de la moneda solicitada no existe o es incorrecto intente nuevamente.");
					}
				}
			break; 
			
			case 2: 
				if (optionOfRequest == 1) {
					System.out.print("eligio 1 en caso 2");
					// si eligio 1 son dos parametros (codigo 1, codigo 2)
				} else if (optionOfRequest == 2) {
					System.out.print("eligio 2 en caso 2");
					// si eligio 2 son tres parametros (codigo1, codigo2, monto)
				}
			break;
		}
	}
}
