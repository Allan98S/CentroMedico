package TDA;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilidades implements Serializable {

    public static int comparar(Object a, Object b) {

        int resultado = 0;

        if (a instanceof Integer && b instanceof Integer) {

            if ((int) a < (int) b) {

                resultado = -1;

            } else if ((int) a > (int) b) {

                resultado = 1;

            } else {

                resultado = 0;

            }

        } else if (a instanceof String && b instanceof String) {

            if (((String) a).compareToIgnoreCase((String) b) < 0) {

                resultado = -1;

            } else if (((String) a).compareToIgnoreCase((String) b) > 0) {

                resultado = 1;

            } else {

                resultado = 0;

            }

        } else if (a instanceof Character && b instanceof Character) {

            if ((char) a < (char) b) {

                resultado = -1;

            } else if ((char) a > (char) b) {

                resultado = 1;

            } else {

                resultado = 0;

            }

        }

        return resultado;

    }

    public static boolean mayorQue(Object a, Object b) {

        boolean menor = false;

        if (a instanceof Integer && b instanceof Integer) {

            menor = (int) a > (int) b;

        } else if (a instanceof String && b instanceof String) {

            menor = String.valueOf(a).compareToIgnoreCase(String.valueOf(b)) > 0;

        } else if (a instanceof Character && b instanceof Character) {

            menor = (char) a > (char) b;

        }

        return menor;
    }

    public static boolean menorQue(Object a, Object b) {

        boolean menor = false;

        if (a instanceof Integer && b instanceof Integer) {

            menor = (int) a < (int) b;

        } else if (a instanceof String && b instanceof String) {

            menor = String.valueOf(a).compareToIgnoreCase(String.valueOf(b)) < 0;

        } else if (a instanceof Character && b instanceof Character) {

            menor = (char) a < (char) b;

        }

        return menor;
    }

    public static Object minimo(Object a, Object b) {

        Object min = null;

        if (a instanceof Integer && b instanceof Integer) {

            if ((int) a <= (int) b) {

                min = a;

            } else {

                min = b;

            }

        }

        if (a instanceof String && b instanceof String) {

            if (String.valueOf(a).compareToIgnoreCase(String.valueOf(b)) < 0) {

                min = a;

            } else {

                min = b;

            }

        } else if (a instanceof Character && b instanceof Character) {

            if ((char) a <= (char) b) {

                min = a;

            } else {

                min = b;

            }

        }

        return min;
    }

    public static Object maximo(Object a, Object b) {

        Object max = null;

        if (a instanceof Integer && b instanceof Integer) {

            if ((int) a > (int) b) {

                max = a;

            } else {

                max = b;

            }

        } else if (a instanceof String && b instanceof String) {

            if (String.valueOf(a).compareToIgnoreCase(String.valueOf(b)) > 0) {

                max = a;

            } else {

                max = b;

            }

        } else if (a instanceof Character && b instanceof Character) {

            if ((char) a > (char) b) {

                max = a;

            } else {

                max = b;

            }

        }

        return max;
    }

    public static int Random(int n) {

        return (int) Math.floor(Math.random() * n);
    }

    public static String formatoMonedaColon(double valor) {

        DecimalFormat formato = new DecimalFormat("₡#,###,###.##");

        return formato.format(valor);

    }

//    public static String formatoMonedaDolar(double valor) {
//
//        DecimalFormat formato = new DecimalFormat("$#,###,###.##");
//
//        return formato.format(valor);
//
//    }
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

    public static boolean igual(Object elementoA, Object elementoB) {

        boolean iguales = false;

        if (elementoA instanceof String && elementoB instanceof String) {

            iguales = String.valueOf(elementoA).equalsIgnoreCase(
                    String.valueOf(elementoB));

        } else if (elementoA instanceof Integer && elementoB instanceof Integer) {

            iguales = (int) elementoA == (int) elementoB;

        } else if (elementoA instanceof Character
                && elementoB instanceof Character) {

            iguales = (char) elementoA == (char) elementoB;

        } else {

            iguales = elementoA.equals(elementoB);

        }

        return iguales;

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

    public String formatoNumero(int num) {
        String numero = "";
        if (num < 10) {
            numero = "00" + num++;
        } else {
            numero = "0" + num++;
        }
        return numero;
    }

    public static String formatoMonedaDolar(double valor) {
        DecimalFormat formato = new DecimalFormat("$#,###,###.##");

        return formato.format(valor);
    }


}
