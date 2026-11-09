package com.example.agenda.model;

import java.util.Objects;

public abstract class Contacto implements Comparable<Contacto> {
    private String nombre;
    private String apellido;
    private String telefono;

    public Contacto(String nombre, String apellido, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String nuevoTelefono) {
        this.telefono = nuevoTelefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacto contacto = (Contacto) o;
        return Objects.equals(nombre, contacto.nombre) &&
                Objects.equals(apellido, contacto.apellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido);
    }

    @Override
    public int compareTo(Contacto o) {
        int cmpApellido = this.apellido.compareToIgnoreCase(o.apellido);
        if (cmpApellido != 0) {
            return cmpApellido;
        }
        return this.nombre.compareToIgnoreCase(o.nombre);
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + " " + apellido + ", Teléfono: " + telefono;
    }
}