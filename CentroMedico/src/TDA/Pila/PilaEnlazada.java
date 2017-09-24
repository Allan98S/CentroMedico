package TDA;

import TDA.Listas.Nodo;

public class PilaEnlazada implements Pila, Cloneable {

	private Nodo tope;// Representa el tope de la pila
	private int contador;// contador de elementos apilados

	public PilaEnlazada() {

		this.tope = null;
		this.contador = 0;

	}

	@Override
	public void anular() {

		this.tope = null;
		this.contador = 0;

	}

	@Override
	public int getSize() {

		return contador;
	}

	@Override
	public boolean isEmpty() {

		return tope == null;// Quiere decir que no apunta a ningún nodo
		// return contador == 0;//Quiere decir que no hemos apilado ningún
		// elemento

	}

	@Override
	public Object top() throws PilaVaciaException {

		if (isEmpty()) {

			throw new PilaVaciaException("La pila se encuentra vacía");

		}

		return tope.elemento;
	}

	@Override
	public void push(Object elemento) throws PilaLlenaException {

		Nodo nuevoNodo = new Nodo(elemento);

		if (isEmpty()) {// Tope apunta a null

			tope = nuevoNodo;// Tope va a apuntar al nuevo nodo creado

		}
		else {// La pila no está vacía

			nuevoNodo.sgte = tope;// Unimos los nodos
			tope = nuevoNodo;// para que la lista enlazada funcione como pila

		}

		this.contador++;// Se apiló un nuevo elemento

	}

	@Override
	public Object pop() throws PilaVaciaException {

		if (isEmpty()) {

			throw new PilaVaciaException("La pila se encuentra vacía");

		}

		Object elemento = tope.elemento;

		tope = tope.sgte;// Tope pasa a apuntar al siguiente nodo
		contador--;// Control número elementos apilados

		return elemento;
	}

	public Object clone() throws CloneNotSupportedException {

		return super.clone();
	}

	@Override
	public String toString() {

		String result = "";

		if (isEmpty()) {

			result = "La pila se encuentra vacía.";

		}
		else {

			try {

				PilaEnlazada clone = (PilaEnlazada) clone();

				while (!clone.isEmpty()) {

					result += clone.pop() + "\n";

				}

			}
			catch (PilaVaciaException | CloneNotSupportedException e) {

				e.printStackTrace();

			}

		}

		return result;

	}

}
