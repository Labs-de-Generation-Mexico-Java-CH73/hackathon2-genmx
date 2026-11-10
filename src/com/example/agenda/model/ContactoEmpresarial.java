// declaracion del paquete correspondiente a los modelos.
package com.example.agenda.model;

// definicion de la clase contactoempresarial que extiende de contacto.
// representa otro ejemplo de herencia donde esta subclase reutiliza el comportamiento de contacto.
public class ContactoEmpresarial extends Contacto {
    // atributos específicos exclusivos para los contactos de negocios.
    private String empresa;
    private String cargo;

    // constructor de la clase contactoempresarial.
    public ContactoEmpresarial(String nombre, String apellido, String telefono, String empresa, String cargo) {
        // invocacion al constructor de la clase padre para inicializar nombre, apellido y telefono.
        super(nombre, apellido, telefono);
        // inicializacion de los atributos especificos de esta subclase.
        this.empresa = empresa;
        this.cargo = cargo;
    }

    // getter para la empresa.
    public String getEmpresa() {
        return empresa;
    }

    // setter para la empresa.
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    // getter para el cargo.
    public String getCargo() {
        return cargo;
    }

    // setter para el cargo.
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    // sobreescritura polimorfica del metodo toString para la variante de contacto empresarial.
    // encadena el toString de la clase base con los atributos propios de esta subclase.
    @Override
    public String toString() {
        return "[Empresarial] " + super.toString() + ", Empresa: " + empresa + ", Cargo: " + cargo;
    }
}