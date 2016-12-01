/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import log.Escribir;

/**
 *
 * @author Vivi
 */
public class Utils {

    private static final Escribir log = new Escribir();
    private static final String modulo = "CLIENT";
    
    public static void logInfo(String mensaje) {
        try {
            
            Escribir.logInfo(" [" + modulo + "] " + mensaje);

        } catch (Exception ex) {
            System.out.println("Error Write Log " + ex.getMessage());
        }
    }

    public static void logWarn(String mensaje) {
        try {
            
            Escribir.logWarning(" [" + modulo + "] " + mensaje);

        } catch (Exception ex) {
            System.out.println("Error Write Log " + ex.getMessage());
        }
    }

    public static void logError(String mensaje) {
        try {
            
            Escribir.logError(" [" + modulo + "] " + mensaje);

        } catch (Exception ex) {
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

    public static String getStringMessageDigest(String message) {
        byte[] digest = null;
        byte[] buffer = message.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.reset();
            messageDigest.update(buffer);
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error creando Digest");
        }
        return toHexadecimal(digest);
    }
    
    private static String toHexadecimal(byte[] digest) {
        String hash = "";
        for (byte aux : digest) {
            int bb = aux & 0xff;
            if (Integer.toHexString(bb).length() == 1) {
                hash += "0";
            }
            hash += Integer.toHexString(bb);
        }
        return hash;
    }
}
