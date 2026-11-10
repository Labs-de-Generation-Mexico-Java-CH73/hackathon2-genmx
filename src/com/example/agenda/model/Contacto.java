// declaracion del paquete donde se ubica esta clase dentro de la estructura del proyecto.
package com.example.agenda.model;

// importacion de la clase utilitaria objects para comparar atributos sin riesgos de errores por nulos.
import java.util.Objects;

// declaracion de una clase abstracta llamada contacto.
// esta clase aplica el concepto de abstraccion ya que sirve como plantilla base y no se puede instanciar directamente.
// implementa la interfaz comparable para permitir ordenamiento natural de instancias de contacto o sus subclases.
public abstract class Contacto implements Comparable<Contacto> {
    // definicion de atributos privados aplicando el principio de encapsulamiento.
    // los atributos solo son accesibles a traves de los metodos getter y setter.
    private String nombre;
    private String apellido;
    private String telefono;

    // constructor de la clase base.
    // permite inicializar el estado del objeto con nombre, apellido y telefono al momento de su creacion.
    public Contacto(String nombre, String apellido, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    // metodo getter para obtener el valor del atributo privado nombre.
    public String getNombre() {
        return nombre;
    }

    // metodo getter para obtener el valor del atributo privado apellido.
    public String getApellido() {
        return apellido;
    }

    // metodo getter para obtener el valor del atributo privado telefono.
    public String getTelefono() {
        return telefono;
    }

    // metodo setter para modificar de forma controlada el atributo privado telefono.
    public void setTelefono(String nuevoTelefono) {
        this.telefono = nuevoTelefono;
    }

    // sobreescritura de metodo heredado de la clase base object.
    // aplica polimorfismo al redefinir como se determina la igualdad entre dos objetos de tipo contacto.
    @Override
    public boolean equals(Object o) {
        // si se compara la misma referencia en memoria retorna verdadero.
        if (this == o) return true;
        // si el objeto a comparar es nulo o de una clase diferente retorna falso.
        if (o == null || getClass() != o.getClass()) return false;
        // casteo explícito del objeto genérico a tipo contacto para acceder a sus atributos.
        Contacto contacto = (Contacto) o;
        // dos contactos se consideran iguales si tienen el mismo nombre y el mismo apellido.
        return Objects.equals(nombre, contacto.nombre) &&
                Objects.equals(apellido, contacto.apellido);
    }

    // sobreescritura de metodo hashCode de la clase object para mantener consistencia con equals.
    @Override
    public int hashCode() {
        // genera un codigo hash basado en el nombre y el apellido.
        return Objects.hash(nombre, apellido);
    }

    // sobreescritura del metodo compareTo de la interfaz comparable.
    // establece la regla para ordenar contactos alfabeticamente por apellido y luego por nombre.
    @Override
    public int compareTo(Contacto o) {
        // compara el apellido sin distinguir entre mayusculas y minusculas.
        int cmpApellido = this.apellido.compareToIgnoreCase(o.apellido);
        if (cmpApellido != 0) {
            return cmpApellido;
        }
        // si los apellidos son iguales compara el nombre sin distinguir mayusculas de minusculas.
        return this.nombre.compareToIgnoreCase(o.nombre);
    }

    // sobreescritura de metodo toString heredado de object para ofrecer una representacion legible en texto.
    @Override
    public String toString() {
        return "Nombre: " + nombre + " " + apellido + ", Teléfono: " + telefono;
    }
}