package Modelo;


public class DetallePedido {
    private ProductoDash producto;
    private int cantidad;

    public void AumentarCantidad(int cantidad){
    this.cantidad+=cantidad;
    }
    
    public double Importe(){
        return producto.getPrecio() * cantidad;
    }
    
    public ProductoDash getProducto() {
        return producto;
    }

    public void setProducto(ProductoDash producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}