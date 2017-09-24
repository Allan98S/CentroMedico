package TDA;

import TDA.Listas.ListaException;
import java.io.Serializable;

public class ConjuntoArreglo implements Conjuntable, Serializable {

	private Object[] datos;
	private int indice;

	public ConjuntoArreglo(int n) {

		this.indice = 0;

		this.datos = new Object[n];

	}

	@Override
	public int getSize() throws ConjuntoException, ListaException {

		return indice;
	}

	@Override
	public void anular() {

		indice = 0;

	}

	@Override
	public boolean isEmpty() {

		return indice == 0;

	}

	@Override
	public int getPosicion(Object elemento) throws ConjuntoException,
	        ListaException {

		for (int i = 0; i < indice; i++) {

			if (Utilidades.igual(datos[i], elemento)) {

				return i;

			}

		}

		return -1;
	}

	@Override
	public Object getElemento(int posicion) throws ConjuntoException,
	        ListaException {

		for (int i = 0; i < datos.length; i++) {

			if (i == posicion) {

				return datos[i];

			}

		}

		return null;
	}

	@Override
	public void insertar(Object elemento) throws ConjuntoException,
	        ListaException {

		if (indice == datos.length) {

			throw new ConjuntoException(
			        "El arreglo conjunto se encuentra lleno.");

		}

		if (isEmpty()) {

			datos[indice++] = elemento;

		}
		else if (!existe(elemento)) {

			datos[indice++] = elemento;

		}

	}

	@Override
	public void suprimir(Object elemento) throws ConjuntoException,
	        ListaException {

		if (isEmpty()) {

			throw new ConjuntoException("Arreglo lleno.");

		}

		for (int i = 0; i < indice; i++) {

			if (Utilidades.igual(datos[i], elemento)) {

				for (int j = i; j < indice - 1; j++) {

					datos[j] = datos[j + 1];

				}

				indice--;

				break;

			}

		}

		// }

	}

	@Override
	public boolean existe(Object elemento) throws ConjuntoException,
	        ListaException {

		for (int i = 0; i < datos.length; i++) {

			if (Utilidades.igual(datos[i], elemento)) {

				return true;

			}

		}

		return false;
	}

	@Override
	public Conjuntable union(Conjuntable a, Conjuntable b)
	        throws ConjuntoException, ListaException {

		if (a.isEmpty() && b.isEmpty()) {
			throw new ConjuntoException(
			        "La unión de 2 conjuntos vacíos es CONJUNTO VACÍO.");
		}

		Conjuntable resultante = new ConjuntoArreglo(a.getSize() + b.getSize());

		// Agregamos todos los elementos a

		for (int i = 0; i < a.getSize(); i++) {

			resultante.insertar(a.getElemento(i));

		}

		// Agregamos todos los elementos b

		for (int i = 0; i < b.getSize(); i++) {

			resultante.insertar(b.getElemento(i));

		}

		return resultante;
	}

	@Override
	public Conjuntable interseccion(Conjuntable a, Conjuntable b)
	        throws ConjuntoException, ListaException {

		if (a.isEmpty() || b.isEmpty()) {

			throw new ConjuntoException(
			        "Uno de los 2 conjuntos es vacío. La intersección con un conjunto vacío es vacío.");

		}

		int contador = 0;
		Conjuntable resultante = null;

		if (a.getSize() <= b.getSize()) {

			for (int i = 0; i < a.getSize(); i++) {

				if (b.existe(a.getElemento(i))) {

					contador++;

				}

			}

			resultante = new ConjuntoArreglo(contador);

			for (int i = 0; i < a.getSize(); i++) {

				if (b.existe(a.getElemento(i))) {

					resultante.insertar(a.getElemento(i));

				}

			}

		}
		else {

			for (int i = 0; i < b.getSize(); i++) {

				if (a.existe(b.getElemento(i))) {

					contador++;

				}

			}

			resultante = new ConjuntoArreglo(contador);

			for (int i = 0; i < b.getSize(); i++) {

				if (a.existe(b.getElemento(i))) {

					resultante.insertar(b.getElemento(i));

				}

			}

		}

		return resultante;
	}

	@Override
	public Conjuntable diferencia(Conjuntable a, Conjuntable b)
	        throws ConjuntoException, ListaException {

		if (a.isEmpty()) {

			throw new ConjuntoException("El conjunto está vacío.");

		}

		if (b.isEmpty()) {

			return a;

		}

		int contador = 0;

		for (int i = 0; i < a.getSize(); i++) {

			if (!b.existe(a.getElemento(i))) {

				contador++;

			}

		}

		Conjuntable resultante = new ConjuntoArreglo(contador);

		for (int i = 0; i < a.getSize(); i++) {

			if (!b.existe(a.getElemento(i))) {

				resultante.insertar(a.getElemento(i));

			}

		}

		return resultante;
	}

	public Conjuntable complemento(ConjuntoArreglo A, ConjuntoArreglo U)
	        throws ConjuntoException, ListaException {

		if (U.isEmpty()) {

			throw new ConjuntoException("El conjunto universo está vacío");

		}

		if (A.isEmpty()) {

			return U;

		}

		int contador = 0;

		for (int i = 0; i < U.getSize(); i++) {

			if (!A.existe(U.getElemento(i))) {

				contador++;

			}

		}

		Conjuntable resultante = new ConjuntoArreglo(contador);

		for (int i = 0; i < U.getSize(); i++) {

			if (!A.existe(U.getElemento(i))) {

				resultante.insertar(U.getElemento(i));

			}

		}

		return resultante;
	}

	public String productoCartesiano(ConjuntoArreglo a, ConjuntoArreglo b)
	        throws ConjuntoException, ListaException {

		if (a.isEmpty() || b.isEmpty()) {

			throw new ConjuntoException("Algún conjunto está vacío.");

		}

		String result = "";

		for (int i = 0; i < a.getSize(); i++) {
			for (int j = 0; j < b.getSize(); j++) {
				result += "(" + a.getElemento(i) + "," + b.getElemento(j)
				        + ") ";
			}
		}

		return result;
	}

	@Override
	public String toString() {

		String result = "";

		for (int i = 0; i < indice; i++) {

			result += datos[i];

			if (i + 1 < indice) {
				result += "-";
			}

		}

		return result;
	}

}
