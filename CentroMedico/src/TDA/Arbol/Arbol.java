package TDA;

public interface Arbol {

	// elimina todo el Arbol
	public void anular();

	// true si el árbol está vacío
	public boolean isEmpty();

	// devuelve el número de nodos que tiene el árbol
	// método interno:
	// private int getSize(NodoBinario nodo)
	public int getSize() throws ArbolException;

	// true si el elemento indicado forma parte del Árbol
	// método interno:
	// private boolean busquedaBinaria(NodoBinario nodo, Object elemento)
	public boolean existe(Object elemento) throws ArbolException;

	// inserta el elemento en el árbol
	// metodo interno:
	// private NodoBinario insertar(NodoBinario nodo, Object elemento)
	public void insertar(Object elemento);

	// suprime el elemento indicado del árbol. Este proceso aplica a 3 casos:
	// 1. El nodo a suprimir no tiene hijos
	// 2. El nodo a suprimir solo tiene un hijo
	// 3. El nodo a suprimir tiene dos hijos
	// metodo interno:
	// private NodoBinario suprimir(NodoBinario nodo, Object elemento)
	public void suprimir(Object elemento) throws ArbolException;

	// devuelve el número de ancestros para un nodo dado –profundidad de un nodo
	// método interno:
	// private int alturaNodo(NodoBinario nodo, Object elemento)
	public int alturaNodo(Object elemento) throws ArbolException;

	// devuelve la altura máxima (profundidad máxima) de la raíz a una hoja del
	// árbol
	// método interno:
	// private int alturaArbol(NodoBinario nodo)
	public int alturaArbol() throws ArbolException;

	// devuelve el valor mínimo contenido en el árbol
	// método interno:
	// private Object minimo(NodoBinario nodo)
	public Object minimo() throws ArbolException;

	// devuelve el valor máximo contenido en el árbol
	// método interno:
	// private Object maximo(NodoBinario nodo)
	public Object maximo() throws ArbolException;

	// muestra todos los elementos existentes en el árbol
	// preOrden: recorre el árbol de la forma: nodo-izq-der
	// inOrden: recorre el árbol de la forma: izq-nodo-der
	// postOrden: recorre el árbol de la forma: izq-der-nodo
	// métodos internos:
	// private String preOrden(NodoBinario nodo)
	// private String inOrden(NodoBinario nodo)
	// private String postOrden(NodoBinario nodo)
	// public String toString();

}// fin de la interface Arbol
