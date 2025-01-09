package com.myproyect.conversor.data;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class Coins implements JsonConverter {
	private JsonData jsonData; 
	private CoinData response;
	private static ArrayList <String> coins = new ArrayList <> ();
	private static ArrayList <String> codeCoins = new ArrayList <> (); ;
	
	public Coins (String json) {
		try {
		    this.jsonData = this.fromJson(json, JsonData.class);
		    this.response = this.fromJson(json, CoinData.class);
		} catch (JsonSyntaxException e) {
		    throw new IllegalArgumentException("Error al procesar el JSON: " + e.getMessage());
		}	
	}
	
	@Override
	 public <T> T fromJson(String json, Class<T> classOfT) {
	        Gson gson = new Gson();
	        return gson.fromJson(json, classOfT);
	 }
	
	public String printResponse () {
		String response = "Resultado de la operación: " 
					    + this.jsonData.result () + "\n" +"\nMonedas activas en el mercado:\n"; 
		
		if (this.response.codes () != null) {
			for (String []  code : this.response.codes ()) {
				coins.add("Codigo: " + code[0] + " -> Nombre: " + code[1]);
				codeCoins.add(code [0]);
				
				response += "\n" + "\t" + code[0] + " -> " + code[1] + "\n";
			}
			
			return response;
			
		} 
			response += "El recurso solicitado no se encuentra disponible.\nPara mas información visite: " 
		             + this.jsonData.documentation () + " o consulte la cuota disponible";
			
			return response;	
	}
	
    public static boolean codeCoinValid (String code) {
    	return codeCoins.contains(code); 
    }
}
