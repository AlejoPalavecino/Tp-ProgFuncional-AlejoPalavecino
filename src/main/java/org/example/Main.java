package org.example;

import org.example.Entidades.Alumno;
import org.example.Entidades.Empleado;
import org.example.Entidades.Libro;
import org.example.Entidades.Producto;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Alumno> alumnos = List.of(
                Alumno.builder().nombre("Juan").nota(85).curso("Matematicas").build(),
                Alumno.builder().nombre("Maria").nota(92).curso("Historia").build(),
                Alumno.builder().nombre("Pedro").nota(76).curso("Ciencias").build(),
                Alumno.builder().nombre("Roman").nota(40).curso("Ciencias").build()
        );

        //Caso Practico 1:

        // Función para obtener los nombres en mayúsculas de los alumnos con nota > 70, ordenados alfabéticamente
        List<String> nombresMayusculas = alumnos.stream()
                .filter(alumno -> alumno.getNota() > 70) // Filtrar alumnos con nota > 70
                .map(Alumno::getNombre) // Obtener los nombres
                .map(String::toUpperCase) // Convertir a mayúsculas
                .sorted() //Ordenar alfabéticamente
                .collect(Collectors.toList()); // Recoger en una lista
        System.out.println("Nombres en mayúsculas de alumnos con nota > 70, ordenados alfabéticamente:");
        nombresMayusculas.forEach(System.out::println); // Imprimir los nombres

        //Calcular el promedio de notas de los alumnos
        double promdioNotas = alumnos.stream()
                .collect(Collectors.averagingDouble(Alumno::getNota));
        System.out.println("Promedio de notas de los alumnos: " + promdioNotas);

        //Agrupar los alumnos por curso usando Collectors.groupingBy
        Map<String, List<Alumno>> alumnosPorCurso = alumnos.stream()
                .collect(Collectors.groupingBy(Alumno::getCurso));
        System.out.println(alumnosPorCurso);

        //Obtener los 3 alumnos con las notas más altas
        List<Alumno> mejoresNotas = alumnos.stream()
                .sorted((a1, a2) -> Integer.compare(a2.getNota(), a1.getNota())) // Ordenar por nota descendente
                .limit(3) // Limitar a los 3 primeros
                .collect(Collectors.toList()); // Recoger en una lista
        System.out.println("Los 3 alumnos con las notas más altas:");
        mejoresNotas.forEach(alumno -> System.out.println(alumno.getNombre() + ": " + alumno.getNota()));

        //Caso Practico 2:

        List<Producto> productos = List.of(
                Producto.builder().nombre("Laptop").precio(1200.00).categoria("Electrónica").stock(10).build(),
                Producto.builder().nombre("Smartphone").precio(800.00).categoria("Electrónica").stock(25).build(),
                Producto.builder().nombre("Tablet").precio(300.00).categoria("Electrónica").stock(15).build(),
                Producto.builder().nombre("Auriculares").precio(150.00).categoria("Accesorios").stock(50).build(),
                Producto.builder().nombre("Reloj Inteligente").precio(200.00).categoria("Accesorios").stock(30).build(),
                Producto.builder().nombre("Cargador").precio(20.00).categoria("Accesorios").stock(100).build()
        );

        //Listar los productos con precio mayor a 100, ordenados por precio descendente.
        List<Producto> prodOrdenados = productos.stream()
                .filter(producto -> producto.getPrecio() > 100) // Filtrar productos con precio > 100
                .sorted((p1, p2) -> Double.compare(p2.getPrecio(), p1.getPrecio())) // Ordenar por precio descendente
                .collect(Collectors.toList()); // Recoger en una lista
        System.out.println("Productos con precio mayor a 100, ordenados por precio descendente:");
        prodOrdenados.forEach(producto -> System.out.println(producto.getNombre() + ": " + producto.getPrecio()));

        //Agrupar los productos por categoría y calcular el stock total por categoría.
        Map<String, Integer> stockPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(Producto::getCategoria,
                        Collectors.summingInt(Producto::getStock))); // Sumar el stock por categoría
        System.out.println("Stock total por categoría:");
        stockPorCategoria.forEach((categoria, stock) ->
                System.out.println(categoria + ": " + stock));

        //Generar un String separando con “;” cada producto que contenga nombre y precio, usando map + collect(joining).
        String productosString = productos.stream()
                .map(producto -> producto.getNombre() + " - $" + producto.getPrecio()) // Mapear a nombre y precio
                .collect(Collectors.joining("; ")); // Unir con "; "
        System.out.println("Productos (nombre y precio) separados por ';':");
        System.out.println(productosString);

        //Calcular el precio promedio general y por categoría.
        double precioPromedioGeneral = productos.stream()
                .collect(Collectors.averagingDouble(Producto::getPrecio)); // Promedio general
        System.out.println("Precio promedio general: " + precioPromedioGeneral);
        Map<String, Double> precioPromedioPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(Producto::getCategoria,
                        Collectors.averagingDouble(Producto::getPrecio))); // Promedio por categoría
        System.out.println("Precio promedio por categoría:");
        precioPromedioPorCategoria.forEach((categoria, promedio) ->
                System.out.println(categoria + ": " + promedio));

        //Caso Practico 3:
         List <Libro> libros = List.of(
                 Libro.builder().titulo("El Quijote").autor("Miguel de Cervantes").paginas(863).precio(29.99).build(),
                 Libro.builder().titulo("Cien Años de Soledad").autor("Gabriel García Márquez").paginas(417).precio(24.99).build(),
                 Libro.builder().titulo("1984").autor("George Orwell").paginas(328).precio(19.99).build(),
                 Libro.builder().titulo("La Sombra del Viento").autor("Carlos Ruiz Zafón").paginas(576).precio(22.50).build()
         );

        //Listar los libros con más de 300 páginas ordenados alfabeticamente.
        List<Libro> librosOrdenados = libros.stream()
                .filter(libro -> libro.getPaginas() > 300) // Filtrar libros con más de 300 páginas
                .sorted((l1, l2) -> l1.getTitulo().compareTo(l2.getTitulo())) // Ordenar alfabéticamente por título
                .collect(Collectors.toList()); // Recoger en una lista
        System.out.println("Libros con más de 300 páginas ordenados alfabéticamente:");
        librosOrdenados.forEach(libro -> System.out.println(libro.getTitulo() + ": " + libro.getPaginas() + " páginas"));

        //Calcular el promedio de páginas de los libros.
        double promedioPaginas = libros.stream()
                .collect(Collectors.averagingInt(Libro::getPaginas)); // Promedio de páginas
        System.out.println("Promedio de páginas de los libros: " + promedioPaginas);

        //Agrupar los libros por autor y contar cuántos libros tiene cada autor.
        Map<String, Long> librosPorAutor = libros.stream()
                .collect(Collectors.groupingBy(Libro::getAutor,
                        Collectors.counting())); // Contar libros por autor
        System.out.println("Cantidad de libros por autor:");
        librosPorAutor.forEach((autor, cantidad) ->
                System.out.println(autor + ": " + cantidad));

        //Obtener el libro más caro.
        Libro libroMasCaro = libros.stream()
                .max((l1, l2) -> Double.compare(l1.getPrecio(), l2.getPrecio())) // Obtener el libro con el precio máximo
                .orElse(null); // Manejar el caso si no hay libros
        if (libroMasCaro != null) {
            System.out.println("El libro más caro es: " + libroMasCaro.getTitulo() + " con un precio de $" + libroMasCaro.getPrecio());
        } else {
            System.out.println("No hay libros disponibles.");
        }

        //Caso Practico 4:
        List<Empleado> empleados = List.of(
                Empleado.builder().nombre("Ana").departamento("Ventas").salario(55000).edad(30).build(),
                Empleado.builder().nombre("Luis").departamento("Marketing").salario(60000).edad(35).build(),
                Empleado.builder().nombre("Carlos").departamento("Ventas").salario(52000).edad(28).build(),
                Empleado.builder().nombre("Marta").departamento("Recursos Humanos").salario(58000).edad(40).build()
        );

        //Obtener la lista de empleados cuyo salario sea mayor a 2000, ordenados por salario descendente.
        List<Empleado> filtradosOrdenados = empleados.stream()
                .filter(e -> e.getSalario() > 2000)
                .sorted(Comparator.comparingDouble(Empleado::getSalario).reversed())
                .collect(Collectors.toList());

        //Calcular el salario promedio general.
        double salarioPromedio = empleados.stream()
                .collect(Collectors.averagingDouble(Empleado::getSalario));

        //Agrupar los empleados por departamento y calcular la suma de salarios de cada uno.
        Map<String, Double> sumaPorDepto = empleados.stream()
                .collect(Collectors.groupingBy(Empleado::getDepartamento,
                        Collectors.summingDouble(Empleado::getSalario)));

        //Obtener los nombres de los 2 empleados más jóvenes.
        List<String> dosMasJovenes =
                empleados.stream()
                        .sorted(Comparator.comparingInt(Empleado::getEdad))
                        .limit(2).map(Empleado::getNombre)
                        .collect(Collectors.toList());
    }
}

