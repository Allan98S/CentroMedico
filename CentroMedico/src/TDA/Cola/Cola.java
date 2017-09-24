package TDA;

public interface Cola {

	public int getSize() throws ColaException; // devuelve el número de
	                                           // elementos en la cola

	public void anular(); // anula la Cola

	public boolean isEmpty(); // true si la Cola está vacía

	public int posicion(Object elemento) throws ColaException; // devuelve la
	                                                           // posicion de un
	                                                           // elemento en la
	                                                           // Cola

	public void encolar(Object elemento) throws ColaException; // encola un
	                                                           // elemento por
	                                                           // el extremo
	                                                           // posterior(final)
	                                                           // de la cola

	public Object desencolar() throws ColaException; // suprime el elemento que
	                                                 // está en la parte
	                                                 // anterior(frente/inicio)
	                                                 // de la cola

	public boolean existe(Object elemento) throws ColaException; // true si el
	                                                             // elemento fue
	                                                             // encolado

	public Object frente() throws ColaException; // devuelve el elemento que
	                                             // está en el frente/inicio de
	                                             // la Cola
}
