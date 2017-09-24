package TDA;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ArbolException extends Exception implements Serializable{

	public ArbolException(String err) {

		super(err);
	}
}
