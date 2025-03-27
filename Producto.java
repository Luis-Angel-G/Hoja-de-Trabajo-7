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
        String[] parts = tallas.split(" ");
        for (String part : parts) {
            String[] tallaInfo = part.split("\\|");
            if (tallaInfo.length == 2) {
                try {
                    map.put(tallaInfo[0], Integer.valueOf(tallaInfo[1]));
                } catch (NumberFormatException e) {
                    System.out.println("Error al convertir cantidad: " + part);
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