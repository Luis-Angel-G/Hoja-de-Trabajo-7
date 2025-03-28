/**
 * Clase Producto que representa un producto en el inventario de ropa deportiva.
 * Contiene información sobre el SKU, nombre, descripción y tallas disponibles.
 * 
 * @Project : Hoja de Trabajo 7
 * @Author : Luis Girón
 * @CreationDate : 27.03.2025
 * @LastModification : 27.03.2025
 * @FileName : Producto.java
 */

import java.util.HashMap;
import java.util.Map;

public class Producto {
    private final String sku; // Código único del producto.
    private final String nombre; // Nombre del producto.
    private String descripcion; // Descripción del producto.
    private Map<String, Integer> tallas; // Mapa de tallas y cantidades disponibles.

    /**
     * Constructor de la clase Producto.
     * Inicializa los atributos del producto con los valores proporcionados.
     * 
     * @param sku Código único del producto.
     * @param nombre Nombre del producto.
     * @param descripcion Descripción del producto.
     * @param datosTallas Cadena con las tallas y cantidades en formato "xs:10|s:15".
     */
    public Producto(String sku, String nombre, String descripcion, String datosTallas) {
        this.sku = sku;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tallas = parsearTallas(datosTallas);
    }

    /**
     * Método auxiliar para parsear la entrada de tallas y cantidades.
     * Convierte una cadena en formato "xs:10|s:15" a un mapa de tallas y cantidades.
     * 
     * @param datosTallas Cadena con el formato de tallas y cantidades.
     * @return Un mapa con las tallas como claves y las cantidades como valores.
     */
    private Map<String, Integer> parsearTallas(String datosTallas) {
        Map<String, Integer> mapaTallas = new HashMap<>();
        if (datosTallas != null && !datosTallas.isEmpty()) {
            for (String par : datosTallas.split("\\|")) {
                String[] partes = par.split(":");
                if (partes.length == 2) {
                    try {
                        mapaTallas.put(partes[0], Integer.parseInt(partes[1]));
                    } catch (NumberFormatException e) {
                        System.out.println("Error al parsear cantidad en: " + par);
                    }
                } else {
                    System.out.println("Formato inválido en: " + par);
                }
            }
        }
        return mapaTallas;
    }

    /**
     * Obtiene el SKU del producto.
     * 
     * @return El código único del producto.
     */
    public String getSku() {
        return sku;
    }

    /**
     * Obtiene el nombre del producto.
     * 
     * @return El nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la descripción del producto.
     * 
     * @return La descripción del producto.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece una nueva descripción para el producto.
     * 
     * @param descripcion La nueva descripción del producto.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el mapa de tallas y cantidades del producto.
     * 
     * @return Un mapa con las tallas como claves y las cantidades como valores.
     */
    public Map<String, Integer> getTallas() {
        return tallas;
    }

    /**
     * Establece un nuevo mapa de tallas y cantidades para el producto.
     * 
     * @param tallas El nuevo mapa de tallas y cantidades.
     */
    public void setTallas(Map<String, Integer> tallas) {
        this.tallas = tallas;
    }

    /**
     * Actualiza la cantidad de una talla específica del producto.
     * 
     * @param talla La talla a actualizar.
     * @param cantidad La nueva cantidad para la talla especificada.
     */
    public void actualizarCantidadTalla(String talla, int cantidad) {
        this.tallas.put(talla, cantidad);
    }

    /**
     * Representa el producto como una cadena de texto.
     * Incluye el SKU, nombre, descripción y tallas disponibles.
     * 
     * @return Una cadena con la información del producto.
     */
    @Override
    public String toString() {
        return "SKU: " + sku + ", Nombre: " + nombre + ", Descripción: " + descripcion + ", Tallas: " + tallas;
    }
}