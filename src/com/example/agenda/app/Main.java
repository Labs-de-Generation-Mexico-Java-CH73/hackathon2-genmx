// declaracion del paquete de la aplicacion principal.
package com.example.agenda.app;

// importaciones de los modelos, servicios y utilidades del sistema.
import com.example.agenda.model.Contacto;
import com.example.agenda.model.ContactoEmpresarial;
import com.example.agenda.model.ContactoPersonal;
import com.example.agenda.service.Agenda;
import com.example.agenda.util.Validador;

// importacion de la clase scanner para lectura de entradas por consola.
import java.util.Scanner;

// clase principal que actua como punto de entrada de la ejecucion del programa.
public class Main {

    // metodo estatico principal main exigido por la maquina virtual de java para arrancar el sistema.
    public static void main(String[] args) {
        // creacion de un objeto scanner para procesar el flujo de entrada de teclado.
        Scanner scanner = new Scanner(System.in);
        // instanciacion de la agenda asignando una capacidad maxima de 10 elementos mediante el constructor parametrizado.
        Agenda agenda = new Agenda(10);
        // variable de control de tipo bandera para controlar la finalizacion del bucle interactivo.
        boolean salir = false;

        System.out.println("=== SISTEMA DE GESTIÓN DE AGENDA ===");

        // bucle while que mantiene la ejecucion interactiva del menu de consola hasta que salir pase a ser verdadero.
        while (!salir) {
            System.out.println("\n\n--- MENÚ ---");
            System.out.println("1. Añadir contacto personal");
            System.out.println("2. Añadir contacto empresarial");
            System.out.println("3. Listar contactos");
            System.out.println("4. Buscar contacto");
            System.out.println("5. Modificar teléfono");
            System.out.println("6. Eliminar contacto");
            System.out.println("7. Ver espacios libres");
            System.out.println("8. Salir");
            System.out.print("Selecciona una opción: ");

            // lectura del texto ingresado por la consola.
            String opcionStr = scanner.nextLine();
            // uso del metodo estatico esvalido de la clase validador para verificar que la entrada no este vacia.
            if (!Validador.esValido(opcionStr)) {
                System.out.println("\nEntrada inválida.");
                continue;
            }

            int opcion;
            // bloque de manejo de excepciones try catch para capturar posibles errores al convertir texto a entero.
            try {
                opcion = Integer.parseInt(opcionStr.trim());
            } catch (NumberFormatException e) {
                // se captura la excepcion si el usuario escribe texto que no es numerico para evitar la caida de la aplicacion.
                System.out.println("\nPor favor ingresa un número válido.");
                continue;
            }

            // estructura de control condicional switch para dirigir la navegacion del menu.
            switch (opcion) {
                case 1:
                    // solicitud de datos mediante la invocacion del metodo auxiliar privado pedirEntrada.
                    String nomP = pedirEntrada(scanner, "Nombre: ");
                    String apeP = pedirEntrada(scanner, "Apellido: ");
                    String telP = pedirEntrada(scanner, "Teléfono: ");
                    String cumP = pedirEntrada(scanner, "Cumpleaños (DD/MM/AAAA): ");

                    // principio de sustitucion de liskov y polimorfismo.
                    // una variable de tipo de la clase base contacto almacena una instancia de la subclase contactopersonal.
                    Contacto p = new ContactoPersonal(nomP, apeP, telP, cumP);
                    // insercion en la agenda evaluando la respuesta booleana.
                    if (agenda.anadirContacto(p)) {
                        System.out.println("\nContacto personal añadido exitosamente.");
                    } else {
                        System.out.println("\nNo se pudo añadir (agenda llena o contacto existente).");
                    }
                    break;

                case 2:
                    String nomE = pedirEntrada(scanner, "Nombre: ");
                    String apeE = pedirEntrada(scanner, "Apellido: ");
                    String telE = pedirEntrada(scanner, "Teléfono: ");
                    String empE = pedirEntrada(scanner, "Empresa: ");
                    String carE = pedirEntrada(scanner, "Cargo: ");

                    // aplicacion de polimorfismo asignando un contactoempresarial a una referencia de tipo contacto.
                    Contacto e = new ContactoEmpresarial(nomE, apeE, telE, empE, carE);
                    if (agenda.anadirContacto(e)) {
                        System.out.println("\nContacto empresarial añadido exitosamente.");
                    } else {
                        System.out.println("\nNo se pudo añadir (agenda llena o contacto existente).");
                    }
                    break;

                case 3:
                    System.out.println("\n--- LISTA DE CONTACTOS ---");
                    // invocacion del metodo que muestra los contactos almacenados.
                    agenda.listarContactos();
                    break;

                case 4:
                    String bNom = pedirEntrada(scanner, "Nombre a buscar: ");
                    String bApe = pedirEntrada(scanner, "Apellido a buscar: ");
                    agenda.buscaContacto(bNom, bApe);
                    break;

                case 5:
                    String mNom = pedirEntrada(scanner, "Nombre: ");
                    String mApe = pedirEntrada(scanner, "Apellido: ");
                    String mTel = pedirEntrada(scanner, "Nuevo Teléfono: ");
                    if (agenda.modificarTelefono(mNom, mApe, mTel)) {
                        System.out.println("\nTeléfono actualizado correctamente.");
                    } else {
                        System.out.println("\nNo se encontró el contacto.");
                    }
                    break;

                case 6:
                    String elNom = pedirEntrada(scanner, "Nombre: ");
                    String elApe = pedirEntrada(scanner, "Apellido: ");
                    // creacion de un objeto temporal auxiliar para aprovechar la comparacion por equals en la eliminacion.
                    Contacto aux = new ContactoPersonal(elNom, elApe, "", "");
                    if (agenda.eliminarContacto(aux)) {
                        System.out.println("\nContacto eliminado correctamente.");
                    } else {
                        System.out.println("\nNo se encontró el contacto a eliminar.");
                    }
                    break;

                case 7:
                    // consulta e impresion del espacio restante disponible en la agenda.
                    System.out.println("Espacios libres disponibles: " + agenda.espacioLibres());
                    break;

                case 8:
                    // cambio del estado de la bandera para salir del bucle interactivo.
                    salir = true;
                    System.out.println("\nSaliendo de la agenda...");
                    break;

                default:
                    System.out.println("\nOpción fuera de rango.");
            }
        }
        // cierre explicito del objeto scanner para liberar los recursos de sistema utilizados.
        scanner.close();
    }

    // metodo auxiliar privado y estatico para abstraer y reutilizar la logica de captura y validacion de entradas de texto.
    private static String pedirEntrada(Scanner scanner, String mensaje) {
        String entrada;
        // bucle do while que garantiza la repeticion del prompt hasta capturar un valor valido.
        do {
            System.out.print(mensaje);
            entrada = scanner.nextLine();
            if (!Validador.esValido(entrada)) {
                System.out.println("\nEl campo no puede estar vacío. Intenta de nuevo.");
            }
        } while (!Validador.esValido(entrada));
        // retorno de la cadena sin espacios blancos residuales en los extremos.
        return entrada.trim();
    }
}