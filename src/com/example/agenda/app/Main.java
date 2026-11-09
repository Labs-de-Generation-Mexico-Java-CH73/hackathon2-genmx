package com.example.agenda.app;

import com.example.agenda.model.Contacto;
import com.example.agenda.model.ContactoEmpresarial;
import com.example.agenda.model.ContactoPersonal;
import com.example.agenda.service.Agenda;
import com.example.agenda.util.Validador;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda(10);
        boolean salir = false;

        System.out.println("=== SISTEMA DE GESTIÓN DE AGENDA ===");

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

            String opcionStr = scanner.nextLine();
            if (!Validador.esValido(opcionStr)) {
                System.out.println("\nEntrada inválida.");
                continue;
            }

            int opcion;
            try {
                opcion = Integer.parseInt(opcionStr.trim());
            } catch (NumberFormatException e) {
                System.out.println("\nPor favor ingresa un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    String nomP = pedirEntrada(scanner, "Nombre: ");
                    String apeP = pedirEntrada(scanner, "Apellido: ");
                    String telP = pedirEntrada(scanner, "Teléfono: ");
                    String cumP = pedirEntrada(scanner, "Cumpleaños (DD/MM/AAAA): ");

                    Contacto p = new ContactoPersonal(nomP, apeP, telP, cumP);
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

                    Contacto e = new ContactoEmpresarial(nomE, apeE, telE, empE, carE);
                    if (agenda.anadirContacto(e)) {
                        System.out.println("\nContacto empresarial añadido exitosamente.");
                    } else {
                        System.out.println("\nNo se pudo añadir (agenda llena o contacto existente).");
                    }
                    break;

                case 3:
                    System.out.println("\n--- LISTA DE CONTACTOS ---");
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
                    Contacto aux = new ContactoPersonal(elNom, elApe, "", "");
                    if (agenda.eliminarContacto(aux)) {
                        System.out.println("\nContacto eliminado correctamente.");
                    } else {
                        System.out.println("\nNo se encontró el contacto a eliminar.");
                    }
                    break;

                case 7:
                    System.out.println("Espacios libres disponibles: " + agenda.espacioLibres());
                    break;

                case 8:
                    salir = true;
                    System.out.println("\nSaliendo de la agenda...");
                    break;

                default:
                    System.out.println("\nOpción fuera de rango.");
            }
        }
        scanner.close();
    }

    private static String pedirEntrada(Scanner scanner, String mensaje) {
        String entrada;
        do {
            System.out.print(mensaje);
            entrada = scanner.nextLine();
            if (!Validador.esValido(entrada)) {
                System.out.println("\nEl campo no puede estar vacío. Intenta de nuevo.");
            }
        } while (!Validador.esValido(entrada));
        return entrada.trim();
    }
}