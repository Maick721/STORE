/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoitq;

// Se importa la clase Pattern del paquete java.util.regex para usar expresiones regulares
// en la validación de formatos, como el correo electrónico.
import java.util.regex.Pattern;

public class Usuario {
    // Atributos privados para encapsular los datos de la clase
    private String idUsuario; // Almacena el ID del usuario; debe contener solo números.
    private String nombre;    // Almacena el nombre del usuario.
    private String email;     // Almacena el correo electrónico del usuario.
    private String telefono;  // Almacena el número de teléfono del usuario.

    // Constructor que inicializa todos los atributos al crear un objeto Usuario
    public Usuario(String idUsuario, String nombre, String email, String telefono) {
        // Validación del ID del usuario al momento de la construcción del objeto.
        if (!esIdValido(idUsuario)) {
            // Lanza una excepción si el ID contiene caracteres no numéricos.
            throw new IllegalArgumentException("El ID del usuario debe contener solo números.");
        }
        this.idUsuario = idUsuario; // Asignación del ID.
        this.nombre = nombre;       // Asignación del nombre.
        this.email = email;         // Asignación del correo electrónico.
        this.telefono = telefono;   // Asignación del número de teléfono.
    }

    // Métodos de acceso (Getters) y modificación (Setters)
    
    public String getIdUsuario() {
        // Retorna el ID del usuario.
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        // Valida el nuevo ID antes de asignarlo.
        if (!esIdValido(idUsuario)) {
            // Lanza una excepción si el ID no es válido.
            throw new IllegalArgumentException("El ID del usuario debe contener solo números.");
        }
        this.idUsuario = idUsuario; // Asignación del ID.
    }

    public String getNombre() {
        // Retorna el nombre del usuario.
        return nombre;
    }

    public void setNombre(String nombre) {
        // Asigna un nuevo nombre al usuario.
        this.nombre = nombre;
    }

    public String getEmail() {
        // Retorna el correo electrónico del usuario.
        return email;
    }

    public void setEmail(String email) {
        // Valida el formato del correo electrónico antes de asignarlo.
        if (!esEmailValido(email)) {
            // Lanza una excepción si el correo no cumple con el formato válido.
            throw new IllegalArgumentException("El correo electrónico no es válido.");
        }
        this.email = email; // Asignación del correo.
    }

    public String getTelefono() {
        // Retorna el número de teléfono del usuario.
        return telefono;
    }

    public void setTelefono(String telefono) {
        // Valida el nuevo número de teléfono antes de asignarlo.
        if (!esTelefonoValido(telefono)) {
            // Lanza una excepción si el teléfono no contiene solo números.
            throw new IllegalArgumentException("El teléfono solo debe contener números.");
        }
        this.telefono = telefono; // Asignación del número de teléfono.
    }

    // Métodos estáticos de validación para atributos específicos.

    public static boolean esEmailValido(String email) {
        // Valida un correo electrónico usando una expresión regular.
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return Pattern.matches(emailRegex, email); // Retorna true si el formato es válido.
    }

    public static boolean esTelefonoValido(String telefono) {
        // Verifica si un número de teléfono contiene solo dígitos (regex: "\\d+").
        return telefono.matches("\\d+"); // Retorna true si solo contiene números.
    }

    public static boolean esIdValido(String idUsuario) {
        // Valida que el ID contenga solo números (regex: "\\d+").
        return idUsuario.matches("\\d+"); // Retorna true si el ID es válido.
    }

    // Sobrescritura del método toString para representar el objeto como un String.
    @Override
    public String toString() {
        return "ID Usuario: " + idUsuario +  // Incluye el ID del usuario.
               "\nNombre: " + nombre +       // Incluye el nombre.
               "\nEmail: " + email +         // Incluye el correo electrónico.
               "\nTeléfono: " + telefono;    // Incluye el número de teléfono.
    }
}
