class Producto {
    String sku;
    String nombre;
    String descripcion;
    String tallas;

    public Producto(String sku, String nombre, String descripcion, String tallas) {
        this.sku = sku;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tallas = tallas;
    }

    @Override
    public String toString() {
        return "SKU: " + sku + ", Nombre: " + nombre + ", Descripción: " + descripcion + ", Tallas: " + tallas;
    }
}