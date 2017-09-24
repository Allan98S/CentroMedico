package TDA.Colas;

import TDA.Colas.Nodo;
import TDA.Utilidades;

public class ColaEnlazada implements Cola {

	private Nodo inicio;
	private Nodo fin;
	private int contador;// Nos lleva el control de los elementos encolados

	public ColaEnlazada() {

		inicio = null;
		fin = null;
		this.contador = 0;

	}

	@Override
	public int getSize() throws ColaException {

		if (isEmpty()) {

			throw new ColaException("La cola está vacía");

		}

		return contador;
	}

	@Override
	public void anular() {

		inicio = null;
		fin = null;
		this.contador = 0;

	}

	@Override
	public boolean isEmpty() {

		return inicio == null;
	}

	@Override
	public int posicion(Object elemento) throws ColaException {

		if (isEmpty()) {

			throw new ColaException("La cola se encuentra vacía");

		}

		int posicion = 1;// El primer elemento de la cola en la posición 1

		Nodo aux = inicio;

		while (aux != null) {

			if (Utilidades.igual(aux.elemento, elemento)) {

				return posicion;

			}

			aux = aux.sgte;
			posicion++;

		}

		return -1;
	}

	@Override
	public void encolar(Object elemento) throws ColaException {

		Nodo nuevoNodo = new Nodo(elemento);

		if (isEmpty()) {

			fin = nuevoNodo;
			inicio = fin;// Garantiza que anterior quede apuntando al
			                     // primer nodo

		}

		else {

			fin.sgte = nuevoNodo;
			fin = nuevoNodo;

		}

		contador++;

	}

	@Override
	public Object desencolar() throws ColaException {

		if (isEmpty()) {

			throw new ColaException("La cola se encuentra vacía");

		}

		Object elemento = inicio.elemento;// Elemento a desencolar

		// //CASO 1 Sólo se tiene un elemento encolado
		//
		// if (anterior == posterior) {
		//
		// anular();//Anulamos la cola
		//
		// }
		// else {//Existen varios elementos encolados

		inicio = inicio.sgte;

		// }

		contador--;// Restamos el elemento desencolado

		return elemento;
	}

	@Override
	public boolean existe(Object elemento) throws ColaException {

		if (isEmpty()) {

			throw new ColaException("La cola se encuentra vacía");

		}

		return posicion(elemento) != -1;
	}

	@Override
	public Object frente() throws ColaException {

		if (isEmpty()) {

		}
		
		return inicio.elemento;// Primer elemento de la cola
		
	}

	@Override
	public String toString() {

		String result = "La cola no existe.";

		if (!isEmpty()) {

			result = "";

			Nodo aux = inicio;

			while (aux != null) {

				result += aux.elemento + "\n";

				aux = aux.sgte;

			}

		}

		return result;
	}

}
