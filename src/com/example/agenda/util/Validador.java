// declaracion del paquete para utilidades del proyecto.
package com.example.agenda.util;

// declaracion de la clase de soporte validador.
public class Validador {

    // metodo estatico de utilidad que evalua si una cadena de texto es valida.
    // al ser un metodo estatico no requiere instanciar la clase validador para ser ejecutado.
    public static boolean esValido(String texto) {
        // proteccion contra cadenas nulas para evitar la excepcion nullpointerexception.
        if (texto == null) {
            return false;
        }
        // eliminacion de espacios en blanco sobrantes al inicio y final de la cadena mediante el metodo trim.
        String textoLimpio = texto.trim();
        // retorna verdadero solo si la cadena procesada no se encuentra completamente vacia.
        return !textoLimpio.isEmpty();
    }
}