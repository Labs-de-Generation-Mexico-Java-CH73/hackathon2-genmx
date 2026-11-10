// declaracion del paquete correspondiente a los modelos.
package com.example.agenda.model;

// importaciones para pruebas unitarias con junit 5.
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

// importacion de aserciones.
import static org.junit.jupiter.api.Assertions.*;

// clase de prueba unitaria para la subclase contactopersonal.
class ContactoPersonalTest {

    // objeto de la clase a probar.
    private ContactoPersonal contactoPersonal;

    // configuracion previa a cada prueba.
    @BeforeEach
    void setUp() {
        // instanciacion directa de la subclase contactopersonal inicializando atributos heredados y propios.
        contactoPersonal = new ContactoPersonal("Ana", "García", "6445551234", "18/04/1997");
    }

    // prueba de herencia y encapsulamiento.
    // verifica que los getters heredados de la clase base contacto y el getter propio de contactopersonal funcionen.
    @Test
    @DisplayName("Debe inicializar y retornar la fecha de cumpleaños correctamente")
    void testGetFechaDeCumple() {
        assertEquals("Ana", contactoPersonal.getNombre());
        assertEquals("García", contactoPersonal.getApellido());
        assertEquals("6445551234", contactoPersonal.getTelefono());
        assertEquals("18/04/1997", contactoPersonal.getFechaDeCumple());
    }

    // prueba del metodo setter exclusivo del atributo fecha de cumpleanos.
    @Test
    @DisplayName("Debe modificar la fecha de cumpleaños (Setter)")
    void testSetFechaDeCumple() {
        contactoPersonal.setFechaDeCumple("25/12/2000");
        assertEquals("25/12/2000", contactoPersonal.getFechaDeCumple());
    }

    // prueba polimorfica del metodo tostring sobreescrito en la subclase contactopersonal.
    @Test
    @DisplayName("Debe generar el formato toString() incluyendo la fecha de cumpleaños")
    void testToString() {
        String str = contactoPersonal.toString();
        assertNotNull(str);
        // confirma que la salida contenga la fecha propia del contacto personal.
        assertTrue(str.contains("18/04/1997"));
    }
}