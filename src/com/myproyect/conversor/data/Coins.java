package com.myproyect.conversor.data;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class Coins implements JsonConverter {
	private JsonData jsonData; 
	private CoinData response;
	private ArrayList <String> coins;
	private ArrayList <String> codeCoins;
	
	public Coins (String json) {
		try {
		    this.jsonData = this.fromJson(json, JsonData.class);
		    this.response = this.fromJson(json, CoinData.class);
		    
		    this.coins = new ArrayList <> ();
			this.codeCoins = new ArrayList <> (); 
		} catch (JsonSyntaxException e) {
		    throw new IllegalArgumentException("Error al procesar el JSON: " + e.getMessage());
		}	
	}
	
	@Override
	 public <T> T fromJson(String json, Class<T> classOfT) {
	        Gson gson = new Gson();
	        return gson.fromJson(json, classOfT);
	 }
	
	public void printResponse () {
		System.out.println ("Resultado de la operación: " + this.jsonData.result () + "\n");
		
		if (this.response.codes () != null) {
			System.out.println ("Monedas activas en el mercado:\n");
			
			for (String []  code : this.response.codes ()) {
				this.coins.add("Codigo: " + code[0] + " -> Nombre: " + code[1]);
				this.codeCoins.add(code [0]);
				
				System.out.println("\t" + code[0] + " -> " + code[1]);
			}
			
		} else {
			 System.out.println("El recurso solicitado no se encuentra disponible.\n"
			 		+ "Para mas información visite: " + this.jsonData.documentation () + " o consulte la cuota disponible");
		}
	}
	
    public boolean codeCoins_isValid (String code) {
    	return this.codeCoins.contains(code); 
    }
}
