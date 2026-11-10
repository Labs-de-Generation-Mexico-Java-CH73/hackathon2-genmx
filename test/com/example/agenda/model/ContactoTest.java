// declaracion del paquete correspondiente a los modelos.
package com.example.agenda.model;

// importaciones de las anotaciones junit 5 jupiter para estructurar pruebas unitarias.
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

// importacion estatica de las aserciones de junit para verificar los resultados esperados.
import static org.junit.jupiter.api.Assertions.*;

// clase de pruebas unitarias para probar la clase abstracta contacto.
class ContactoTest {

    // declaracion de una subclase privada interna y estatica que extiende de contacto.
    // concepto clave: clase auxiliar concreta.
    // dado que la clase contacto es abstracta y no se puede instanciar directamente con new contacto,
    // creamos esta implementacion temporal para probar los metodos concretos heredados de la clase base.
    private static class ContactoImpl extends Contacto {
        // constructor de la clase auxiliar que invoca al constructor de la clase abstracta con super.
        public ContactoImpl(String nombre, String apellido, String telefono) {
            super(nombre, apellido, telefono);
        }
    }

    // variables de instancia para almacenar los objetos de prueba.
    private Contacto contacto1;
    private Contacto contacto2;
    private Contacto contactoDiferente;

    // anotacion beforeeach de junit 5.
    // este metodo se ejecuta automaticamente antes de cada una de las pruebas unitarias test.
    // sirve para reiniciar el estado de las variables y garantizar independencia entre pruebas.
    @BeforeEach
    void setUp() {
        // instanciacion de objetos usando la subclase concreta auxiliar contactoimpl.
        contacto1 = new ContactoImpl("Juan", "Pérez", "6441234567");
        contacto2 = new ContactoImpl("Juan", "Pérez", "6441234567");
        contactoDiferente = new ContactoImpl("Carlos", "Estrada", "6449876543");
    }

    // anotacion test marca este metodo como una prueba unitaria ejecutable por el marco junit.
    // anotacion displayname asigna un nombre descriptivo para los reportes de ejecucion.
    @Test
    @DisplayName("Debe obtener los datos iniciales correctamente (Getters)")
    void testGetters() {
        // asercion assertequals verifica que el valor retornado por el getter coincida exactamente con el valor esperado.
        assertEquals("Juan", contacto1.getNombre());
        assertEquals("Pérez", contacto1.getApellido());
        assertEquals("6441234567", contacto1.getTelefono());
    }

    // prueba unitaria para verificar la modificacion de atributos encapsulados mediante setters.
    @Test
    @DisplayName("Debe actualizar el teléfono correctamente (Setter)")
    void testSetTelefono() {
        // ejecucion del metodo setter a probar.
        contacto1.setTelefono("6440000000");
        // verificacion del cambio de estado.
        assertEquals("6440000000", contacto1.getTelefono());
    }

    // prueba unitaria para validar la sobreescritura del metodo equals.
    @Test
    @DisplayName("Debe evaluar la igualdad de objetos con equals()")
    void testEquals() {
        // verifica que dos objetos con el mismo nombre y apellido se consideren iguales equivalencia logica.
        assertEquals(contacto1, contacto2);
        // asercion assertnotequals verifica que dos objetos con distinto nombre o apellido sean diferentes.
        assertNotEquals(contacto1, contactoDiferente);
        // verifica la proteccion contra comparaciones con valores nulos.
        assertNotEquals(contacto1, null);
    }

    // prueba unitaria para validar la coherencia de hashcode con equals.
    @Test
    @DisplayName("Debe generar el mismo hashCode para objetos equivalentes")
    void testHashCode() {
        // si dos objetos son iguales por equals obligatoriamente deben generar el mismo valor hashcode.
        assertEquals(contacto1.hashCode(), contacto2.hashCode());
    }

    // prueba unitaria para evaluar la implementacion del metodo compareto de la interfaz comparable.
    @Test
    @DisplayName("Debe comparar orden alfabético correctamente con compareTo()")
    void testCompareTo() {
        // asercion asserttrue valida que el resultado sea un entero negativo indicando que estrada va antes que perez.
        assertTrue(contactoDiferente.compareTo(contacto1) < 0);
        // verifica que comparar dos objetos equivalentes retorne cero indicando misma posicion alfabetica.
        assertEquals(0, contacto1.compareTo(contacto2));
    }

    // prueba unitaria para verificar la representacion en texto dada por el metodo tostring.
    @Test
    @DisplayName("Debe retornar la representación en String del contacto")
    void testToString() {
        String resultado = contacto1.toString();
        // asercion assertnotnull comprueba que la cadena generada no sea nula.
        assertNotNull(resultado);
        // verifica que la cadena contenga las subcadenas esperadas.
        assertTrue(resultado.contains("Juan"));
        assertTrue(resultado.contains("Pérez"));
    }
}