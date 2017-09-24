package TDA.Listas;

import java.io.Serializable;

public class Nodo implements Serializable {

    //Atributos
    public Object elemento; //informacion del nodo
    public Nodo ant, sgte; //apuntador

    //Constructor No. 1
    public Nodo(Object elemento) {
        this.elemento = elemento;
        this.ant = this.sgte = null;
    }//Constructor
    
    
    
}//fin de la clase Nodo
