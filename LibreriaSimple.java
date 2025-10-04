import java.util.Scanner;

public class LibreriaSimple {


    static String[] libros = new String[10];
    static boolean[] prestados = new boolean[10];
    static int cantidadLibros = 0;

    public static void agregarLibro(Scanner sc) {
        if (cantidadLibros >= 10) {
            System.out.println("La librería está llena. No puedes agregar más libros.");
            return;
        }

        System.out.print("Escribe el nombre del libro que quieres agregar: ");
        String nombre = sc.nextLine();
        libros[cantidadLibros] = nombre;
        prestados[cantidadLibros] = false; // no está prestado
        cantidadLibros++;

        System.out.println("Libro agregado con éxito.");
    }

    public static void verLibros() {
        System.out.println("Lista de libros:");
        if (cantidadLibros == 0) {
            System.out.println("No hay libros agregados.");
            return;
        }

        for (int i = 0; i < cantidadLibros; i++) {
            System.out.println((i + 1) + ". " + libros[i] + " ,prestado: "+prestados[i]);
        }
    }

    public static void prestarLibro(Scanner sc) {
        verLibros();
        if (cantidadLibros == 0) return;

        System.out.print("Escribe el número del libro que quieres prestar: ");
        int numero = Integer.parseInt(sc.nextLine());

        if (numero < 1 || numero > cantidadLibros) {
            System.out.println("Número inválido.");
            return;
        }

        int indice = numero - 1;
        if (prestados[indice]) {
            System.out.println("Ese libro ya está prestado.");
        } else {
            prestados[indice] = true;
            System.out.println("Has prestado el libro: " + libros[indice]);
        }
    }

    public static void verLibrosPrestados() {
        boolean hayPrestados = false;
        System.out.println("Libros prestados:");
        for (int i = 0; i < cantidadLibros; i++) {
            if (prestados[i]) {
                System.out.println("- " + libros[i]);
                hayPrestados = true;
            }
        }

        if (!hayPrestados) {
            System.out.println("No hay libros prestados.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Agregar libro");
            System.out.println("2. Ver libros");
            System.out.println("3. Prestar libro");
            System.out.println("4. Ver libros prestados");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            String opcion = sc.nextLine();

            if (opcion.equals("1")) {
                agregarLibro(sc);
            } else if (opcion.equals("2")) {
                verLibros();
            } else if (opcion.equals("3")) {
                prestarLibro(sc);
            } else if (opcion.equals("4")) {
                verLibrosPrestados();
            } else if (opcion.equals("5")) {
                System.out.println("Gracias por usar la librería. ¡Hasta luego!");
                break;
            } else {
                System.out.println("Opción inválida. Intenta de nuevo.");
            }
        }

        sc.close();
    }
}