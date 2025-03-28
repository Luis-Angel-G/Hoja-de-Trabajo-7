/**
 * Clase BinaryTreeTest que contiene pruebas unitarias para la clase BinaryTree.
 * Verifica la funcionalidad de inserción, búsqueda, actualización y recorrido in-order
 * en un árbol binario que almacena productos.
 * 
 * @Project : Hoja de Trabajo 7
 * @Author : Luis Girón
 * @CreationDate : 27.03.2025
 * @LastModification : 27.03.2025
 * @FileName : BinaryTreeTest.java
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BinaryTreeTest {
    private BinaryTree<String, Producto> arbol; // Árbol binario de prueba.

    /**
     * Configuración inicial para cada prueba.
     * Inicializa un árbol binario vacío antes de cada prueba.
     */
    @BeforeEach
    public void setUp() {
        arbol = new BinaryTree<>();
    }

    /**
     * Prueba para verificar la inserción de un elemento en el árbol.
     * Asegura que el elemento insertado se pueda buscar correctamente.
     */
    @Test
    public void testInsertarUnElemento() {
        Producto producto = new Producto("001", "Short de Pádel", "Ajuste cómodo", "xs:5");
        arbol.insertar(producto.getSku(), producto);

        Producto resultado = arbol.buscar("001");
        assertNotNull(resultado, "El producto debería estar en el árbol después de insertar");
        assertEquals("001", resultado.getSku(), "El SKU del producto insertado debería coincidir");
        assertEquals("Short de Pádel", resultado.getNombre(), "El nombre del producto insertado debería coincidir");
    }

    /**
     * Prueba para verificar la búsqueda de un elemento existente en el árbol.
     * Asegura que el elemento buscado se encuentre correctamente.
     */
    @Test
    public void testBuscarElementoExistente() {
        Producto producto1 = new Producto("001", "Short de Pádel", "Ajuste cómodo", "xs:5");
        Producto producto2 = new Producto("002", "Conjunto de Gimnasia", "Transpirable", "m:10");
        arbol.insertar(producto1.getSku(), producto1);
        arbol.insertar(producto2.getSku(), producto2);

        Producto resultado = arbol.buscar("002");
        assertNotNull(resultado, "El producto con SKU 002 debería encontrarse");
        assertEquals("Conjunto de Gimnasia", resultado.getNombre(), "El nombre del producto encontrado debería coincidir");
    }

    /**
     * Prueba para verificar la búsqueda de un elemento no existente en el árbol.
     * Asegura que la búsqueda de un SKU inexistente devuelva null.
     */
    @Test
    public void testBuscarElementoNoExistente() {
        Producto producto = new Producto("001", "Short de Pádel", "Ajuste cómodo", "xs:5");
        arbol.insertar(producto.getSku(), producto);

        Producto resultado = arbol.buscar("999");
        assertNull(resultado, "La búsqueda de un SKU no existente debería devolver null");
    }

    /**
     * Prueba para verificar la actualización de un elemento existente al insertar.
     * Asegura que los datos del elemento se actualicen correctamente.
     */
    @Test
    public void testInsertarActualizaElementoExistente() {
        Producto productoOriginal = new Producto("001", "Short de Pádel", "Ajuste cómodo", "xs:5");
        Producto productoActualizado = new Producto("001", "Short de Pádel", "Ajuste mejorado", "xs:10");
        arbol.insertar(productoOriginal.getSku(), productoOriginal);
        arbol.insertar(productoActualizado.getSku(), productoActualizado);

        Producto resultado = arbol.buscar("001");
        assertNotNull(resultado, "El producto debería estar presente después de actualizar");
        assertEquals("Ajuste mejorado", resultado.getDescripcion(), "La descripción debería haberse actualizado");
        assertEquals("{xs=10}", resultado.getTallas().toString(), "Las tallas deberían haberse actualizado");
    }

    /**
     * Prueba para verificar el recorrido in-order con múltiples elementos.
     * Asegura que los elementos se inserten y puedan ser buscados correctamente.
     */
    @Test
    public void testInOrderConVariosElementos() {
        Producto p1 = new Producto("003", "Guantes de Gimnasio", "Alta intensidad", "m:18");
        Producto p2 = new Producto("001", "Short de Pádel", "Ajuste cómodo", "xs:5");
        Producto p3 = new Producto("002", "Conjunto de Gimnasia", "Transpirable", "m:10");
        arbol.insertar(p1.getSku(), p1);
        arbol.insertar(p2.getSku(), p2);
        arbol.insertar(p3.getSku(), p3);

        assertEquals(p2, arbol.buscar("001"), "El producto 001 debería estar en el árbol");
        assertEquals(p3, arbol.buscar("002"), "El producto 002 debería estar en el árbol");
        assertEquals(p1, arbol.buscar("003"), "El producto 003 debería estar en el árbol");
    }
}