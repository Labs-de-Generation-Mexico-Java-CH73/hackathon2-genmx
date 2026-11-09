package com.example.agenda.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class ContactoPersonalTest {

    private ContactoPersonal contactoPersonal;

    @BeforeEach
    void setUp() {
        contactoPersonal = new ContactoPersonal("Ana", "García", "6445551234", "18/04/1997");
    }

    @Test
    @DisplayName("Debe inicializar y retornar la fecha de cumpleaños correctamente")
    void testGetFechaDeCumple() {
        assertEquals("Ana", contactoPersonal.getNombre());
        assertEquals("García", contactoPersonal.getApellido());
        assertEquals("6445551234", contactoPersonal.getTelefono());
        assertEquals("18/04/1997", contactoPersonal.getFechaDeCumple());
    }

    @Test
    @DisplayName("Debe modificar la fecha de cumpleaños (Setter)")
    void testSetFechaDeCumple() {
        contactoPersonal.setFechaDeCumple("25/12/2000");
        assertEquals("25/12/2000", contactoPersonal.getFechaDeCumple());
    }

    @Test
    @DisplayName("Debe generar el formato toString() incluyendo la fecha de cumpleaños")
    void testToString() {
        String str = contactoPersonal.toString();
        assertNotNull(str);
        assertTrue(str.contains("18/04/1997"));
    }
}