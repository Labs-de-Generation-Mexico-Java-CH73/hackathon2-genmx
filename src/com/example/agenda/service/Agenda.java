// declaracion del paquete de servicios.
package com.example.agenda.service;

// importacion de la clase base contacto para manejar arreglos polimorficos.
import com.example.agenda.model.Contacto;

// la clase agenda implementa la interfaz agendable.
// cumple el contrato obligandose a definir el codigo de todos los metodos declarados en agendable.
public class Agenda implements Agendable {
    // arreglo estatico que actua como estructura de almacenamiento en memoria para los contactos.
    // contiene elementos de tipo contacto permitiendo almacenar tanto contactopersonal como contactoempresarial por polimorfismo.
    private Contacto[] contactos;
    // contador entero para llevar el seguimiento exacto de la cantidad de contactos guardados actualmente.
    private int contador;

    // primer constructor por defecto sin parametros.
    // aplica el concepto de sobrecarga de constructores al ofrecer mas de un constructor en la misma clase.
    public Agenda() {
        // uso de this con argumentos para llamar al segundo constructor asignando una capacidad por defecto de 10.
        this(10);
    }

    // segundo constructor parametrizado que permite definir una capacidad fija personalizada.
    // sobrecarga de constructores.
    public Agenda(int capacidad) {
        // inicializacion del arreglo estatico con el tamano especificado.
        this.contactos = new Contacto[capacidad];
        // inicializacion del contador en cero posiciones ocupadas.
        this.contador = 0;
    }

    // implementacion obligatoria del metodo de la interfaz para anadir contactos.
    @Override
    public boolean anadirContacto(Contacto c) {
        // validacion previa. si el contacto es nulo, la agenda esta llena o el contacto ya existe se rechaza el registro.
        if (c == null || agendaLlena() || existeContacto(c)) {
            return false;
        }
        // almacenamiento del objeto en la posicion libre apuntada por el contador.
        contactos[contador] = c;
        // incremento del contador de elementos almacenados.
        contador++;
        return true;
    }

    // implementacion del metodo para verificar la existencia de un contacto en el arreglo.
    @Override
    public boolean existeContacto(Contacto c) {
        if (c == null) return false;
        // recorrido secuencial del arreglo hasta el limite marcado por el contador.
        for (int i = 0; i < contador; i++) {
            // llamada al metodo equals que fue sobreescrito en la clase contacto para determinar igualdad de nombre y apellido.
            if (contactos[i].equals(c)) {
                return true;
            }
        }
        return false;
    }

    // implementacion del metodo para imprimir el listado completo de la agenda.
    @Override
    public void listarContactos() {
        if (contador == 0) {
            System.out.println("La agenda está vacía.");
            return;
        }
        // bucle para recorrer unicamente los elementos validos registrados en el arreglo.
        for (int i = 0; i < contador; i++) {
            // al imprimir contactos sub i se ejecuta el metodo toString de la clase real en tiempo de ejecucion.
            // este es un ejemplo de polimorfismo dinámico ya que decidira si llama a toString de personal o empresarial.
            System.out.println((i + 1) + ". " + contactos[i]);
        }
    }

    // implementacion de la busqueda por coincidencia de nombre y apellido.
    @Override
    public void buscaContacto(String nombre, String apellido) {
        boolean encontrado = false;
        for (int i = 0; i < contador; i++) {
            // utilizacion de getters para extraer datos y de equalsignorecase para evitar errores por mayusculas o minusculas.
            if (contactos[i].getNombre().equalsIgnoreCase(nombre) &&
                    contactos[i].getApellido().equalsIgnoreCase(apellido)) {
                // impresion del contacto encontrado apoyado en el polimorfismo de toString.
                System.out.println("Contacto encontrado: " + contactos[i]);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró ningún contacto con ese nombre y apellido.");
        }
    }

    // implementacion de la eliminacion de un contacto del arreglo estatico.
    @Override
    public boolean eliminarContacto(Contacto c) {
        if (c == null) return false;
        for (int i = 0; i < contador; i++) {
            if (contactos[i].equals(c)) {
                // reordenamiento manual de indices para evitar huecos intermedios en el arreglo estatico tras eliminar un elemento.
                for (int j = i; j < contador - 1; j++) {
                    contactos[j] = contactos[j + 1];
                }
                // liberacion explicita de la ultima posicion que ahora queda duplicada o invalida asignando nulo.
                contactos[contador - 1] = null;
                // decremento de la cuenta total de elementos activos.
                contador--;
                return true;
            }
        }
        return false;
    }

    // implementacion del metodo para actualizar la informacion de telefono de un registro existente.
    @Override
    public boolean modificarTelefono(String nombre, String apellido, String nuevoTelefono) {
        for (int i = 0; i < contador; i++) {
            if (contactos[i].getNombre().equalsIgnoreCase(nombre) &&
                    contactos[i].getApellido().equalsIgnoreCase(apellido)) {
                // modificacion del dato mediante el metodo setter habilitado por la encapsulacion.
                contactos[i].setTelefono(nuevoTelefono);
                return true;
            }
        }
        return false;
    }

    // metodo que evalua si la capacidad del arreglo ha alcanzado su límite maximo.
    @Override
    public boolean agendaLlena() {
        return contador >= contactos.length;
    }

    // metodo que calcula cuántos lugares quedan disponibles realizando una resta entre el largo del arreglo y el contador.
    @Override
    public int espacioLibres() {
        return contactos.length - contador;
    }
}