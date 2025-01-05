package api;

public enum Resource {
	LATEST("1) En base a una moneda asignada, devuelve una lista completa con la tasa de cambio actual de cada moneda del mundo."),
	CODES("2) Devuelve una lista completa con todas las monedas del mundo activas en el mercado."),
	PAIR("3) En base a dos monedas asignadas, devuelve la tasa de cambio actual."), 
	QUOTA("4) Devuelve la cuota de solicitudes disponible para el usuario."); 

	private final String description;

	private Resource(String description) {
		this.description = description;
	}

	public void getDescription() {
		System.out.println(this.name() + ": " + this.description);
	}
	
    public static void printAllDescriptions() {
        for (Resource resource : Resource.values()) {
            resource.getDescription();
        }
    }

	public static boolean isValid (String value) {
		for (Resource resource : Resource.values ()) {
			if (resource.name ().equalsIgnoreCase (value)) {
				return true; 
			}
		}	
		return false;
	}
	
	public static String getResource (int option) {
		return Resource.values()[option - 1].name(); 
	}
	
	public static void listOfRequest(String resource) {
		switch (resource) {
	    	case "latest/":
	    		System.out.println("\nSolicitudes disponibles para el recurso LATEST: ");
	    		System.out.println("\t1) Para conocer la tasa de cambio actual de cada moneda del mundo en base a una moneda asignada.");
	    		System.out.println("\t2) Para conocer la tasa de cambio y la equivalencia monetaria actual de cada moneda del mundo en base de una moneda asignada y su monto.");		 
	            break;

	        case "pair/":
	        	System.out.println("\nSolicitudes disponibles para el recurso PAIR: ");
	    		System.out.println("\t1) Para conocer la tasa de cambio y la equivalencia monetaria actual de dos monedas asignadas.");
	    		System.out.println("\t2) Para conocer la tasa de cambio y la equivalencia monetaria actual de dos monedas asignadas y un monto");		 
	            break;
	    }
	}
}

