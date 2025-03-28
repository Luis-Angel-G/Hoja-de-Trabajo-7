/**
 * Clase principal para ejecutar el sistema de inventario de ropa deportiva.
 * Gestiona la interacción con el usuario y las funcionalidades del inventario.
 * 
 * @Project : Hoja de Trabajo 7
 * @Author : Luis Girón
 * @CreationDate : 27.03.2025
 * @LastModification : 27.03.2025
 * @FileName : Main.java
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    /**
     * Método principal que ejecuta el sistema de inventario.
     * Presenta un menú interactivo para gestionar productos en el inventario.
     * 
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== Sistema de Inventario de Ropa Deportiva ===");
            System.out.println("1. Cargar inventario desde archivo CSV");
            System.out.println("2. Agregar nuevo producto");
            System.out.println("3. Editar producto existente");
            System.out.println("4. Listar productos por SKU");
            System.out.println("5. Listar productos por nombre");
            System.out.println("6. Buscar producto por SKU");
            System.out.println("7. Buscar producto por nombre");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del archivo CSV (e.g., inventario.csv): ");
                    String nombreArchivo = scanner.nextLine();
                    inventario.cargarDesdeCSV(nombreArchivo);
                    break;

                case 2:
                    System.out.print("Ingrese SKU: ");
                    String sku = scanner.nextLine();
                    System.out.print("Ingrese nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese descripción: ");
                    String descripcion = scanner.nextLine();
                    System.out.print("Ingrese tallas y cantidades (formato xs:10|s:15): ");
                    String tallasInput = scanner.nextLine();

                    Producto nuevoProducto = new Producto(sku, nombre, descripcion, tallasInput);
                    inventario.agregarProducto(nuevoProducto);
                    System.out.println("Producto agregado exitosamente.");
                    break;

                case 3:
                    System.out.print("Ingrese el SKU del producto a editar: ");
                    String skuEditar = scanner.nextLine();
                    Producto producto = inventario.buscarPorSKU(skuEditar);
                    if (producto != null) {
                        System.out.print("Nueva descripción (deje en blanco para mantener actual): ");
                        String nuevaDesc = scanner.nextLine();
                        if (!nuevaDesc.isEmpty()) {
                            producto.setDescripcion(nuevaDesc);
                        }

                        System.out.print("Nuevas tallas y cantidades (formato xs:5|s:10, deje en blanco para mantener actual): ");
                        String nuevasTallasInput = scanner.nextLine();
                        if (!nuevasTallasInput.isEmpty()) {
                            producto.setTallas(parsearTallasInput(nuevasTallasInput));
                            inventario.editarProducto(skuEditar, producto.getDescripcion(), producto.getTallas());
                        } else {
                            inventario.editarProducto(skuEditar, producto.getDescripcion(), producto.getTallas());
                        }
                        System.out.println("Producto editado exitosamente.");
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;

                case 4:
                    inventario.listarPorSKU();
                    break;

                case 5:
                    inventario.listarPorNombre();
                    break;

                case 6:
                    System.out.print("Ingrese el SKU a buscar: ");
                    String skuBuscar = scanner.nextLine();
                    Producto encontradoSKU = inventario.buscarPorSKU(skuBuscar);
                    if (encontradoSKU != null) {
                        System.out.println("Producto encontrado: " + encontradoSKU);
                    } else {
                        System.out.println("Producto con SKU " + skuBuscar + " no encontrado.");
                    }
                    break;

                case 7:
                    System.out.print("Ingrese el nombre a buscar: ");
                    String nombreBuscar = scanner.nextLine();
                    Producto encontradoNombre = inventario.buscarPorNombre(nombreBuscar);
                    if (encontradoNombre != null) {
                        System.out.println("Producto encontrado: " + encontradoNombre);
                    } else {
                        System.out.println("Producto con nombre '" + nombreBuscar + "' no encontrado.");
                    }
                    break;

                case 8:
                    continuar = false;
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        scanner.close();
    }

    /**
     * Método auxiliar para parsear la entrada de tallas y cantidades.
     * Convierte una cadena en formato "xs:10|s:15" a un mapa de tallas y cantidades.
     * 
     * @param tallasInput Cadena con el formato de tallas y cantidades.
     * @return Un mapa con las tallas como claves y las cantidades como valores.
     */
    private static Map<String, Integer> parsearTallasInput(String tallasInput) {
        Map<String, Integer> tallas = new HashMap<>();
        if (tallasInput != null && !tallasInput.isEmpty()) {
            String[] pares = tallasInput.split("\\|");
            for (String par : pares) {
                String[] partes = par.split(":");
                if (partes.length == 2) {
                    try {
                        tallas.put(partes[0], Integer.parseInt(partes[1]));
                    } catch (NumberFormatException e) {
                        System.out.println("Error al parsear cantidad en: " + par);
                    }
                }
            }
        }
        return tallas;
    }
}