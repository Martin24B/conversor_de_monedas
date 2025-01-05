package data;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Quota {
	private QuotaResponse response;
	
	public Quota (String json) {
		this.response = this.fromJson(json, QuotaResponse.class);
	}
	
	 private <T> T fromJson(String json, Class<T> classOfT) {
	        Gson gson = new Gson();
	        
	        return gson.fromJson(json, classOfT);
	 }
	
	 public void printResponse () {
			System.out.println ("Resultado de la operación: " + this.response.resultado + "\n");
			
			if (this.response.cuotaEstablecida != "0" && this.response.cuotaEstablecida != null) {
				int cuotaDisponible = (Integer.valueOf(this.response.cuotaEstablecida) - Integer.valueOf(this.response.solicitudesDisponibles));
				
				System.out.println ("Cuota establecida: " + this.response.cuotaEstablecida + "\nSolicitudes disponibles: " 
							+ this.response.solicitudesDisponibles + "\nSolicitudes realizadas: " + cuotaDisponible);
			
			} else if (this.response.cuotaEstablecida == "0") {
				 System.out.println("La cuota disponible ha sido agotada, para mas información visite" + this.response.terminosDeUso);
			} else {
				 System.out.println("El recurso solicitado no se encuentra disponible.\n"
					 		+ "Para mas información visite: " + this.response.documentacion);
			}
		}

	private static final class QuotaResponse {  
		@SerializedName("result")
	    private String resultado; 
	    @SerializedName("documentation")
	    private String documentacion;
	    @SerializedName ("terms_of_use")
	    private String terminosDeUso; 
	    @SerializedName ("plan_quota")
	    private String cuotaEstablecida;
	    @SerializedName ("requests_remaining")
	    private String solicitudesDisponibles;
	}
}
