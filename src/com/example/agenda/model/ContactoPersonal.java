// declaracion del paquete correspondiente a los modelos.
package com.example.agenda.model;

// definicion de la clase contactopersonal que extiende de contacto.
// aqui se aplica el concepto fundamental de herencia usando la palabra reservada extends.
// contactopersonal hereda todos los atributos y metodos de la clase abstracta contacto.
public class ContactoPersonal extends Contacto {
    // atributo especifico de la subclase contactopersonal que no existe en la clase padre.
    private String fechaDeCumple;

    // constructor de la subclase.
    // recibe los datos basicos mas el atributo especifico del contacto personal.
    public ContactoPersonal(String nombre, String apellido, String telefono, String fechaDeCumple) {
        // uso de la palabra reservada super para invocar al constructor de la clase padre contacto.
        // esto reutiliza la logica de asignacion de nombre, apellido y telefono.
        super(nombre, apellido, telefono);
        // asignacion del atributo exclusivo de esta subclase.
        this.fechaDeCumple = fechaDeCumple;
    }

    // metodo getter especifico para obtener la fecha de cumpleanos.
    public String getFechaDeCumple() {
        return fechaDeCumple;
    }

    // metodo setter especifico para modificar la fecha de cumpleanos.
    public void setFechaDeCumple(String fechaDeCumple) {
        this.fechaDeCumple = fechaDeCumple;
    }

    // sobreescritura del metodo toString para aplicar polimorfismo en tiempo de ejecucion.
    // reutiliza el metodo toString de la clase padre mediante super.toString() y anade el dato propio.
    @Override
    public String toString() {
        return "[Personal] " + super.toString() + ", Cumpleaños: " + fechaDeCumple;
    }
}