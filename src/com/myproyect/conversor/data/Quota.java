package com.myproyect.conversor.data;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class Quota implements JsonConverter{
	private JsonData jsonData; 
	private QuotaData response;
	
	public Quota (String json) {
		try {
			this.jsonData = this.fromJson(json, JsonData.class);
			this.response = this.fromJson(json, QuotaData.class);
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
			
			if (this.response.quotaLimit () != "0" && this.response.quotaLimit () != null) {
				int quotaRemaining = (Integer.valueOf(this.response.quotaLimit ()) - Integer.valueOf(this.response.requestRemaining()));
				
				System.out.println ("Cuota establecida: " + this.response.quotaLimit () + "\nSolicitudes disponibles: " 
							+ this.response.requestRemaining() + "\nSolicitudes realizadas: " + quotaRemaining);
			
			} else if (this.response.quotaLimit () == "0") {
				 System.out.println("La cuota disponible ha sido agotada, para mas información visite" + this.jsonData.termsOfUse ());
			} else {
				 System.out.println("El recurso solicitado no se encuentra disponible.\n"
					 		+ "Para mas información visite: " + this.jsonData.documentation ());
			}
		}
}
