import java.util.Scanner; // Importa la clase Scanner para leer entradas del usuario

public class Libreria {

    // Arreglo para almacenar los libros disponibles (máximo 10)
    static String[] libros = new String[10];

    // Arreglo para almacenar los libros que han sido prestados (máximo 10)
    static String[] librosPrestados = new String[10];

    // Método que llena el arreglo con algunos libros iniciales
    public static void llenarLibros() {
        libros[0] = "Don Quijote";
        libros[1] = "Cien Años de Soledad";
        libros[2] = "El Principito";
        libros[3] = "La Sombra del Viento";
    }

    // Método que muestra en pantalla los libros disponibles
    public static void mostrarLibros() {
        boolean hayLibros = false; // Bandera para verificar si hay libros
        System.out.println("Libros disponibles:");
        for (int i = 0; i < libros.length; i++) {
            if (libros[i] != null) { // Solo imprime si el espacio del arreglo no está vacío
                System.out.println("- " + libros[i]);
                hayLibros = true;
            }
        }
        if (!hayLibros) {
            System.out.println("No hay libros disponibles.");
        }
    }

    // Método que muestra en pantalla los libros que han sido prestados
    public static void mostrarLibrosPrestados() {
        boolean hayPrestados = false;
        System.out.println("Libros prestados:");
        for (int i = 0; i < librosPrestados.length; i++) {
            if (librosPrestados[i] != null) { // Solo imprime si el libro está prestado
                System.out.println("- " + librosPrestados[i]);
                hayPrestados = true;
            }
        }
        if (!hayPrestados) {
            System.out.println("No hay libros prestados.");
        }
    }

    // Método para agregar un nuevo libro a la librería
    public static void agregarLibro(Scanner sc) {
        System.out.print("Escribe el nombre del libro que quieres agregar: ");
        String nuevoLibro = sc.nextLine();

        int indiceLibre = -1; // Busca una posición vacía en el arreglo de libros
        for (int i = 0; i < libros.length; i++) {
            if (libros[i] == null) {
                indiceLibre = i;
                break;
            }
        }

        if (indiceLibre == -1) { // Si no hay espacio, muestra mensaje
            System.out.println("No hay espacio para agregar más libros.");
        } else { // Si hay espacio, agrega el libro en el arreglo
            libros[indiceLibre] = nuevoLibro;
            System.out.println("Libro agregado: " + nuevoLibro);
        }
        esperarEnter(sc); // Espera que el usuario presione Enter
    }

    // Método para prestar un libro
    public static void prestarLibro(Scanner sc) {
        mostrarLibros(); // Primero muestra los libros disponibles
        System.out.print("¿Qué libro quieres pedir prestado? (Escribe el nombre exacto o ignora mayúsculas) ");
        String libroPedido = sc.nextLine();

        int indexLibroDisponible = -1; // Busca el libro solicitado en la lista
        for (int i = 0; i < libros.length; i++) {
            if (libros[i] != null && libroPedido.equalsIgnoreCase(libros[i])) {
                indexLibroDisponible = i;
                break;
            }
        }

        if (indexLibroDisponible == -1) { // Si no lo encuentra
            System.out.println("Lo siento, ese libro no está disponible.");
            esperarEnter(sc);
            return;
        }

        int indexPrestadoLibre = -1; // Busca espacio en la lista de prestados
        for (int i = 0; i < librosPrestados.length; i++) {
            if (librosPrestados[i] == null) {
                indexPrestadoLibre = i;
                break;
            }
        }

        if (indexPrestadoLibre == -1) { // Si no hay espacio en la lista de prestados
            System.out.println("No se pueden prestar más libros, espacio lleno.");
            esperarEnter(sc);
            return;
        }

        // Mueve el libro de la lista de disponibles a la de prestados
        librosPrestados[indexPrestadoLibre] = libros[indexLibroDisponible];
        libros[indexLibroDisponible] = null;

        System.out.println("Has pedido prestado: " + librosPrestados[indexPrestadoLibre]);
        esperarEnter(sc);
    }

    // Método para devolver un libro
    public static void devolverLibro(Scanner sc) {
        mostrarLibrosPrestados(); // Muestra los libros que el usuario tiene prestados
        System.out.print("¿Qué libro quieres devolver? (Escribe el nombre exacto o ignora mayúsculas) ");
        String libroDevuelto = sc.nextLine();

        int indexLibroPrestado = -1; // Busca el libro en la lista de prestados
        for (int i = 0; i < librosPrestados.length; i++) {
            if (librosPrestados[i] != null && libroDevuelto.equalsIgnoreCase(librosPrestados[i])) {
                indexLibroPrestado = i;
                break;
            }
        }

        if (indexLibroPrestado == -1) { // Si no encuentra el libro prestado
            System.out.println("No tienes ese libro prestado.");
            esperarEnter(sc);
            return;
        }

        int indexDisponibleLibre = -1; // Busca espacio en la lista de disponibles
        for (int i = 0; i < libros.length; i++) {
            if (libros[i] == null) {
                indexDisponibleLibre = i;
                break;
            }
        }

        if (indexDisponibleLibre == -1) { // Si no hay espacio en la lista de disponibles
            System.out.println("No hay espacio para devolver el libro.");
            esperarEnter(sc);
            return;
        }

        // Mueve el libro de prestados a disponibles
        libros[indexDisponibleLibre] = librosPrestados[indexLibroPrestado];
        librosPrestados[indexLibroPrestado] = null;

        System.out.println("Has devuelto: " + libros[indexDisponibleLibre]);
        esperarEnter(sc);
    }

    // Método que hace una pausa hasta que el usuario presione Enter
    public static void esperarEnter(Scanner sc) {
        System.out.println("Presiona Enter para continuar...");
        sc.nextLine();
    }

    // Método principal
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        llenarLibros(); // Carga algunos libros iniciales

        // Ciclo principal del programa
        while (true) {
            System.out.println("\nBienvenido a la librería");
            System.out.println("1. Agregar un libro");
            System.out.println("2. Pedir un libro prestado");
            System.out.println("3. Devolver un libro");
            System.out.println("4. Ver libros prestados");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");

            String opcion = sc.nextLine();

            // Dependiendo de la opción, llama al método correspondiente
            if (opcion.equals("1")) {
                agregarLibro(sc);
            } else if (opcion.equals("2")) {
                prestarLibro(sc);
            } else if (opcion.equals("3")) {
                devolverLibro(sc);
            } else if (opcion.equals("4")) {
                mostrarLibrosPrestados();
                esperarEnter(sc);
            } else if (opcion.equals("5")) {
                System.out.println("Gracias por usar la librería. ¡Hasta luego!");
                break; // Sale del bucle y finaliza el programa
            } else {
                System.out.println("Opción no válida, intenta de nuevo.");
                esperarEnter(sc);
            }
        }

        sc.close(); // Cierra el scanner al terminar
    }
}