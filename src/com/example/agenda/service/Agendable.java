// declaracion del paquete de servicios.
package com.example.agenda.service;

// importacion del modelo base de contacto.
import com.example.agenda.model.Contacto;

// definicion de una interfaz llamada agendable.
// la interfaz representa un contrato formal que especifica que metodos debe implementar cualquier clase que sea agendable.
// no contiene codigo de ejecucion sino las firmas de las operaciones permitidas.
public interface Agendable {
    // firma para anadir un contacto.
    boolean anadirContacto(Contacto c);
    // firma para verificar la existencia de un contacto.
    boolean existeContacto(Contacto c);
    // firma para mostrar todos los contactos.
    void listarContactos();
    // firma para buscar un contacto especifico.
    void buscaContacto(String nombre, String apellido);
    // firma para borrar un contacto de la estructura de datos.
    boolean eliminarContacto(Contacto c);
    // firma para actualizar el numero telefónico.
    boolean modificarTelefono(String nombre, String apellido, String nuevoTelefono);
    // firma para verificar si se alcanzo el limite de almacenamiento.
    boolean agendaLlena();
    // firma para consultar cuantas posiciones quedan vacias.
    int espacioLibres();
}