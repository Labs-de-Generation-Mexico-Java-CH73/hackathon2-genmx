package com.example.agenda.model;

public class ContactoPersonal extends Contacto {
    private String fechaDeCumple;

    public ContactoPersonal(String nombre, String apellido, String telefono, String fechaDeCumple) {
        super(nombre, apellido, telefono);
        this.fechaDeCumple = fechaDeCumple;
    }

    public String getFechaDeCumple() {
        return fechaDeCumple;
    }

    public void setFechaDeCumple(String fechaDeCumple) {
        this.fechaDeCumple = fechaDeCumple;
    }

    @Override
    public String toString() {
        return "[Personal] " + super.toString() + ", Cumpleaños: " + fechaDeCumple;
    }
}