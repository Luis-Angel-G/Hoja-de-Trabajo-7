/**
 * Clase Inventario que gestiona el almacenamiento y manipulación de productos
 * en un sistema de inventario de ropa deportiva. Utiliza árboles binarios para
 * organizar los productos por SKU y por nombre.
 * 
 * @Project : Hoja de Trabajo 7
 * @Author : Luis Girón
 * @CreationDate : 27.03.2025
 * @LastModification : 27.03.2025
 * @FileName : Inventario.java
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Inventario {
    private final BinaryTree<String, Producto> arbolSKU; // Árbol binario para organizar productos por SKU.
    private final BinaryTree<String, Producto> arbolNombre; // Árbol binario para organizar productos por nombre.
    private String archivoCSV; // Nombre del archivo CSV utilizado para guardar/cargar el inventario.
    private boolean cargandoDesdeCSV; // Bandera para evitar guardar en CSV durante la carga.

    /**
     * Constructor de la clase Inventario.
     * Inicializa los árboles binarios y configura el estado inicial.
     */
    public Inventario() {
        this.arbolSKU = new BinaryTree<>();
        this.arbolNombre = new BinaryTree<>();
        this.cargandoDesdeCSV = false;
    }

    /**
     * Agrega un producto al inventario.
     * Inserta el producto en los árboles binarios y guarda los cambios en el archivo CSV.
     * 
     * @param producto El producto a agregar.
     */
    public void agregarProducto(Producto producto) {
        arbolSKU.insertar(producto.getSku(), producto);
        arbolNombre.insertar(producto.getNombre(), producto);
        if (!cargandoDesdeCSV) {
            guardarEnCSV();
        }
    }

    /**
     * Busca un producto en el inventario por su SKU.
     * 
     * @param sku El SKU del producto a buscar.
     * @return El producto encontrado o null si no existe.
     */
    public Producto buscarPorSKU(String sku) {
        return arbolSKU.buscar(sku);
    }

    /**
     * Busca un producto en el inventario por su nombre.
     * 
     * @param nombre El nombre del producto a buscar.
     * @return El producto encontrado o null si no existe.
     */
    public Producto buscarPorNombre(String nombre) {
        return arbolNombre.buscar(nombre);
    }

    /**
     * Lista todos los productos del inventario ordenados por SKU.
     * Imprime los productos en orden ascendente según su SKU.
     */
    public void listarPorSKU() {
        System.out.println("Productos ordenados por SKU:");
        arbolSKU.inOrder();
    }

    /**
     * Lista todos los productos del inventario ordenados por nombre.
     * Imprime los productos en orden ascendente según su nombre.
     */
    public void listarPorNombre() {
        System.out.println("Productos ordenados por Nombre:");
        arbolNombre.inOrder();
    }

    /**
     * Carga productos desde un archivo CSV al inventario.
     * Cada línea del archivo representa un producto con formato "SKU,Nombre,Descripción,Cantidad por talla".
     * 
     * @param nombreArchivo El nombre del archivo CSV a cargar.
     */
    public void cargarDesdeCSV(String nombreArchivo) {
        this.archivoCSV = nombreArchivo;
        this.cargandoDesdeCSV = true;
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            boolean primeraLinea = true;
            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }
                String[] datos = linea.split(",");
                if (datos.length == 4) {
                    Producto producto = new Producto(datos[0], datos[1], datos[2], datos[3]);
                    agregarProducto(producto);
                } else {
                    System.out.println("Formato inválido en: " + linea);
                }
            }
            System.out.println("Inventario cargado exitosamente desde " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } finally {
            this.cargandoDesdeCSV = false;
        }
    }

    /**
     * Edita un producto existente en el inventario.
     * Actualiza la descripción y las tallas del producto, y guarda los cambios en el archivo CSV.
     * 
     * @param sku El SKU del producto a editar.
     * @param nuevaDescripcion La nueva descripción del producto.
     * @param nuevasTallas El nuevo mapa de tallas y cantidades.
     */
    public void editarProducto(String sku, String nuevaDescripcion, Map<String, Integer> nuevasTallas) {
        Producto producto = buscarPorSKU(sku);
        if (producto != null) {
            producto.setDescripcion(nuevaDescripcion);
            producto.setTallas(nuevasTallas);
            arbolSKU.insertar(sku, producto);
            arbolNombre.insertar(producto.getNombre(), producto);
            guardarEnCSV();
        } else {
            System.out.println("Producto con SKU " + sku + " no encontrado.");
        }
    }

    /**
     * Guarda el inventario actual en un archivo CSV.
     * Escribe los productos en el archivo con formato "SKU,Nombre,Descripción,Cantidad por talla".
     */
    private void guardarEnCSV() {
        try (FileWriter writer = new FileWriter(archivoCSV)) {
            arbolSKU.guardarEnCSV(writer, "SKU,Nombre,Descripción,Cantidad por talla");
            System.out.println("Inventario guardado en " + archivoCSV);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
}