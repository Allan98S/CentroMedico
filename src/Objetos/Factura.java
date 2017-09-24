/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author juang
 */
public class Factura {

    private int codigoCita;
    private int codigoPaciente;
    private double monto;
    private int idFactura;
    static int contador = 0;

    public Factura(int codigoCita, int codigoPaciente, double monto, int idFactura) {
        this.codigoCita = codigoCita;
        this.codigoPaciente = codigoPaciente;
        this.monto = monto;
        this.idFactura = contador++;
    }

    public Factura() {
    }

    public int getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(int codigoCita) {
        this.codigoCita = codigoCita;
    }

    public int getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(int codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    @Override
    public String toString() {
        return "Factura{" + "codigoCita=" + codigoCita + ", codigoPaciente=" + codigoPaciente + ", monto=" + monto + ", idFactura=" + idFactura + '}';
    }
}
