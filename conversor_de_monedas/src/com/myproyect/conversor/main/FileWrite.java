package com.myproyect.conversor.main;

import java.io.FileWriter;
import java.io.IOException;

public final class FileWrite {

	public static void printCodeCoins (String response) {
		try (FileWriter writer = new FileWriter("userData/codeCoins.txt")) {
			 writer.write(response);
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

}
