package conversor_de_monedas;


public class Client { 
	private String resource; 
	private String parameters; 
	
	public Client () {
		
	}
	
	public void setResource (String resource) {
		this.resource = resource; 
	}
	
	public String getResource () {
		return this.resource; 
	}
	
	public void setParameters (String parameters) {
		this.parameters = parameters; 
	}
	
	
 /*
  * codigo de estado
  *  cuerpo de la respuesta
  *  		result
  *  		documentation
  *  		terms_of_use
  *  		time_last_update_unix
  *  			time_last_update_etc
  * 			 time_next_update_unix
  * 			 time_next_update_utc
  * 			 base_code
  * 			  conversion_rates
  * 
  */ /*
   
      //  try {
     	
        	/*    
            // Convertir la respuesta JSON a un objeto Response
            String json = response.body();
            Gson gson = new Gson();
            Response respuesta = gson.fromJson(json, Response.class);

            // Mostrar el resultado deserializado
            System.out.println("Resultado: " + respuesta.getResult());
            System.out.println("Base de moneda: " + respuesta.getBaseCode());
            System.out.println("Timestamp: " + respuesta.getTimeLastUpdateUnix());
            System.out.println("Tasa de conversión para EUR: " + respuesta.getConversionRates().get("EUR"));
*/
    } 
