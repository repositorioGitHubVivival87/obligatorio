/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package log;

import org.apache.log4j.Logger;

/**
 *
 * @author Agustina
 */


public class Escribir {

    private static final Logger log = Logger.getLogger(Escribir.class);    
    String url = "";

    
    public Escribir() {
        
    }
    
    public static void logInfo(String texto) {
        log.info(texto);
    }
    
    public static void logError(String texto) {
        log.error(texto);
    }
    
    public static void logWarning(String texto) {
        log.warn(texto);
    }

}
