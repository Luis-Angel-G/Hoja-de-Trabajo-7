import java.util.HashMap;

class Producto {
    String sku;
    String nombre;
    String descripcion;
    HashMap<String, Integer> tallasYCantidades;

    public Producto(String sku, String nombre, String descripcion, String tallas) {
        this.sku = sku;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tallasYCantidades = parseTallas(tallas);
    }

    private HashMap<String, Integer> parseTallas(String tallas) {
        HashMap<String, Integer> map = new HashMap<>();
        String[] parts = tallas.split("\\|");
        for (String part : parts) {
            int colonIndex = part.indexOf(":");
            if (colonIndex != -1) { 
                String talla = part.substring(0, colonIndex).trim();
                String cantidadStr = part.substring(colonIndex + 1).trim();
                try {
                    int cantidad = Integer.parseInt(cantidadStr);
                    map.put(talla, cantidad);
                } catch (NumberFormatException e) {
                    System.out.println("Error: No se pudo convertir a número: " + cantidadStr);
                }
            }
        }
        return map;
    }    

    @Override
    public String toString() {
        return "SKU: " + sku + ", Nombre: " + nombre + ", Descripción: " + descripcion + ", Tallas: " + tallasYCantidades;
    }
}