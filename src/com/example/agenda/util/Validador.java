package com.example.agenda.util;

public class Validador {

    public static boolean esValido(String texto) {
        if (texto == null) {
            return false;
        }
        String textoLimpio = texto.trim();
        return !textoLimpio.isEmpty();
    }
}