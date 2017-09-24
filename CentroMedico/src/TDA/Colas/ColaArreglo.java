package TDA.Colas;

import TDA.Utilidades;

public class ColaArreglo implements Cola {

	private Object[] cola;
	private int fin;
	private int inicio;

	public ColaArreglo(int tamanio) {
		
		if (tamanio <= 0) {
	        
			System.exit(0);
			
        }
		
		this.cola = new Object[tamanio];
		this.fin = cola.length - 1;
		this.inicio = cola.length - 1;

	}

	@Override
	public int getSize() throws ColaException {

		return fin - inicio;
	}

	@Override
	public void anular() {

		inicio = fin;

	}

	@Override
	public boolean isEmpty() {

		return inicio == fin;
	}

	@Override
	public int posicion(Object elemento) throws ColaException {

		if (isEmpty()) {

			throw new ColaException("La cola se encuentra vacía");

		}

		int contador = 1;// El primer elemento está en uno

		for (int i = inicio + 1; i <= fin; i++) {

			if (Utilidades.igual(elemento, cola[i])) {

				return contador;

			}

			contador++;

		}

		return -1;
	}

	@Override
	public void encolar(Object elemento) throws ColaException {

		if (getSize() == cola.length) {

			throw new ColaException("La cola se encuentra llena");

		}

		// Cuando la cola está vacía no entra en el for, porque anterior y
		// posterior están en la misma posición (último elemento del arreglo)
		for (int i = inicio; i < fin; i++) {

			cola[i] = cola[i + 1];

		}

		// La primera vez encola en el extremo posterior y mueve anterior una
		// posición a la izquierda

		cola[fin] = elemento;
		inicio--;

	}

	@Override
	public Object desencolar() throws ColaException {

		if (isEmpty()) {

			throw new ColaException("La cola se encuentra vacía");

		}

		return cola[++inicio];
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

			throw new ColaException("La cola se encuentra vacía");

		}

		return cola[inicio + 1];
	}

	@Override
	public String toString() {

		String result = "La cola no existe.";

		if (!isEmpty()) {

			result = "";

			for (int i = inicio + 1; i <= fin; i++) {

				result += cola[i] + "\n";

			}
		}

		return result + "";
	}

}
