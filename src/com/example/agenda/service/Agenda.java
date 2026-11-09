package com.example.agenda.service;

import com.example.agenda.model.Contacto;

public class Agenda implements Agendable {
    private Contacto[] contactos;
    private int contador;

    public Agenda() {
        // Capacidad por defecto de 10 elementos
        this(10);
    }

    public Agenda(int capacidad) {
        this.contactos = new Contacto[capacidad];
        this.contador = 0;
    }

    @Override
    public boolean anadirContacto(Contacto c) {
        if (c == null || agendaLlena() || existeContacto(c)) {
            return false;
        }
        contactos[contador] = c;
        contador++;
        return true;
    }

    @Override
    public boolean existeContacto(Contacto c) {
        if (c == null) return false;
        for (int i = 0; i < contador; i++) {
            if (contactos[i].equals(c)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void listarContactos() {
        if (contador == 0) {
            System.out.println("La agenda está vacía.");
            return;
        }
        for (int i = 0; i < contador; i++) {
            System.out.println((i + 1) + ". " + contactos[i]);
        }
    }

    @Override
    public void buscaContacto(String nombre, String apellido) {
        boolean encontrado = false;
        for (int i = 0; i < contador; i++) {
            if (contactos[i].getNombre().equalsIgnoreCase(nombre) &&
                    contactos[i].getApellido().equalsIgnoreCase(apellido)) {
                System.out.println("Contacto encontrado: " + contactos[i]);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró ningún contacto con ese nombre y apellido.");
        }
    }

    @Override
    public boolean eliminarContacto(Contacto c) {
        if (c == null) return false;
        for (int i = 0; i < contador; i++) {
            if (contactos[i].equals(c)) {
                // Desplazar los elementos para no dejar huecos en el arreglo estático
                for (int j = i; j < contador - 1; j++) {
                    contactos[j] = contactos[j + 1];
                }
                contactos[contador - 1] = null;
                contador--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean modificarTelefono(String nombre, String apellido, String nuevoTelefono) {
        for (int i = 0; i < contador; i++) {
            if (contactos[i].getNombre().equalsIgnoreCase(nombre) &&
                    contactos[i].getApellido().equalsIgnoreCase(apellido)) {
                contactos[i].setTelefono(nuevoTelefono);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean agendaLlena() {
        return contador >= contactos.length;
    }

    @Override
    public int espacioLibres() {
        return contactos.length - contador;
    }
}