package com.myproyect.conversor.data;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.Map;

public class Convertion implements JsonConverter{
	private JsonData jsonData;
	private AllConvertionData AllResponse;
    private ConvertionData response;

    public Convertion(String AllJson) {
    	try {
    		this.jsonData = this.fromJson(AllJson, JsonData.class);
    		this.AllResponse = this.fromJson(AllJson, AllConvertionData.class);
    		
    	} catch (JsonSyntaxException e) {
    			throw new IllegalArgumentException("Error al procesar el JSON: " + e.getMessage());
    	  }	
    }
    
    public Convertion(String json, int option) {
    	try {
    		this.jsonData = this.fromJson(json, JsonData.class);
    		this.response = this.fromJson(json, ConvertionData.class);
    		
    	} catch (JsonSyntaxException e) {
    			throw new IllegalArgumentException("Error al procesar el JSON: " + e.getMessage());
    	  }	
    }
    

    @Override
    public <T> T fromJson(String json, Class<T> classOfT) {
        Gson gson = new Gson();
        return gson.fromJson(json, classOfT);
    }

    public String printAllExchange() {
    	String response = "Resultado de la operación: " + this.jsonData.result()  
    			+ "\n" + "\nCódigo de moneda base: " + this.AllResponse.code () 
    			+ "\n" + "\nTasas de cambio disponibles para 1 " + this.AllResponse.code() + ":\n" + "\n"; 
    	
        if (this.AllResponse.exchange() != null) {         
            for (Map.Entry<String, Double> entry : this.AllResponse.exchange().entrySet()) {
                response += entry.getKey() + ": " + entry.getValue() + "\n";
            }
            return response; 
        } 
        
        response += "El recurso solicitado no se encuentra disponible.\nPara más información, visite: " 
        		+ this.jsonData.documentation() + " o consulte la cuota disponible."; 
   
        return response;
    }
  
    
    public String printExchange() {
    	String response = "Resultado de la operación: " + this.jsonData.result()  
		+ "\n" + "\nCódigo de moneda base: " + this.response.code () 
		+ "\n" + "\nCódigo de moneda secundaria: " + this.response.code2 () 
		+ "\n" + "\nTasa de cambio disponible para 1: " + this.response.code() + " a " + this.response.code2() + "\n"; 
    	
        if (this.response.exchange() != 0) { 
        	response += "\t" + this.response.exchange() + "\n";
        	return response; 
        }
      
        response += "El recurso solicitado no se encuentra disponible.\n" + "Para más información, visite: " + this.jsonData.documentation () + " o consulte la cuota disponible.\n";
        return response;
    }
    
    public String convertion () {
    	String response = "\nEl monto elegido en " + this.response.code() + " equivale a "; 

        if (this.response.convertionResult() != 0) { 
        	response += "\t" + this.response.convertionResult() + " " + this.response.code2() + "\n"; 
            return response; 
        } 
        
        response += "El recurso solicitado no se encuentra disponible.\n" + "Para más información, visite: " + this.jsonData.documentation () + " o consulte la cuota disponible.\n";
        return response;
    }
 
}
