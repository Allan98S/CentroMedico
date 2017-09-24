package TDA;

import TDA.NodoBinario;

public class ArbolBinarioBusqueda implements Arbol {

	// Atributos

	private NodoBinario raiz;// Representa la entrada única al árbol

	@Override
	public void anular() {

		raiz = null;

	}

	@Override
	public boolean isEmpty() {

		return raiz == null;

	}

	@Override
	public int getSize() throws ArbolException {

		if (isEmpty()) {

			throw new ArbolException("El Árbol Binario no existe");

		}

		return getSize(raiz);

	}

	private int getSize(NodoBinario nodo) {

		if (nodo == null) {

			return 0;

		}
		else {

			return 1 + getSize(nodo.izquierda) + getSize(nodo.derecha);

		}
	}

	@Override
	public boolean existe(Object elemento) throws ArbolException {

		if (isEmpty()) {

			throw new ArbolException("El Árbol Binario no existe");

		}

		return existe(raiz, elemento);

	}

	private boolean existe(NodoBinario nodo, Object elemento) {

		if (nodo == null) {

			return false;

		}
		else if (Utilidades.igual(nodo.elemento, elemento)) {

			return true;

		}
		else {

			if (Utilidades.comparar(nodo.elemento, elemento) < 0) {

				return existe(nodo.izquierda, elemento);

			}
			else {

				return existe(nodo.derecha, elemento);

			}

		}

	}

	@Override
	public void insertar(Object elemento) {

		raiz = insertar(raiz, elemento);

	}

	private NodoBinario insertar(NodoBinario nodo, Object elemento) {

		if (nodo == null) {// El árbol está vacío

			nodo = new NodoBinario(elemento);

		}

		else {

			if (Utilidades.comparar(nodo.elemento, elemento) < 0) {

				nodo.derecha = insertar(nodo.derecha, elemento);

			}
			else if (Utilidades.comparar(nodo.elemento, elemento) > 0) {

				nodo.izquierda = insertar(nodo.izquierda, elemento);

			}

		}

		return nodo;
	}

	@Override
	public void suprimir(Object elemento) throws ArbolException {

		if (isEmpty()) {

			throw new ArbolException("El árbol binario no existe");

		}

		raiz = suprimir(raiz, elemento);

	}

	private NodoBinario suprimir(NodoBinario nodo, Object elemento)
	        throws ArbolException {

		if (nodo != null) {

			if (Utilidades.comparar(nodo.elemento, elemento) > 0) {

				nodo.izquierda = suprimir(nodo.izquierda, elemento);

			}
			else if (Utilidades.comparar(nodo.elemento, elemento) < 0) {

				nodo.derecha = suprimir(nodo.derecha, elemento);

			}
			else if (Utilidades.igual(nodo.elemento, elemento)) {// Si ya lo
				                                                 // encontré

				if (nodo.izquierda == null && nodo.derecha == null) {

					return nodo = null;

				}
				else if (nodo.izquierda != null && nodo.derecha == null) {

					nodo = nodo.izquierda;

				}
				else if (nodo.izquierda == null && nodo.derecha != null) {

					Object temp = getNodo(minimo(nodo.derecha)).elemento;

					suprimir(temp);

					nodo.elemento = temp;

				}
				else if (nodo.izquierda != null && nodo.derecha != null) {

					Object temp = getNodo(minimo(nodo.derecha)).elemento;

					suprimir(temp);

					nodo.derecha.elemento = temp;

				}
			}

		}

		return nodo;

	}

	@Override
	public int alturaNodo(Object elemento) throws ArbolException {

		if (isEmpty()) {

			throw new ArbolException("El árbol binario no existe");

		}

		return alturaNodo(raiz, elemento, 0);
	}

	@Override
	public int alturaArbol() throws ArbolException {

		if (isEmpty()) {

			throw new ArbolException("El árbol binario no existe");

		}

		return alturaArbol(raiz);
	}

	private int alturaNodo(NodoBinario nodo, Object elemento, int contador) {

		if (nodo == null) {

			return 0;

		}
		else if (Utilidades.igual(nodo.elemento, elemento)) {

			return contador;

		}

		else if (Utilidades.comparar(nodo.elemento, elemento) < 0) {

			return (int) alturaNodo(nodo.izquierda, elemento, ++contador);

		}

		else {

			return (int) alturaNodo(nodo.derecha, elemento, ++contador);

		}

	}

	private int alturaArbol(NodoBinario nodo) {

		if (nodo == null) {

			return 0;

		}

		else {

			return (int) Utilidades.maximo(alturaArbol(nodo.izquierda),
			        alturaArbol(nodo.derecha) + 1);

		}

	}

	@Override
	public Object minimo() throws ArbolException {

		if (isEmpty()) {

			throw new ArbolException("El árbol binario no existe");

		}

		return minimo(raiz);
	}

	private Object minimo(NodoBinario nodo) {

		if (nodo.izquierda == null) {

			return nodo.elemento;

		}
		else {

			return minimo(nodo.izquierda);

		}
	}

	@Override
	public Object maximo() throws ArbolException {

		if (isEmpty()) {

			throw new ArbolException("El árbol binario no existe");

		}

		return maximo(raiz);

	}

	private Object maximo(NodoBinario nodo) {

		if (nodo.derecha == null) {

			return nodo.elemento;

		}
		else {

			return maximo(nodo.derecha);

		}
	}

	@Override
	public String toString() {

		String result = "****Recorrido por un árbol binario simple****\n\n";

		result += "Preorden:\n" + preOrden(raiz) + "\n";
		result += "InOrden:\n" + inOrden(raiz) + "\n";
		result += "PostOrden:\n" + postOrden(raiz) + "\n";

		return result;
	}

	private String postOrden(NodoBinario nodo) {

		String result = "";

		if (nodo != null) {

			result += postOrden(nodo.izquierda);
			result += postOrden(nodo.derecha);
			result += nodo.elemento + " ";

		}

		return result;

	}

	private String inOrden(NodoBinario nodo) {

		String result = "";

		if (nodo != null) {

			result += inOrden(nodo.izquierda);
			result += nodo.elemento + " ";
			result += inOrden(nodo.derecha);

		}

		return result;

	}

	private String preOrden(NodoBinario nodo) {

		String result = "";

		if (nodo != null) {

			result += nodo.elemento + " ";
			result += preOrden(nodo.izquierda);
			result += preOrden(nodo.derecha);

		}

		return result;
	}

	public NodoBinario getNodo(Object elemento) throws ArbolException {

		if (isEmpty()) {

			throw new ArbolException("El árbol binario no existe");

		}

		// if (!existe(elemento)) {
		//
		// throw new ArbolException(
		// "El elemento no se encuentra en el árbol binario");
		//
		// }

		return getNodo(raiz, elemento);

	}

	private NodoBinario getNodo(NodoBinario nodo, Object elemento) {

		if (Utilidades.igual(nodo.elemento, elemento)) {

			return nodo;

		}
		else {

			if (Utilidades.comparar(nodo.elemento, elemento) < 0) {

				return getNodo(nodo.derecha, elemento);

			}
			else if (Utilidades.comparar(nodo.elemento, elemento) > 0) {

				return getNodo(nodo.izquierda, elemento);

			}

		}

		return null;

	}
}
