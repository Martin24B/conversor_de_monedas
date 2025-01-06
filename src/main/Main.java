package main;

import java.util.Scanner;

import api.*;
import data.*;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		
		instruction ();
		int optionResource = enterResource (scanner);
		enterRequest (scanner, optionResource); 
	}
	
	public static void instruction () {
		System.out.println("BIENVENIDOS AL CONVERSOR DE MONEDAS EN TIEMPO REAL MAS COMPLETO DEL MERCADO!\n");
		System.out.println ("En este conversor de monedas el usuario dispone de una gran variedad de informacion relacionada con el mercado actual como por ejemplo: tasas de cambio, equivalencias monetarias, etc.\n");
		System.out.println ("Para comenzar a operar seleccione la opcion del recurso que desea solicitar:\n"
							+ "\tSi es la primera vez que va a utilizar el conversor de monedas se recomienda comenzar con el recurso CODES o visitar el archivo Readme.md para mantenerse informado sobre las funcionalidades disponibles de la app.\n"
							+ "\tSi desea realizar multiples operaciones se recomienza utilizar el recurso Quota para consultar el limite disponible y evitar bloqueos inesperados.\n");
		Resource.printAllDescriptions();
	}
	
	public static int enterResource (Scanner scanner) {
		int optionResource = 0;
		
		while (!(optionResource > 0 && optionResource < 5)) {
			if (scanner.hasNextInt()) { 
				optionResource = scanner.nextInt(); 
				
			if (optionResource > 0 && optionResource < 5) {
				scanner.close(); 
				return optionResource; 
			} else {
				System.out.println("Opcion incorrecta, el valor numerico debe ser entre 1 y 5.\nIntente nuevamente...");				
			  }	
			
			} else {
				System.out.println("Error de entrada, el valor ingresado debe ser un numero entero.\nIntente nuevamente...");     				
				scanner.next();
			}
		}	
	
		scanner.close(); 
		return 0; 
	}
	
	
	public static void enterRequest (Scanner scanner, int optionResource) {
		Client client = new Client ();
		Coins coins = new Coins (client.getResponse()); 
		
		client.setResource(Resource.getResource (optionResource).toLowerCase());
		
		System.out.println("\nOpcion elegida: " + Resource.getResource (optionResource).toLowerCase() + " \ncargando...\n");	
		
		switch (optionResource) {
			case 1:
			case 3:
				Resource.listOfRequest(client.getResource ());
				enterParameters (scanner, client, coins, optionResource);
			break;
			
			case 2:
				client.sendRequest(); 
				coins.printResponse();
				break;
			case 4:
				client.sendRequest();
				Quota quota = new Quota (client.getResponse()); 
				quota.printResponse();
				break; 
		}
	}
		

	
	public static void enterParameters (Scanner scanner, Client client, Coins coins, int optionResource) {
		int optionRequest = 0;
		
		while (!(optionRequest > 0 && optionRequest < 3)) {
			if (scanner.hasNextInt()) { 
				optionRequest = scanner.nextInt(); 

				if (optionRequest > 0 && optionRequest < 3) { 
					System.out.println ("Codigos de identificación para cada moneda del mundo:\n");
					coins.getCoins(); 
	
					switch (optionResource) {
						case 1:
							if (optionRequest == 1) {
								System.out.println ("Ingrese el codigo de la moneda que desea evaluar.");
								String coin = scanner.next();
								
								while (coins.codeCoins_isValid(coin)) {
									if (coins.codeCoins_isValid(coin)) {
										client.setParameters(coin);
										client.sendRequest();
										//preparar gson
		
									} else {
									System.out.println ("El codigo de la moneda asignada no existe o es incorrecto intente nuevamente.");
									}
								}
							} else if (optionRequest == 2) {
								System.out.println ("Ingrese el codigo de la moneda que desea evaluar.");
								String coin = scanner.next();
	
								while (coins.codeCoins_isValid(coin)) {
									if (coins.codeCoins_isValid(coin)) {
										System.out.println ("Ingrese un monto numerico para la moneda asignada.");								
										
										if (scanner.hasNextDouble()) { 
											double amount = scanner.nextDouble();
											
											client.setParameters(coin);
											client.setParameters(String.valueOf (amount));
											client.sendRequest();
											//preparar gson
										} else {
											System.out.println("Error de entrada, el valor ingresado debe ser un valor numerico.\nIntente nuevamente...");     				
											scanner.next();
										}
									} else {
										System.out.println ("El codigo de la monedas asignada no existe o es incorrecto intente nuevamente.");
									}
								}							
							}	
							break;
				
						case 3:
							if (optionRequest == 1) {
								System.out.println ("Ingrese el codigo de la primer moneda que desea evaluar.");
								String coin_1 = scanner.next();
								System.out.println ("Ingrese el codigo de la segunda moneda que desea evaluar.");
								String coin_2 = scanner.next();
								
								while (coins.codeCoins_isValid(coin_1) && coins.codeCoins_isValid(coin_2)) {
									if (coins.codeCoins_isValid(coin_1) && coins.codeCoins_isValid(coin_2)) {
										client.setParameters(coin_1);
										client.setParameters(coin_2);
										client.sendRequest();
										//preparar gson
		
									} else {
										System.out.println ("El codigo de las monedas asignadas no existe o es incorrecto intente nuevamente.");
									}
								}
							} else if (optionRequest == 2) {
								System.out.println ("Ingrese el codigo de la primer moneda que desea evaluar.");
								String coin_1 = scanner.next();
								System.out.println ("Ingrese el codigo de la segunda moneda que desea evaluar.");
								String coin_2 = scanner.next();
	
								while (coins.codeCoins_isValid(coin_1) && coins.codeCoins_isValid(coin_2)) {
									if (coins.codeCoins_isValid(coin_1) && coins.codeCoins_isValid(coin_2)) {
										System.out.println ("Ingrese un monto numerico para obtener la equivalencia monetaria de ambas monedas.");								
										
										if (scanner.hasNextDouble()) { 
											double amount = scanner.nextDouble();
											
											client.setParameters(coin_1);
											client.setParameters(coin_2);
											client.setParameters(String.valueOf (amount));
											client.sendRequest();
											
											//preparar gson
										} else {
											System.out.println("Error de entrada, el valor ingresado debe ser un valor numerico.\nIntente nuevamente...");     				
											scanner.next();
										}
									} else {
										System.out.println ("El codigo de las monedas asignadas no existe o es incorrecto intente nuevamente.");
									}
								}							
							}	
							break;
					}
					
				} else {
					System.out.println("Opcion incorrecta, el valor numerico debe ser entre 1 y 2.\nIntente nuevamente...");				
				}	
			}  else {
				System.out.println("Error de entrada, el valor ingresado debe ser un numero entero.\nIntente nuevamente...");     				
				scanner.next();
			}
			
			scanner.close(); 
		}		
	}	
}
