package TDA;

public interface Pila {

	public void anular();//Anulamos la pila
	
	public int getSize();//Devuelve el tamaño de la pila

	public boolean isEmpty();//Verifica si la pila se encuentra vacía

	public Object top() throws PilaVaciaException;//Retorna el tope de la pila

	public void push(Object elemento) throws PilaLlenaException;//Inserta un elemento en la pila (nuevo tope)

	public Object pop() throws PilaVaciaException;//Desapila y retorna el tope de la pila
	
}
