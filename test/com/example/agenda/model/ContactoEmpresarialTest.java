package com.example.agenda.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class ContactoEmpresarialTest {

    private ContactoEmpresarial contactoEmpresarial;

    @BeforeEach
    void setUp() {
        contactoEmpresarial = new ContactoEmpresarial("Luis", "Mendoza", "6443339999", "Tech Corp", "Desarrollador");
    }

    @Test
    @DisplayName("Debe retornar la empresa y cargo correctamente")
    void testGetters() {
        assertEquals("Tech Corp", contactoEmpresarial.getEmpresa());
        assertEquals("Desarrollador", contactoEmpresarial.getCargo());
    }

    @Test
    @DisplayName("Debe permitir actualizar la empresa y el cargo")
    void testSetters() {
        contactoEmpresarial.setEmpresa("Innovatech");
        contactoEmpresarial.setCargo("Líder Técnico");

        assertEquals("Innovatech", contactoEmpresarial.getEmpresa());
        assertEquals("Líder Técnico", contactoEmpresarial.getCargo());
    }

    @Test
    @DisplayName("Debe incluir empresa y cargo en el método toString()")
    void testToString() {
        String str = contactoEmpresarial.toString();
        assertNotNull(str);
        assertTrue(str.contains("Tech Corp"));
        assertTrue(str.contains("Desarrollador"));
    }
}