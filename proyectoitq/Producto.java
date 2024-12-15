package proyectoitq;

public class Producto {
    private String idProducto; // Atributo IDProducto (tipo String para poder gestionar números y posibles ceros a la izquierda)
    private String nombre; // Variable privada de tipo String para almacenar el nombre del producto
    private double precioUnitario; // Variable privada de tipo double para almacenar el precio unitario del producto
    private int cantidad; // Variable privada de tipo int para almacenar la cantidad del producto

    // Constructor de la clase Producto
    public Producto(String idProducto, String nombre, double precioUnitario, int cantidad) {
        this.idProducto = idProducto; // Inicializa el atributo idProducto con el valor recibido como parámetro
        this.nombre = nombre; // Inicializa el atributo nombre con el valor recibido como parámetro
        this.precioUnitario = precioUnitario; // Inicializa el atributo precioUnitario con el valor recibido como parámetro
        this.cantidad = cantidad; // Inicializa el atributo cantidad con el valor recibido como parámetro
    }

    // Métodos getter y setter para el atributo idProducto
    public String getIdProducto() { // Getter para obtener el valor del atributo idProducto
        return idProducto; // Retorna el valor almacenado en idProducto
    }

    public void setIdProducto(String idProducto) { // Setter para establecer el valor de idProducto
        this.idProducto = idProducto; // Asigna el valor recibido como parámetro al atributo idProducto
    }

    // Métodos getter y setter para el atributo nombre
    public String getNombre() { // Getter para obtener el valor del atributo nombre
        return nombre; // Retorna el valor almacenado en nombre
    }

    public void setNombre(String nombre) { // Setter para establecer el valor de nombre
        this.nombre = nombre; // Asigna el valor recibido como parámetro al atributo nombre
    }

    // Métodos getter y setter para el atributo precioUnitario
    public double getPrecioUnitario() { // Getter para obtener el valor del atributo precioUnitario
        return precioUnitario; // Retorna el valor almacenado en precioUnitario
    }

    public void setPrecioUnitario(double precioUnitario) { // Setter para establecer el valor de precioUnitario
        this.precioUnitario = precioUnitario; // Asigna el valor recibido como parámetro al atributo precioUnitario
    }

    // Métodos getter y setter para el atributo cantidad
    public int getCantidad() { // Getter para obtener el valor del atributo cantidad
        return cantidad; // Retorna el valor almacenado en cantidad
    }

    public void setCantidad(int cantidad) { // Setter para establecer el valor de cantidad
        this.cantidad = cantidad; // Asigna el valor recibido como parámetro al atributo cantidad
    }
}