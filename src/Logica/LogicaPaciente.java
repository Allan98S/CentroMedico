/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Objetos.Cita;
import Objetos.Factura;
import TDA.Listas.ListaCircular;
import TDA.Listas.ListaEnlazada;
import TDA.Listas.ListaException;

/**
 *
 * @author juan
 */
public class LogicaPaciente {

    //citas: agendar modificar cancelar 
    //consultar historial: mostrar todo o indicar con rangos de fechas
    //consultar e imprimir recetas(pdf,excel,impresora)
    //consultar y pagar facturas pendientes bitacora de facturas
    private ListaCircular listaCitas;
    private ListaEnlazada bitacoraPago;
    
    public LogicaPaciente() {
        listaCitas = new ListaCircular();
        bitacoraPago = new ListaEnlazada();
    }
    
    public void agendarCita(Cita cita) throws ListaException {
        if (!listaCitas.existe(cita)) {
            listaCitas.insertar(cita);
        }
        //TODO enviar correo con el tipo de cita, la fecha y hora de la cita 
    }
    
    public void cancelarCita(Cita cita) throws ListaException {
        if (listaCitas.isEmpty()) {
            throw new ListaException("La lista esta vacia ");
            
        }
        if (listaCitas.existe(cita)) {
            listaCitas.suprimir(cita);
        }
        //no encontro la cita y no la elimino
    }
    
    public void modificarCita(Cita cita) throws ListaException {
        if (!listaCitas.existeCita(cita.getCedulaPaciente())) {//cita.getCedulaPaciente()
            throw new ListaException("La cita con el numero de cedula del paciente no se encuentra ");
        } else {
            Cita c = new Cita();
            for (int i = 0; i < listaCitas.getSize(); i++) {
                c = (Cita) listaCitas.getNodo(i).elemento;
                if (c.getCedulaPaciente().equals(cita.getCedulaPaciente())) {
                    listaCitas.suprimir(c);
                    listaCitas.insertar(cita);
                }
            }
        }
    }

    //bitacora de pago
    // cancelada la factura se registra en la bitacora
    public void registraBitacoraFactura(Factura f) {
        if (f.getEsPagada()) {
            bitacoraPago.insertar(f);
        }
    }
}
