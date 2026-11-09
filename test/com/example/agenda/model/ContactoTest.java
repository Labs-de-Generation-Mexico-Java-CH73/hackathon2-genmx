package com.example.agenda.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class ContactoTest {

    // Subclase concreta auxiliar solo para probar los métodos heredados de la clase abstracta
    private static class ContactoImpl extends Contacto {
        public ContactoImpl(String nombre, String apellido, String telefono) {
            super(nombre, apellido, telefono);
        }
    }

    private Contacto contacto1;
    private Contacto contacto2;
    private Contacto contactoDiferente;

    @BeforeEach
    void setUp() {
        contacto1 = new ContactoImpl("Juan", "Pérez", "6441234567");
        contacto2 = new ContactoImpl("Juan", "Pérez", "6441234567");
        contactoDiferente = new ContactoImpl("Carlos", "Estrada", "6449876543");
    }

    @Test
    @DisplayName("Debe obtener los datos iniciales correctamente (Getters)")
    void testGetters() {
        assertEquals("Juan", contacto1.getNombre());
        assertEquals("Pérez", contacto1.getApellido());
        assertEquals("6441234567", contacto1.getTelefono());
    }

    @Test
    @DisplayName("Debe actualizar el teléfono correctamente (Setter)")
    void testSetTelefono() {
        contacto1.setTelefono("6440000000");
        assertEquals("6440000000", contacto1.getTelefono());
    }

    @Test
    @DisplayName("Debe evaluar la igualdad de objetos con equals()")
    void testEquals() {
        assertEquals(contacto1, contacto2);
        assertNotEquals(contacto1, contactoDiferente);
        assertNotEquals(contacto1, null);
    }

    @Test
    @DisplayName("Debe generar el mismo hashCode para objetos equivalentes")
    void testHashCode() {
        assertEquals(contacto1.hashCode(), contacto2.hashCode());
    }

    @Test
    @DisplayName("Debe comparar orden alfabético correctamente con compareTo()")
    void testCompareTo() {
        assertTrue(contactoDiferente.compareTo(contacto1) < 0); // "Estrada" va antes que "Pérez"
        assertEquals(0, contacto1.compareTo(contacto2)); // Mismos datos
    }

    @Test
    @DisplayName("Debe retornar la representación en String del contacto")
    void testToString() {
        String resultado = contacto1.toString();
        assertNotNull(resultado);
        assertTrue(resultado.contains("Juan"));
        assertTrue(resultado.contains("Pérez"));
    }
}