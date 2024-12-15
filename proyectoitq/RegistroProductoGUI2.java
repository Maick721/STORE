package proyectoitq; // Define que la clase pertenece al paquete 'proyectoitq'.

import java.awt.*; // Importa todas las clases necesarias para el diseño de interfaces gráficas utilizando AWT.
import javax.swing.*; // Importa todas las clases del paquete Swing para construir la interfaz gráfica.
import java.sql.*; // Importa las clases necesarias para interactuar con bases de datos utilizando JDBC.
import java.awt.event.*; // Importa las clases necesarias para manejar eventos en componentes gráficos.
import javax.swing.text.AbstractDocument; // Clase que permite manipular documentos de texto de Swing.
import javax.swing.text.DocumentFilter; // Clase para aplicar restricciones en los campos de texto.
import javax.swing.text.AttributeSet; // Clase que describe los atributos de un documento de texto.
import javax.swing.text.BadLocationException; // Excepción lanzada si ocurre un error al manipular texto en un documento.
import proyectoitq.GUI; // Importa la clase MainGUI del paquete 'proyectoitq'.


// Clase principal que hereda de JFrame para crear una ventana gráfica.
public class RegistroProductoGUI2 extends JFrame {

    // Declaración de los campos de texto y botón utilizados en la interfaz.
    private JTextField txtNombreProducto, txtPrecio, txtStock;
    private JButton btnGuardar;

    // Cadena de conexión para la base de datos SQL Server.
    String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=WOFSTORE;"
            + "encrypt=true;trustServerCertificate=true;integratedSecurity=true";

    // Constructor de la clase que recibe una instancia de MainGUI como parámetro.
    public RegistroProductoGUI2(GUI gui) {
        setTitle("Registro de Productos"); // Establece el título de la ventana.
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Define la acción al cerrar la ventana.
        setSize(500, 200); // Establece el tamaño de la ventana.
        setLayout(new GridLayout(4, 2)); // Utiliza un diseño de cuadrícula con 4 filas y 2 columnas.

        // Creación de etiquetas y campos de texto.
        JLabel lblNombreProducto = new JLabel("Nombre del Producto: "); // Etiqueta para el nombre del producto.
        txtNombreProducto = new JTextField(); // Campo de texto para el nombre del producto.
        // Aplica un filtro para restringir la entrada a texto.
        ((AbstractDocument) txtNombreProducto.getDocument()).setDocumentFilter(new TextDocumentFilter());

        JLabel lblPrecio = new JLabel("Precio: "); // Etiqueta para el precio del producto.
        txtPrecio = new JTextField(); // Campo de texto para el precio.
        // Aplica un filtro para restringir la entrada a números decimales.
        ((AbstractDocument) txtPrecio.getDocument()).setDocumentFilter(new DecimalDocumentFilter());

        JLabel lblStock = new JLabel("Stock: "); // Etiqueta para el stock del producto.
        txtStock = new JTextField(); // Campo de texto para el stock.
        // Aplica un filtro para restringir la entrada a números enteros.
        ((AbstractDocument) txtStock.getDocument()).setDocumentFilter(new NumericDocumentFilter());

        btnGuardar = new JButton("Guardar"); // Botón para guardar los datos.

        // Agrega los componentes a la ventana utilizando el diseño definido.
        add(lblNombreProducto);
        add(txtNombreProducto);
        add(lblPrecio);
        add(txtPrecio);
        add(lblStock);
        add(txtStock);
        add(new JLabel()); // Agrega un espacio vacío en el diseño.
        add(btnGuardar);

        // Define la acción del botón Guardar utilizando una expresión lambda.
        btnGuardar.addActionListener(e -> {
            // Obtiene los valores ingresados por el usuario en los campos de texto.
            String nombreProducto = txtNombreProducto.getText();
            String precio = txtPrecio.getText();
            String stock = txtStock.getText();

            try {
                // Valida los datos ingresados.
                validarNombreProducto(nombreProducto);
                validarPrecio(precio);
                validarStock(stock);

                // Registra el producto en la base de datos si las validaciones son exitosas.
                registrarProducto(nombreProducto, Double.parseDouble(precio), Integer.parseInt(stock));

            } catch (Exception ex) {
                // Muestra un mensaje de error si ocurre alguna excepción.
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true); // Hace visible la ventana gráfica.
    }

    RegistroProductoGUI2() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Filtros personalizados para los campos de texto.

    // Filtro que permite solo letras y espacios en los campos de texto.
    private static class TextDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string.matches("[a-zA-Z\\s]+")) { // Verifica si la entrada contiene solo letras y espacios.
                super.insertString(fb, offset, string, attr);
            } else {
                Toolkit.getDefaultToolkit().beep(); // Emite un sonido si la entrada es inválida.
            }
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String string, AttributeSet attr) throws BadLocationException {
            if (string.matches("[a-zA-Z\\s]+")) { // Verifica si la entrada contiene solo letras y espacios.
                super.replace(fb, offset, length, string, attr);
            } else {
                Toolkit.getDefaultToolkit().beep(); // Emite un sonido si la entrada es inválida.
            }
        }
    }

    // Filtro que permite solo números decimales en los campos de texto.
    private static class DecimalDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string.matches("[0-9]*\\.?[0-9]*")) { // Permite números con o sin punto decimal.
                super.insertString(fb, offset, string, attr);
            } else {
                Toolkit.getDefaultToolkit().beep(); // Emite un sonido si la entrada es inválida.
            }
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String string, AttributeSet attr) throws BadLocationException {
            if (string.matches("[0-9]*\\.?[0-9]*")) { // Permite números con o sin punto decimal.
                super.replace(fb, offset, length, string, attr);
            } else {
                Toolkit.getDefaultToolkit().beep(); // Emite un sonido si la entrada es inválida.
            }
        }
    }

    // Filtro que permite solo números enteros en los campos de texto.
    private static class NumericDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string.matches("\\d*")) { // Permite solo dígitos.
                super.insertString(fb, offset, string, attr);
            } else {
                Toolkit.getDefaultToolkit().beep(); // Emite un sonido si la entrada es inválida.
            }
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String string, AttributeSet attr) throws BadLocationException {
            if (string.matches("\\d*")) { // Permite solo dígitos.
                super.replace(fb, offset, length, string, attr);
            } else {
                Toolkit.getDefaultToolkit().beep(); // Emite un sonido si la entrada es inválida.
            }
        }
    }

    // Métodos de validación.

    private void validarNombreProducto(String nombre) throws Exception {
        if (nombre.isEmpty()) { // Verifica si el nombre está vacío.
            throw new Exception("El nombre del producto no puede estar vacío.");
        }
    }

    private void validarPrecio(String precio) throws Exception {
        if (precio.isEmpty()) { // Verifica si el precio está vacío.
            throw new Exception("El precio no puede estar vacío.");
        }
        try {
            double precioValue = Double.parseDouble(precio); // Intenta convertir el precio a double.
            if (precioValue <= 0) { // Verifica si el precio es mayor a 0.
                throw new Exception("El precio debe ser mayor que 0.");
            }
        } catch (NumberFormatException e) {
            throw new Exception("El precio debe ser un número válido."); // Lanza una excepción si la conversión falla.
        }
    }

    private void validarStock(String stock) throws Exception {
        if (stock.isEmpty()) { // Verifica si el stock está vacío.
            throw new Exception("El stock no puede estar vacío.");
        }
        try {
            int stockValue = Integer.parseInt(stock); // Intenta convertir el stock a entero.
            if (stockValue <= 0) { // Verifica si el stock es mayor a 0.
                throw new Exception("El stock debe ser mayor que 0.");
            }
        } catch (NumberFormatException e) {
            throw new Exception("El stock debe ser un número entero válido."); // Lanza una excepción si la conversión falla.
        }
    }

    // Método para registrar un producto en la base de datos.
    private void registrarProducto(String nombre, double precio, int stock) {
        try (Connection connection = DriverManager.getConnection(jdbcURL)) { // Crea una conexión a la base de datos.
            String insertQuery = "INSERT INTO Productos (nombre_producto, precio, cantidad_en_stock) VALUES (?, ?, ?)"; // Consulta SQL para insertar datos.
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery); // Prepara la consulta.
            preparedStatement.setString(1, nombre); // Establece el primer parámetro (nombre del producto).
            preparedStatement.setDouble(2, precio); // Establece el segundo parámetro (precio).
            preparedStatement.setInt(3, stock); // Establece el tercer parámetro (stock).

            int filasAfectadas = preparedStatement.executeUpdate(); // Ejecuta la consulta y obtiene el número de filas afectadas.
            if (filasAfectadas > 0) { // Verifica si el registro fue exitoso.
                int confirm = JOptionPane.showConfirmDialog(null, "Producto registrado exitosamente. ¿Deseas registrar otro?", "Éxito", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) { // Si el usuario desea registrar otro producto.
                    txtNombreProducto.setText(""); // Limpia el campo de texto del nombre del producto.
                    txtPrecio.setText(""); // Limpia el campo de texto del precio.
                    txtStock.setText(""); // Limpia el campo de texto del stock.
                } else {
                    dispose(); // Cierra la ventana si no desea registrar otro producto.
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error al registrar el producto."); // Muestra un mensaje de error si el registro falla.
            }

            preparedStatement.close(); // Cierra el PreparedStatement.
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos: " + ex.getMessage()); // Muestra un mensaje de error si ocurre una excepción de SQL.
        }
    }
}
