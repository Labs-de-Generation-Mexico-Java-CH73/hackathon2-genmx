package com.example.agenda.model;

public class ContactoEmpresarial extends Contacto {
    private String empresa;
    private String cargo;

    public ContactoEmpresarial(String nombre, String apellido, String telefono, String empresa, String cargo) {
        super(nombre, apellido, telefono);
        this.empresa = empresa;
        this.cargo = cargo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "[Empresarial] " + super.toString() + ", Empresa: " + empresa + ", Cargo: " + cargo;
    }
}