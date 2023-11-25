import java.util.ArrayList;
import java.util.Scanner;

class Persona {
    private final String nombre;
    private final String apellido;
    private final String dni;
    private final String fechaNacimiento;

    // Constructor
    public Persona(String nombre, String apellido, String dni, String fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Métodos de validación
    public boolean validarNombre() {
        return !nombre.isEmpty();
    }

    public boolean validarApellido() {
        return !apellido.isEmpty();
    }

    public boolean validarDNI() {
        return dni.matches("\\d{8}");  // Asegura que el DNI sea un número de 8 dígitos
    }

    public boolean validarFechaNacimiento() {
        // Aquí puedes implementar una lógica más avanzada para validar la fecha de nacimiento
        // Puedes considerar el uso de clases como LocalDate de Java 8 para simplificar esto
        // Pero por simplicidad, aquí solo se verifica que tenga el formato dd/mm/yyyy
        return fechaNacimiento.matches("\\d{2}/\\d{2}/\\d{4}");
    }

    // Otros métodos si es necesario
}

public class RegistroPersonasApp {
    private static final ArrayList<Persona> listaPersonas = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer de entrada

            switch (opcion) {
                case 1 -> agregarPersona();
                case 2 -> listarPersonas();
                case 3 -> System.out.println("Saliendo del programa. ¡Hasta luego!");
                default -> System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        } while (opcion != 3);
    }

    private static void mostrarMenu() {
        System.out.println("\n*** Registro de Personas ***");
        System.out.println("1. Agregar Persona");
        System.out.println("2. Listar Personas");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void agregarPersona() {
        System.out.println("\n*** Agregar Persona ***");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("DNI: ");
        String dni = scanner.nextLine();

        System.out.print("Fecha de Nacimiento (dd/mm/yyyy): ");
        String fechaNacimiento = scanner.nextLine();

        // Validar los datos antes de agregar la persona
        if (validarDatos(nombre, apellido, dni, fechaNacimiento)) {
            Persona nuevaPersona = new Persona(nombre, apellido, dni, fechaNacimiento);
            listaPersonas.add(nuevaPersona);
            System.out.println("Persona agregada con éxito.");
        } else {
            System.out.println("Error: Datos no válidos. La persona no fue agregada.");
        }
    }

    private static void listarPersonas() {
        System.out.println("\n*** Listado de Personas ***");

        if (listaPersonas.isEmpty()) {
            System.out.println("No hay personas registradas.");
        } else {
            for (Persona persona : listaPersonas) {
                System.out.println(persona.toString());
            }
        }
    }

    private static boolean validarDatos(String nombre, String apellido, String dni, String fechaNacimiento) {
        Persona personaTemporal = new Persona(nombre, apellido, dni, fechaNacimiento);
        return personaTemporal.validarNombre() && personaTemporal.validarApellido()
                && personaTemporal.validarDNI() && personaTemporal.validarFechaNacimiento();
    }
}
