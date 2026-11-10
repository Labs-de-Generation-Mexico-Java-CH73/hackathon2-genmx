// declaracion del paquete correspondiente a los modelos.
package com.example.agenda.model;

// importaciones de componentes junit 5.
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

// importacion estatica de funciones de asercion.
import static org.junit.jupiter.api.Assertions.*;

// clase de pruebas unitarias especificas para la subclase contactoempresarial.
class ContactoEmpresarialTest {

    // referencia al objeto bajo prueba.
    private ContactoEmpresarial contactoEmpresarial;

    // metodo de preparacion antes de cada caso de prueba.
    @BeforeEach
    void setUp() {
        // instanciacion de la subclase contactoempresarial.
        contactoEmpresarial = new ContactoEmpresarial("Luis", "Mendoza", "6443339999", "Tech Corp", "Desarrollador");
    }

    // prueba unitaria para verificar los getters de los atributos propios empresa y cargo.
    @Test
    @DisplayName("Debe retornar la empresa y cargo correctamente")
    void testGetters() {
        assertEquals("Tech Corp", contactoEmpresarial.getEmpresa());
        assertEquals("Desarrollador", contactoEmpresarial.getCargo());
    }

    // prueba unitaria para verificar la actualizacion de empresa y cargo mediante sus setters.
    @Test
    @DisplayName("Debe permitir actualizar la empresa y el cargo")
    void testSetters() {
        contactoEmpresarial.setEmpresa("Innovatech");
        contactoEmpresarial.setCargo("Líder Técnico");

        assertEquals("Innovatech", contactoEmpresarial.getEmpresa());
        assertEquals("Líder Técnico", contactoEmpresarial.getCargo());
    }

    // prueba unitaria para comprobar la sobreescritura polimorfica de tostring en la clase contactoempresarial.
    @Test
    @DisplayName("Debe incluir empresa y cargo en el método toString()")
    void testToString() {
        String str = contactoEmpresarial.toString();
        assertNotNull(str);
        // valida que el resultado en texto posea la informacion especifica de la empresa y del cargo.
        assertTrue(str.contains("Tech Corp"));
        assertTrue(str.contains("Desarrollador"));
    }
}