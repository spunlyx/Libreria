import java.util.Scanner; // Importamos Scanner para leer datos ingresados por el usuario

public class Segundaentrega2 {

    // Declaramos una lista inicial de libros como un arreglo
    static String[] libros = {"Cien años de soledad", "El principito", "Don Quijote", "Harry Potter", "La Odisea"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Variables para almacenar datos del préstamo de libros y del usuario
        String fechaActual, fechaDevolucion, nom, libroSolicitado, ced;
        int opcion;

        System.out.println("BIENVENIDO A LA BIBLIOTECA VIRTUAL EL lugar de los libros olvidados");

        // Ciclo principal que muestra un menú al usuario
        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Ver libros disponibles");
            System.out.println("2. Sugerir un libro");
            System.out.println("3. Editar un libro");
            System.out.println("4. Eliminar un libro");
            System.out.println("5. Prestar un libro");
            System.out.println("6. Salir");
            System.out.print("Elija una opción: ");
            opcion = sc.nextInt();   // Lee la opción del usuario
            sc.nextLine();           // Limpia el buffer del scanner

            // Menú de opciones
            if (opcion == 1) {
                // Ver todos los libros disponibles
                verLibros();
            } else if (opcion == 2) {
                // Sugerir un nuevo libro (se agrega al arreglo)
                System.out.print("Escriba el nombre del libro que desea sugerir: ");
                String nuevoLibro = sc.nextLine();
                sugerirLibro(nuevoLibro);
            } else if (opcion == 3) {
                // Editar el nombre de un libro existente
                System.out.print("Escriba el número del libro que desea editar: ");
                int numEditar = sc.nextInt();
                sc.nextLine(); // limpiar buffer
                System.out.print("Escriba el nuevo nombre del libro: ");
                String nuevoNombre = sc.nextLine();
                editarLibro(numEditar, nuevoNombre);
            } else if (opcion == 4) {
                // Eliminar un libro (se pone en null)
                System.out.print("Escriba el número del libro que desea eliminar: ");
                int numEliminar = sc.nextInt();
                sc.nextLine();
                eliminarLibro(numEliminar);
            } else if (opcion == 5) {
                // Pedir préstamo de un libro
                System.out.print("Digite su nombre: ");
                nom = sc.nextLine();

                System.out.print("Digite su cédula: ");
                ced = sc.nextLine();

                System.out.print("Fecha de préstamo: ");
                fechaActual = sc.nextLine();

                System.out.print("Fecha de devolución: ");
                fechaDevolucion = sc.nextLine();

                System.out.print("Digite libro a prestar: ");
                libroSolicitado = sc.nextLine();

                prestarLibro(libroSolicitado, nom);
            } else if (opcion == 6) {
                // Salir del programa
                System.out.println("Gracias por usar la biblioteca virtual.");
            } else {
                // Si el usuario digita una opción inválida
                System.out.println("Opción no válida.");
            }

        } while (opcion != 6); // Repite el menú hasta que el usuario elija salir

        sc.close(); // Cierra el scanner
    }

    // Función para mostrar la lista de libros disponibles
    public static void verLibros() {
        System.out.println("\n--- LIBROS DISPONIBLES ---");
        for (int i = 0; i < libros.length; i++) {
            if (libros[i] != null) { // Verifica que no esté eliminado
                System.out.println((i + 1) + ". " + libros[i]); // Imprime el índice + 1 y el libro
            }
        }
    }

    // Función para sugerir (agregar) un libro
    public static void sugerirLibro(String libro) {
        // Crea un nuevo arreglo con un espacio más
        String[] nuevaLista = new String[libros.length + 1];
        for (int i = 0; i < libros.length; i++) {
            nuevaLista[i] = libros[i]; // Copia los libros actuales
        }
        nuevaLista[libros.length] = libro; // Agrega el nuevo libro al final
        libros = nuevaLista; // Reemplaza el arreglo viejo con el nuevo
        System.out.println("Libro sugerido correctamente.");
    }

    // Función para editar el nombre de un libro según su número
    public static void editarLibro(int numero, String nuevoNombre) {
        // Verifica que el número ingresado sea válido y que el libro exista
        if (numero > 0 && numero <= libros.length && libros[numero - 1] != null) {
            libros[numero - 1] = nuevoNombre; // Cambia el nombre
            System.out.println("Libro editado correctamente.");
        } else {
            System.out.println("Número de libro no válido.");
        }
    }

    // Función para eliminar un libro (ponerlo en null)
    public static void eliminarLibro(int numero) {
        if (numero > 0 && numero <= libros.length && libros[numero - 1] != null) {
            libros[numero - 1] = null; // Elimina el libro
            System.out.println("Libro eliminado correctamente.");
        } else {
            System.out.println("Número de libro no válido.");
        }
    }

    // Función para prestar un libro
    public static void prestarLibro(String libro, String nombre) {
        boolean encontrado = false;
        for (int i = 0; i < libros.length; i++) {
            // Busca si el libro existe y coincide con el solicitado
            if (libros[i] != null && libros[i].equalsIgnoreCase(libro)) {
                libros[i] = null; // Lo elimina de la lista porque fue prestado
                System.out.println("El libro '" + libro + "' ha sido prestado a " + nombre);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            // Mensaje si no se encuentra disponible
            System.out.println("El libro '" + libro + "' no está disponible en el momento.");
        }
    }
}