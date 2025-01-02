package api;

public enum Resource {
	LATEST("1) Dependiendo del tipo de moneda asignada, devuelve una lista completa con la equivalencia actual de cada moneda del mundo."),
	CODES("2) Devuelve una lista completa con todas las monedas del mundo."),
	PAIR("3) En base a dos monedas asignadas, devuelve su equivalencia actual."),
	QUOTA("4) Devuelve el límite de solicitudes disponibles para el usuario.");

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
	    	case "LATEST":
	    		System.out.println("\nSolicitudes disponibles para el recurso LATEST: ");
	    		System.out.println("1) Para conocer la equivalencia actual de cada moneda del mundo dependiendo de una moneda asignada.");
	    		System.out.println("2) Para conocer la equivalencia actual de cada moneda del mundo dependiendo de una moneda asignada y su monto.");		 
	            break;

	        case "PAIR":
	        	System.out.println("\nSolicitudes disponibles para el recurso PAIR: ");
	    		System.out.println("1) Para conocer la equivalencia actual de dos monedas");
	    		System.out.println("2) Para conocer la equivalencia actual de dos monedas y un monto");		 
	            break;
	    }
	}
}

