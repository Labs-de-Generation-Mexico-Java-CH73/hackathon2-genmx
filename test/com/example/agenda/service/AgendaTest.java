package com.example.agenda.service;

import com.example.agenda.model.Contacto;
import com.example.agenda.model.ContactoPersonal;
import com.example.agenda.model.ContactoEmpresarial;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class AgendaTest {

    private Agenda agenda;
    private Contacto c1;
    private Contacto c2;

    @BeforeEach
    void setUp() {
        agenda = new Agenda(3); // Agenda con capacidad para 3 elementos para facilitar pruebas de límite
        c1 = new ContactoPersonal("María", "López", "6441112233", "01/01/1995");
        c2 = new ContactoEmpresarial("Pedro", "Sánchez", "6444445566", "Google", "Manager");
    }

    @Test
    @DisplayName("Debe añadir un contacto exitosamente")
    void testAnadirContacto() {
        assertTrue(agenda.anadirContacto(c1));
        assertEquals(2, agenda.espacioLibres());
    }

    @Test
    @DisplayName("Debe identificar si un contacto ya existe en la agenda")
    void testExisteContacto() {
        agenda.anadirContacto(c1);
        assertTrue(agenda.existeContacto(c1));
        assertFalse(agenda.existeContacto(c2));
    }

    @Test
    @DisplayName("Debe eliminar un contacto existente de la agenda")
    void testEliminarContacto() {
        agenda.anadirContacto(c1);
        assertTrue(agenda.eliminarContacto(c1));
        assertFalse(agenda.existeContacto(c1));
        assertEquals(3, agenda.espacioLibres());
    }

    @Test
    @DisplayName("Debe modificar el teléfono de un contacto existente")
    void testModificarTelefono() {
        agenda.anadirContacto(c1);
        boolean modificado = agenda.modificarTelefono("María", "López", "6449990000");
        assertTrue(modificado);
        assertEquals("6449990000", c1.getTelefono());
    }

    @Test
    @DisplayName("Debe detectar correctamente cuando la agenda está llena")
    void testAgendaLlena() {
        Contacto c3 = new ContactoPersonal("José", "Ramírez", "6447778899", "10/05/1990");

        agenda.anadirContacto(c1);
        agenda.anadirContacto(c2);
        agenda.anadirContacto(c3);

        assertTrue(agenda.agendaLlena());
        assertEquals(0, agenda.espacioLibres());

        // Intentar agregar un cuarto elemento a una agenda de tamaño 3 debe retornar false
        Contacto cExtra = new ContactoPersonal("Lucía", "Torres", "6440001122", "15/08/1998");
        assertFalse(agenda.anadirContacto(cExtra));
    }

    @Test
    @DisplayName("Debe calcular los espacios libres restantes de forma exacta")
    void testEspacioLibres() {
        assertEquals(3, agenda.espacioLibres());
        agenda.anadirContacto(c1);
        assertEquals(2, agenda.espacioLibres());
    }
}