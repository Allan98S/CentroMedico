/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDA.Listas;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Julio
 */
public class MisUtilidades {

    public static int Random() {

        return (int) Math.floor(Math.random() * 100);
    }

    public static String formatoMonedaColon(double valor) {

        DecimalFormat formato = new DecimalFormat("₡#,###,###.##");

        return formato.format(valor);

    }

    public static String formatoMonedaDolar(double valor) {

        DecimalFormat formato = new DecimalFormat("$#,###,###.##");

        return formato.format(valor);

    }

    public static String formatoNumero(double valor) {

        DecimalFormat formato = new DecimalFormat("#,###,###.##");

        return formato.format(valor);

    }

    // muestra el Tn en formato hh:mm:ss
    public static String getTnHHMMSS(long tInicio, long tFin) {

        long milisegundos = tFin - tInicio;
        long hora = milisegundos / 3600000;
        long restohora = milisegundos % 3600000;
        long minuto = restohora / 60000;
        long restominuto = restohora % 60000;
        long segundo = restominuto / 1000;
        long restosegundo = restominuto % 1000;

        return hora + ":" + minuto + ":" + segundo + "." + restosegundo;
    }

    // Obtener hora y fecha con formato
    public static String getFechaHoraActual() {

        Date date = new Date();

        DateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        return f.format(date);
    }

    // Obtener hora con formato
    public static String getHoraActual() {

        Date date = new Date();

        DateFormat f = new SimpleDateFormat("HH:mm:ss");

        return f.format(date);
    }

    // Obtener fecha con formato
    public static String getFechaActual() {

        Date date = new Date();

        DateFormat f = new SimpleDateFormat("dd/MM/yyyy");

        return f.format(date);
    }

    // Da formato a un Date recibido por parámetro IMPRIME FECHA
    public static String formatoFecha(Date fecha) {

        DateFormat f = new SimpleDateFormat("dd/MM/yyyy");

        return f.format(fecha);

    }

    // Da formato a un Date recibido por parámetro IMPRIME HORA Y FECHA
    public static String formatoHoraFecha(Date fecha) {

        DateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        return f.format(fecha);

    }

    // Da formato a un Date recibido por parámetro IMPRIME HORA
    public static String formatoHora(Date fecha) {

        DateFormat f = new SimpleDateFormat("HH:mm:ss");

        return f.format(fecha);

    }

    // Calcula la diferecia de días entre 2 fechas tipo Date recibidas por
    // parámetro
    public static int diferenciaDias(Date inicio, Date fin) {

        // Calendar calendar = new GregorianCalendar();
        // Date trialTime = new Date();
        // calendar.setTime(trialTime);
        GregorianCalendar fechaInicio = new GregorianCalendar();

        fechaInicio.setTime(inicio);

        GregorianCalendar fechaFinal = new GregorianCalendar();

        fechaFinal.setTime(fin);

        long milisegundos = (fechaFinal.getTime()).getTime()
                - (fechaInicio.getTime()).getTime();

        long dias = milisegundos / (24 * 60 * 60 * 1000);

        return (int) dias;

    }

    public static boolean igual(Object a, Object b) {

        boolean iguales = false;

        if (a instanceof String) {

            iguales = (String.valueOf(a).equalsIgnoreCase(String.valueOf(b)));

        }
        if (a instanceof Integer) {

            iguales = ((int) a) == ((int) b);

        }

        return iguales;

    }

    public static boolean menorQue(Object e1, Object e2) {

        boolean menor = false;

        if (e1 instanceof Integer) {

            menor = (int) e1 <= (int) e2;

        }

        if (e1 instanceof String) {

            menor = String.valueOf(e1).compareToIgnoreCase(String.valueOf(e2)) <= 0;

        }

        return menor;
    }

    public static boolean esMayorQ(Object a, Object b) {
        switch (instanceOf(a, b)) {
            case "entero":
                int x = (int) a;
                int y = (int) b;
                return x > y;
            case "string":
                String p = (String) a;
                String q = (String) b;
                return p.compareToIgnoreCase(q) > 0;

        }//switch
        return false;
    }

    //Este metodo devuelve el tipo almacenado en la lista enlazada
    private static String instanceOf(Object a, Object b) {
        if (a instanceof Integer && b instanceof Integer) {
            return "entero";
        }
        if (a instanceof String && b instanceof String) {
            return "string";
        }
//        if (a instanceof Persona && b instanceof Persona) {
//            return "persona";
//        }
        return "desconocido";
    }
}