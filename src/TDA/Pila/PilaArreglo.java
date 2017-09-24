package TDA;

public class PilaArreglo implements Pila, Cloneable {

	private int tope;
	private Object[] pila;

	public PilaArreglo(int n) {

		if (n <= 0) {

			System.exit(0);

		}

		pila = new Object[n];
		tope = -1;

	}

	@Override
	public void anular() {

		this.tope = -1;

	}

	@Override
	public int getSize() {

		return tope + 1;
	}

	@Override
	public boolean isEmpty() {

		return tope == -1;
	}

	@Override
	public Object top() throws PilaVaciaException {

		if (isEmpty()) {

			throw new PilaVaciaException("La pila se encuentra vacía.");

		}

		return pila[tope];
	}

	@Override
	public void push(Object elemento) throws PilaLlenaException {

		if (getSize() == pila.length) {

			throw new PilaLlenaException("La pila se encuentra llena.");

		}

		pila[++tope] = elemento;

	}

	@Override
	public Object pop() throws PilaVaciaException {

		if (isEmpty()) {

			throw new PilaVaciaException("La pila se encuentra vacía.");

		}

		return pila[tope--];
	}

	@Override
	public String toString() {

		String result = "";

		if (isEmpty()) {

			result = "La pila se encuentra vacía.";

		}
		else {

			try {

				PilaArreglo clone = (PilaArreglo) clone();

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

	// MÉTODOS ADICIONALES

	public Object clone() throws CloneNotSupportedException {

		return super.clone();
	}

}
