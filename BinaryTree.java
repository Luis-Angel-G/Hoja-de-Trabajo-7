/**
 * Clase BinaryTree que implementa un árbol binario genérico.
 * Permite almacenar elementos clave-valor y realizar operaciones como inserción,
 * búsqueda, recorrido in-order y exportación a un archivo CSV.
 * 
 * @Project : Hoja de Trabajo 7
 * @Author : Luis Girón
 * @CreationDate : 27.03.2025
 * @LastModification : 27.03.2025
 * @FileName : BinaryTree.java
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class BinaryTree<K extends Comparable<K>, V> {
    // Clase interna para los nodos del árbol
    private class Nodo {
        K clave; // Clave del nodo.
        V valor; // Valor asociado al nodo.
        Nodo izquierdo, derecho; // Hijos izquierdo y derecho del nodo.

        /**
         * Constructor de la clase Nodo.
         * 
         * @param clave La clave del nodo.
         * @param valor El valor asociado al nodo.
         */
        Nodo(K clave, V valor) {
            this.clave = clave;
            this.valor = valor;
            this.izquierdo = null;
            this.derecho = null;
        }
    }

    private Nodo raiz; // Raíz del árbol binario.

    /**
     * Constructor de la clase BinaryTree.
     * Inicializa un árbol binario vacío.
     */
    public BinaryTree() {
        this.raiz = null;
    }

    /**
     * Inserta un nuevo nodo en el árbol binario.
     * Si la clave ya existe, actualiza el valor asociado.
     * 
     * @param clave La clave del nodo a insertar.
     * @param valor El valor asociado al nodo.
     */
    public void insertar(K clave, V valor) {
        raiz = insertarRec(raiz, clave, valor);
    }

    // Método recursivo para insertar un nodo en el árbol.
    private Nodo insertarRec(Nodo raiz, K clave, V valor) {
        if (raiz == null) {
            return new Nodo(clave, valor);
        }
        int comparacion = clave.compareTo(raiz.clave);
        if (comparacion < 0) {
            raiz.izquierdo = insertarRec(raiz.izquierdo, clave, valor);
        } else if (comparacion > 0) {
            raiz.derecho = insertarRec(raiz.derecho, clave, valor);
        } else {
            raiz.valor = valor;
        }
        return raiz;
    }

    /**
     * Busca un nodo en el árbol binario por su clave.
     * 
     * @param clave La clave del nodo a buscar.
     * @return El valor asociado al nodo, o null si no se encuentra.
     */
    public V buscar(K clave) {
        Nodo nodo = buscarRec(raiz, clave);
        return nodo != null ? nodo.valor : null;
    }

    // Método recursivo para buscar un nodo en el árbol.
    private Nodo buscarRec(Nodo raiz, K clave) {
        if (raiz == null || clave.compareTo(raiz.clave) == 0) {
            return raiz;
        }
        return clave.compareTo(raiz.clave) < 0 
            ? buscarRec(raiz.izquierdo, clave) 
            : buscarRec(raiz.derecho, clave);
    }

    /**
     * Realiza un recorrido in-order del árbol binario.
     * Imprime las claves y valores de los nodos en orden ascendente.
     */
    public void inOrder() {
        inOrderRec(raiz);
    }

    // Método recursivo para realizar el recorrido in-order.
    private void inOrderRec(Nodo raiz) {
        if (raiz != null) {
            inOrderRec(raiz.izquierdo);
            System.out.println(raiz.clave + ": " + raiz.valor);
            inOrderRec(raiz.derecho);
        }
    }

    /**
     * Guarda el contenido del árbol binario en un archivo CSV.
     * 
     * @param writer El objeto FileWriter para escribir en el archivo.
     * @param header El encabezado del archivo CSV.
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    public void guardarEnCSV(FileWriter writer, String header) throws IOException {
        writer.write(header + "\n"); // Escribir el encabezado
        guardarInOrder(raiz, writer);
    }

    // Método privado para recorrer el árbol in-order y escribir en el archivo CSV.
    private void guardarInOrder(Nodo nodo, FileWriter writer) throws IOException {
        if (nodo != null) {
            guardarInOrder(nodo.izquierdo, writer);
            if (nodo.valor instanceof Producto) {
                Producto p = (Producto) nodo.valor;
                String tallasStr = mapToString(p.getTallas());
                writer.write(String.format("%s,%s,%s,%s\n", p.getSku(), p.getNombre(), p.getDescripcion(), tallasStr));
            }
            guardarInOrder(nodo.derecho, writer);
        }
    }

    /**
     * Convierte un mapa de tallas y cantidades a una cadena de texto.
     * 
     * @param tallas El mapa de tallas y cantidades.
     * @return Una cadena con el formato "talla1:cantidad1|talla2:cantidad2".
     */
    private String mapToString(Map<String, Integer> tallas) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : tallas.entrySet()) {
            if (sb.length() > 0) sb.append("|");
            sb.append(entry.getKey()).append(":").append(entry.getValue());
        }
        return sb.toString();
    }
}