package data;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Coins {
	private CoinsResponse response;
	private ArrayList <String> coins;
	private ArrayList <String> codeCoins;
	
	public Coins () {
		
	}
	
	public Coins (String json) {
			this.response = this.fromJson(json, CoinsResponse.class);
			this.coins = new ArrayList <> ();
			this.codeCoins = new ArrayList <> (); 
	}
	
	 private <T> T fromJson(String json, Class<T> classOfT) {
	        Gson gson = new Gson();
	        return gson.fromJson(json, classOfT);
	 }
	
	public void printResponse () {
		System.out.println ("Resultado de la operación: " + this.response.resultado + "\n");
		
		if (this.response.codigosDeMonedas != null) {
			System.out.println ("Monedas activas en el mercado:\n");
			
			for (String []  code : this.response.codigosDeMonedas) {
				this.coins.add("Codigo: " + code[0] + " -> Nombre: " + code[1]);
				this.codeCoins.add(code [0]);
				
				System.out.println("\t" + code[0] + " -> " + code[1]);
			}
			
		} else {
			 System.out.println("El recurso solicitado no se encuentra disponible.\n"
			 		+ "Para mas información visite: " + this.response.documentacion + " o consulte la cuota disponible");
		}
	}

    public ArrayList<String> getCoins() {
        return new ArrayList<>(this.coins);
    }
	
    public boolean codeCoins_isValid (String code) {
    	return this.codeCoins.contains(code);
    }
    
	private static final class CoinsResponse {  
		@SerializedName("result")
	    private String resultado; 
	    @SerializedName("documentation")
	    private String documentacion;  
	    @SerializedName("supported_codes")
	    private String[][] codigosDeMonedas;   
	}
}
