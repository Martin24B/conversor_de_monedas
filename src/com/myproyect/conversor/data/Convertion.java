package com.myproyect.conversor.data;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.Map;

public class Convertion implements JsonConverter{
	private JsonData jsonData;
	private AllConvertionData AllResponse;
    private ConvertionData response;

    public Convertion(String json) {
    	try {
    		this.jsonData = this.fromJson(json, JsonData.class);
    		this.response = this.fromJson(json, ConvertionData.class);
    		this.AllResponse = this.fromJson(json, AllConvertionData.class);
    		
    	} catch (JsonSyntaxException e) {
    			throw new IllegalArgumentException("Error al procesar el JSON: " + e.getMessage());
    	  }	
    }

    @Override
    public <T> T fromJson(String json, Class<T> classOfT) {
        Gson gson = new Gson();
        return gson.fromJson(json, classOfT);
    }

    public void printTasasDeCambio() {
        System.out.println("Resultado de la operación: " + this.jsonData.result() + "\n");
        System.out.println("Código de moneda base: " + this.AllResponse.code () + "\n");

        if (this.AllResponse.exchange() != null) {
            System.out.println("Tasas de cambio disponibles:\n");
            for (Map.Entry<String, Double> entry : this.AllResponse.exchange().entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } else {
            System.out.println("El recurso solicitado no se encuentra disponible.\n"
                    + "Para más información, visite: " + this.jsonData.documentation() + " o consulte la cuota disponible.");
        }
    }
    
    public void printTasaDeCambio() {
        System.out.println("Resultado de la operación: " + this.jsonData.result () + "\n");
        System.out.println("Código de moneda base: " + this.response.code ());
        System.out.println("Código de moneda secundaria: " + this.response.code2 ());

        if (this.response.exchange() != 0) { 
            System.out.println("Tasa de cambio disponible para 1 peso argentino: " + this.response.exchange());
        } else {
            System.out.println("El recurso solicitado no se encuentra disponible.\n"
                    + "Para más información, visite: " + this.jsonData.documentation () + " o consulte la cuota disponible.");
        }
    }
    
    public void equivalenciaMonetaria () {
        System.out.println("Resultado de la operación: " + this.response.convertionResult() + "\n");
        System.out.println("Código de moneda base: " + this.response.code ());
        System.out.println("Código de moneda secundaria: " + this.response.code ());

        if (this.response.exchange() != 0) { 
            System.out.println("Tasa de cambio disponible para 1: " + this.response.code() + " a " + this.response.code2()  + " = " +this.response.exchange());
         
        } else {
            System.out.println("El recurso solicitado no se encuentra disponible.\n"
                    + "Para más información, visite: " + this.jsonData.documentation () + " o consulte la cuota disponible.");
        }
    }
 
}
