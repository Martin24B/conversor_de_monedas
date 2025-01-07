package com.myproyect.conversor.api;

public enum Resource {
	LATEST(" Al asignar una moneda determinada, esta función devuelve una lista completa con la tasa de cambio actual para cada moneda del mundo."),
	CODES(" Devuelve una lista completa con todas las monedas del mundo que estan actualmente activas en el mercado."),
	PAIR(" Al asignar dos monedas determinadas, esta función devuelve la tasa de cambio actual entre ambas."), 
	QUOTA(" Devuelve la cuota de solicitudes disponible para el usuario."); 

	private final String description;

	private Resource(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.name().toLowerCase() + " -> " + this.description;
	}
	
    public static String printAllDescriptions() {
    	String allDescriptions = "";
    	
        for (Resource resource : Resource.values()) {
        	allDescriptions += resource.getDescription() + "\n";
        }
        
        return allDescriptions; 
    }

	public static boolean isValid (String value) {
		for (Resource resource : Resource.values ()) {
			if (resource.name ().equalsIgnoreCase (value)) {
				return true; 
			}
		}	
		return false;
	}
	
	public static int posResource (String value) {
		for (Resource resource : Resource.values()) 	  
			  if (resource.name().equalsIgnoreCase(value)) 
				  return resource.ordinal()+1;
		
		return -1; 
	} 
	
	public static String listOfRequest(String resource) {
		String listOfRequest = "";
		
		if (resource.equalsIgnoreCase (PAIR.name()))
	        	listOfRequest =
	        			"""
	        			  	\nSolicitudes disponibles para el recurso pair:\n 
	        			\t1) Para conocer la tasa de cambio y la equivalencia monetaria actual de dos monedas asignadas.\n
	        			\t2) Para conocer la tasa de cambio y la equivalencia monetaria actual de dos monedas asignadas basadas en un monto"\n		 
	        			""";
			
		return listOfRequest; 
	}
}

