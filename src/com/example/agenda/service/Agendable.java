package com.example.agenda.service;

import com.example.agenda.model.Contacto;

public interface Agendable {
    boolean anadirContacto(Contacto c);
    boolean existeContacto(Contacto c);
    void listarContactos();
    void buscaContacto(String nombre, String apellido);
    boolean eliminarContacto(Contacto c);
    boolean modificarTelefono(String nombre, String apellido, String nuevoTelefono);
    boolean agendaLlena();
    int espacioLibres();
}