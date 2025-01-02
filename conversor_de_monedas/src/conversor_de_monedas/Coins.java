package conversor_de_monedas;

public final class Coins {
	private String coins_list;
	
	public Coins (String coins_list) {
		this.coins_list = coins_list; 
	}
	
	public String toString () {
		return this.coins_list;
	}
	
	
	
	//recibir objeto response como String
	//almacenar en un arreglo constante los codigos de cada moneda
	//recibir una o dos monedas y comprobar que sean validas
}
