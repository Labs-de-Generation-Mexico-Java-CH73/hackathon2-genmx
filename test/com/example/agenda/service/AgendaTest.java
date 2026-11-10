// declaracion del paquete donde se ubican los servicios y sus pruebas.
package com.example.agenda.service;

// importacion del modelo abstracto base y sus subclases concretas.
import com.example.agenda.model.Contacto;
import com.example.agenda.model.ContactoPersonal;
import com.example.agenda.model.ContactoEmpresarial;

// importaciones del framework junit 5.
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

// importacion estatica de utilidades de asercion.
import static org.junit.jupiter.api.Assertions.*;

// clase de prueba unitaria encargada de evaluar la logica de negocio de la clase agenda.
class AgendaTest {

    // atributos para instanciar la agenda y contactos de prueba.
    private Agenda agenda;
    private Contacto c1;
    private Contacto c2;

    // inicializacion del entorno antes de cada ejecucion de prueba.
    @BeforeEach
    void setUp() {
        // instanciacion de una agenda con capacidad reducida fija de 3 elementos para facilitar las pruebas de limites.
        agenda = new Agenda(3);
        // principio de polimorfismo. asignacion de objetos de las subclases contactopersonal y contactoempresarial a variables de tipo contacto.
        c1 = new ContactoPersonal("María", "López", "6441112233", "01/01/1995");
        c2 = new ContactoEmpresarial("Pedro", "Sánchez", "6444445566", "Google", "Manager");
    }

    // prueba unitaria para la adicion exitosa de un contacto en el arreglo de la agenda.
    @Test
    @DisplayName("Debe añadir un contacto exitosamente")
    void testAnadirContacto() {
        // asercion asserttrue asegura que el metodo anadircontacto devuelva verdadero al registrar.
        assertTrue(agenda.anadirContacto(c1));
        // verifica que tras insertar un elemento los espacios libres se reduzcan a dos.
        assertEquals(2, agenda.espacioLibres());
    }

    // prueba unitaria para verificar la existencia previa de un contacto en la estructura.
    @Test
    @DisplayName("Debe identificar si un contacto ya existe en la agenda")
    void testExisteContacto() {
        agenda.anadirContacto(c1);
        // asserttrue comprueba que c1 ya registrado sea detectado.
        assertTrue(agenda.existeContacto(c1));
        // assertfalse comprueba que c2 aun no agregado devuelva falso.
        assertFalse(agenda.existeContacto(c2));
    }

    // prueba unitaria para la eliminacion de contactos y reordenamiento del arreglo estatico.
    @Test
    @DisplayName("Debe eliminar un contacto existente de la agenda")
    void testEliminarContacto() {
        agenda.anadirContacto(c1);
        // confirma que la operacion de eliminacion retorna verdadero.
        assertTrue(agenda.eliminarContacto(c1));
        // confirma que el contacto c1 ya no existe en la agenda.
        assertFalse(agenda.existeContacto(c1));
        // verifica que la cantidad de espacios libres vuelva a ser la capacidad inicial de tres.
        assertEquals(3, agenda.espacioLibres());
    }

    // prueba unitaria para la modificacion de datos de un registro.
    @Test
    @DisplayName("Debe modificar el teléfono de un contacto existente")
    void testModificarTelefono() {
        agenda.anadirContacto(c1);
        boolean modificado = agenda.modificarTelefono("María", "López", "6449990000");
        assertTrue(modificado);
        // valida que el estado interno del objeto c1 haya sido actualizado correctamente.
        assertEquals("6449990000", c1.getTelefono());
    }

    // prueba unitaria para probar la capacidad limite y desbordamiento del arreglo.
    @Test
    @DisplayName("Debe detectar correctamente cuando la agenda está llena")
    void testAgendaLlena() {
        Contacto c3 = new ContactoPersonal("José", "Ramírez", "6447778899", "10/05/1990");

        // llenado del arreglo hasta su capacidad maxima de 3 elementos.
        agenda.anadirContacto(c1);
        agenda.anadirContacto(c2);
        agenda.anadirContacto(c3);

        // verifica que agendallena devuelva verdadero al alcanzar el límite.
        assertTrue(agenda.agendaLlena());
        // confirma que ya no quedan posiciones desocupadas.
        assertEquals(0, agenda.espacioLibres());

        // prueba del caso de desbordamiento. intentar insertar un cuarto elemento en una agenda de tamano 3 debe retornar falso.
        Contacto cExtra = new ContactoPersonal("Lucía", "Torres", "6440001122", "15/08/1998");
        assertFalse(agenda.anadirContacto(cExtra));
    }

    // prueba unitaria para calcular los espacios libres restantes en diferentes momentos.
    @Test
    @DisplayName("Debe calcular los espacios libres restantes de forma exacta")
    void testEspacioLibres() {
        // estado inicial agenda vacia.
        assertEquals(3, agenda.espacioLibres());
        // estado tras agregar un elemento.
        agenda.anadirContacto(c1);
        assertEquals(2, agenda.espacioLibres());
    }
}