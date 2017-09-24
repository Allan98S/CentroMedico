/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import TDA.Utilidades;

/**
 *
 * @author allan
 */
public class Factura {

    Utilidades u = new Utilidades();
    private String consecutivo;
    private Medico medico;
    private Asistente asistente;
    private ServicioMedico servicioMedico;

    private static int contador = 0;

    public Factura(Medico medico, Asistente asistente, ServicioMedico descripcion) {
        this.consecutivo = u.formatoNumero(++contador);
        this.medico = medico;
        this.asistente = asistente;
        this.servicioMedico = descripcion;
       
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Asistente getAsistente() {
        return asistente;
    }

    public void setAsistente(Asistente asistente) {
        this.asistente = asistente;
    }

    public ServicioMedico getDescripcion() {
        return servicioMedico;
    }

    public void setDescripcion(ServicioMedico descripcion) {
        this.servicioMedico = descripcion;
    }

   

    @Override
    public String toString() {
      String mensaje="FACTURA HOSPTIAL CIMA";
      mensaje+="Número de factura : "+ consecutivo+"\nMédico : "+medico+"\nAsistente: "+asistente+"\nServicio Medico : "+
              servicioMedico.toString();
      return mensaje;
    }

}