/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package herramientas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Vivi
 */
public class Utiles {

    public static void logWS(String moduleName, String mensaje) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

            Date fecha = new Date();
            String currentDate = dateFormat.format(fecha);
            String currentTime = timeFormat.format(fecha);
            Long threadId = Thread.currentThread().getId();

            String fileoutput = "../logs/" + moduleName + "_" + currentDate + "_" + Long.toString(threadId) + ".log";
            File outputFile = new File(fileoutput);
            BufferedWriter salida = new BufferedWriter(new FileWriter(outputFile, true));
            salida.write(currentTime + " : " + mascararString(mensaje));
            salida.newLine();
            salida.close();

        } catch (IOException ex) {
            System.out.println("Error Write Log " + ex.getMessage());
        }
    }
    
    public static String soloNumerosYletras(String cadena) {
        String salida = "";

        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.substring(i, i + 1).matches("[0-9a-zA-Z]")) {
                salida = salida + cadena.substring(i, i + 1);
            }
        }
        return salida;
    }
    
    public static boolean validoDigito(String cartao) {

        boolean resultado = false;
        int suma = 0;
        int sumando = 0;
        int posicion = 0;

        for (int i = cartao.length(); i > 0; i--) {
            posicion++;
            int digito = Integer.parseInt(cartao.substring(i - 1, i));
            if (posicion != 1) {
                if ((posicion % 2) == 0) {
                    if ((digito * 2) > 9) {
                        sumando = (digito * 2) - 9;
                    } else {
                        sumando = digito * 2;
                    }
                } else {
                    sumando = digito;
                }
                suma = suma + sumando;
            }
        }
        int total = suma * 9;
        String stotal = Integer.toString(total);
        String digitoVerificador = stotal.substring(stotal.length() - 1);

        if (digitoVerificador.equals(cartao.substring(15))) {
            resultado = true;
        }
        return resultado;
    }
    
    public static String mascararString(String cadena) {

        String cadenaOriginal = cadena;
        cadena = soloNumerosYletras(cadenaOriginal);

        String cadenaMascarada = "";
        String cadenaSalida = "";

        if (!(cadena == null)) {
            int pointer = 0;
            while ((pointer + 16) <= cadena.length()) {
                String potencialCartao = cadena.substring(pointer, pointer + 16);
                if (isNumeric(potencialCartao)) {
                    if (potencialCartao.substring(0, 1).equals("4") 
                            || potencialCartao.substring(0, 1).equals("5") 
                            || potencialCartao.substring(0, 1).equals("6")) {
                        if (validoDigito(potencialCartao)) {
                            cadenaMascarada = cadenaMascarada 
                                    + potencialCartao.substring(0, 6) + "******" + potencialCartao.substring(12);
                            pointer = pointer + 16;
                        } else {
                            cadenaMascarada = cadenaMascarada + cadena.substring(pointer, pointer + 1);
                            pointer = pointer + 1;
                        }
                    } else {
                        cadenaMascarada = cadenaMascarada + cadena.substring(pointer, pointer + 1);
                        pointer = pointer + 1;
                    }
                } else {
                    cadenaMascarada = cadenaMascarada + cadena.substring(pointer, pointer + 1);
                    pointer = pointer + 1;
                }
            }
            if (pointer < cadena.length()) {
                cadenaMascarada = cadenaMascarada + cadena.substring(pointer);
            }
            int pos = 0;

            for (int i = 0; i < cadenaOriginal.length(); i++) {
                if (cadenaOriginal.substring(i, i + 1).matches("[0-9a-zA-Z]")) {
                    cadenaSalida = cadenaSalida + cadenaMascarada.substring(pos, pos + 1);
                    pos++;
                } else {
                    cadenaSalida = cadenaSalida + cadenaOriginal.substring(i, i + 1);
                }
            }
        } else {
            cadenaSalida = cadena;
        }

        return cadenaSalida;

    }
    
    public static boolean isNumeric(String cadena) {
        try {
            Long.parseLong(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean isNull(Object... params) {
        boolean retorno = false;
        for (int i = 0; i < params.length; i++) {
            if (params[i] == null || params[i].equals("")) {
                retorno = true;
                break;
            }
        }
        return retorno;
    }

    public static boolean isFechaValida(String fecha) {
        try {
            if (fecha.length() != 10) {
                return false;
            }
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
