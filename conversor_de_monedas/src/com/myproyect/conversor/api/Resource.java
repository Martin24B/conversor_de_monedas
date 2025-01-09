package com.myproyect.conversor.api;

public enum Resource {
	LATEST("Asignada una moneda, publica una lista completa con la tasa de cambio actual para todas las monedas del mundo."),
	CODES("Publica una lista completa con todas las monedas que estan actualmente activas en el mercado."),
	PAIR(" Asignadas dos monedas, publica la tasa de cambio actual entre ambas."), 
	QUOTA("Publica la cuota actual de solicitudes disponible para el usuario."); 

	private final String description;

	private Resource(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.name().toLowerCase() + " -> " + this.description;
	}

	public static int posResource (String value) {
		for (Resource resource : Resource.values()) 	  
			  if (resource.name().toLowerCase().equals(value)) 
				  return resource.ordinal()+1;
		
		return -1; 
	} 
	
	public static boolean resourceValid (String value) {
		for (Resource resource : Resource.values ()) {
			if (resource.name().toLowerCase().equals(value)) {
				return true; 
			}
		}	
		return false;
	}
	
	public static boolean optionValid (String option) {
		return option.equals("1") || option.equals("2");
	}
	
	public static String instruction () {
        String message = """
            BIENVENIDOS AL CONVERSOR DE MONEDAS EN TIEMPO REAL MAS COMPLETO DEL MERCADO!\n
            En este conversor de monedas el usuario dispone de una gran variedad de información relacionada con el mercado actual como por ejemplo tasas de cambio, conversiones monetarias, actividad económica, etc.\n
            IMPORTANTE!\n
            \tSi es su primera vez utilizando el conversor de monedas se recomienda elegir el recurso CODES o visitar el archivo Readme.md para mantenerse informado sobre las funcionalidades disponibles de la App.\n
            \tSi desea realizar múltiples operaciones se recomienza utilizar el recurso Quota para consultar el limite disponible y evitar bloqueos inesperados.\n
            MUCHAS GRACIAS POR SU VISITA, QUE LO DISFRUTE!\n
            __________________________________________________________________________________________________________________________________________________________________________________________________________________________\n
            Para comenzar a operar seleccione la opción correspondiente al recurso que desea solicitar:\n
            """;
  
        return message; 
	}
	
	 public static String printAllDescriptions() {
	    	String allDescriptions = "";
	    	
	        for (Resource resource : Resource.values()) {
	        	allDescriptions += resource.getDescription() + "\n";
	        }
	        
	        return allDescriptions; 
	 }
	 
	 public static String listOfRequest(String resource) {
			String listOfRequest = "";
			
			if (resource.equals (PAIR.name().toLowerCase())) {
		        	listOfRequest =
		        			"""
		        			  	\nSolicitudes disponibles para el recurso pair:\n 
		        			\t1) Para conocer la tasa de cambio actual entre ambas monedas.\n
		        			\t2) Para conocer la tasa de cambio y la equivalencia monetaria actual entre ambas monedas determinadas por un monto"		 
		        			""";
		        	return listOfRequest; 
			}
			
			return listOfRequest; 
	 }
}

