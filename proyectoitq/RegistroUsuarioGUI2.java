package proyectoitq; // Define el paquete donde se encuentra la clase.

import java.awt.*; // Importa clases para manejar componentes gráficos.
import javax.swing.*; // Importa clases de la biblioteca Swing para interfaces gráficas.
import javax.swing.text.AttributeSet; // Importa clases necesarias para la manipulación de texto.
import javax.swing.text.BadLocationException; // Importa la excepción usada en la manipulación de texto.
import javax.swing.text.AbstractDocument; // Importa la clase base para manejar documentos.
import javax.swing.text.DocumentFilter; // Importa la clase para aplicar filtros de texto en campos.
import java.sql.*; // Importa las clases necesarias para la conexión con bases de datos SQL.
import java.awt.event.*; // Importa clases para manejar eventos como clics de botones.
import proyectoitq.GUI; // Importa la clase principal MainGUI.

public class RegistroUsuarioGUI2 extends JFrame { // Define la clase que extiende JFrame para la interfaz gráfica.
    

    // Resto de la clase
    // Declaración de los campos de texto y botón
    private JTextField txtCedula, txtNombre, txtApellido, txtTelefono, txtEmail; // Campos de texto para datos del usuario.
    private JButton btnGuardar; // Botón para guardar los datos en la base de datos.

    // URL de conexión a la base de datos SQL Server
     String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=WOFSTORE;"
            + "encrypt=true;trustServerCertificate=true;integratedSecurity=true";

    // Constructor de la clase, recibe una referencia a MainGUI
    public RegistroUsuarioGUI2(GUI gui) {
        setTitle("Registro de Clientes"); // Establece el título de la ventana.
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana al cerrarla.
        setSize(500, 300); // Establece el tamaño de la ventana.
        setLayout(new GridLayout(6, 2)); // Define un layout de 6 filas y 2 columnas.

        // Etiqueta y campo para la cédula
        JLabel lblCedula = new JLabel("Cédula: "); // Crea la etiqueta para "Cédula".
        txtCedula = new JTextField(); // Crea el campo de texto para ingresar la cédula.
        // Aplica filtro para permitir solo números y máximo 10 dígitos
        ((AbstractDocument) txtCedula.getDocument()).setDocumentFilter(new NumericDocumentFilter(10));

        // Etiqueta y campo para el nombre
        JLabel lblNombre = new JLabel("Nombre: "); // Crea la etiqueta para "Nombre".
        txtNombre = new JTextField(); // Crea el campo de texto para el nombre.
        // Aplica filtro para permitir solo letras y espacios
        ((AbstractDocument) txtNombre.getDocument()).setDocumentFilter(new TextDocumentFilter());

        // Etiqueta y campo para el apellido
        JLabel lblApellido = new JLabel("Apellido: "); // Crea la etiqueta para "Apellido".
        txtApellido = new JTextField(); // Crea el campo de texto para el apellido.
        // Aplica filtro para permitir solo letras y espacios
        ((AbstractDocument) txtApellido.getDocument()).setDocumentFilter(new TextDocumentFilter());

        // Etiqueta y campo para el teléfono
        JLabel lblTelefono = new JLabel("Teléfono: "); // Crea la etiqueta para "Teléfono".
        txtTelefono = new JTextField(); // Crea el campo de texto para el teléfono.
        // Aplica filtro para permitir solo números y máximo 10 dígitos
        ((AbstractDocument) txtTelefono.getDocument()).setDocumentFilter(new NumericDocumentFilter(10));

        // Etiqueta y campo para el email
        JLabel lblEmail = new JLabel("Email: "); // Crea la etiqueta para "Email".
        txtEmail = new JTextField(); // Crea el campo de texto para el email.

        // Botón para guardar los datos
        btnGuardar = new JButton("Guardar"); // Crea el botón "Guardar".

        // Añadir componentes a la ventana
        add(lblCedula);
        add(txtCedula);
        add(lblNombre);
        add(txtNombre);
        add(lblApellido);
        add(txtApellido);
        add(lblTelefono);
        add(txtTelefono);
        add(lblEmail);
        add(txtEmail);
        add(new JLabel()); // Espacio vacío en la cuadrícula.
        add(btnGuardar);

        // Evento del botón Guardar
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Captura los datos ingresados en los campos
                String cedula = txtCedula.getText();
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                String telefono = txtTelefono.getText();
                String email = txtEmail.getText();

                try {
                    // Métodos para validar los campos ingresados
                    validarCedula(cedula);
                    validarNombreApellido(nombre);
                    validarNombreApellido(apellido);
                    validarTelefono(telefono);
                    validarEmail(email);

                    // Llama al método para registrar el cliente
                    registrarCliente(cedula, nombre, apellido, telefono, email);

                } catch (Exception ex) {
                    // Muestra un mensaje de error si ocurre una excepción
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true); // Hace visible la ventana.
    }

    RegistroUsuarioGUI2( ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Clase interna para permitir solo números con longitud limitada
    private static class NumericDocumentFilter extends DocumentFilter {
        private final int maxLength; // Longitud máxima permitida para el campo.

        public NumericDocumentFilter(int maxLength) {
            this.maxLength = maxLength; // Inicializa la longitud máxima.
        }

        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string.matches("[0-9]*") && (fb.getDocument().getLength() + string.length() <= maxLength)) {
                super.insertString(fb, offset, string, attr); // Inserta si cumple con la longitud y solo números.
            } else {
                JOptionPane.showMessageDialog(null, "Solo se permiten números con un máximo de " + maxLength + " dígitos.");
            }
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String string, AttributeSet attr) throws BadLocationException {
            if (string.matches("[0-9]*") && (fb.getDocument().getLength() - length + string.length() <= maxLength)) {
                super.replace(fb, offset, length, string, attr); // Reemplaza si cumple con la longitud y solo números.
            } else {
                JOptionPane.showMessageDialog(null, "Solo se permiten números con un máximo de " + maxLength + " dígitos.");
            }
        }
    }

    // Clase interna para permitir solo letras y caracteres especiales
    private static class TextDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]*")) {
                super.insertString(fb, offset, string, attr); // Inserta si contiene solo letras o espacios.
            } else {
                JOptionPane.showMessageDialog(null, "Solo se permiten letras y espacios.");
            }
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String string, AttributeSet attr) throws BadLocationException {
            if (string.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]*")) {
                super.replace(fb, offset, length, string, attr); // Reemplaza si contiene solo letras o espacios.
            } else {
                JOptionPane.showMessageDialog(null, "Solo se permiten letras y espacios.");
            }
        }
    }

    // Valida la cédula
    private void validarCedula(String cedula) throws Exception {
        if (cedula.isEmpty()) throw new Exception("La cédula no puede estar vacía.");
        if (cedula.length() != 10) throw new Exception("La cédula debe tener 10 dígitos.");
        if (!cedula.matches("\\d+")) throw new Exception("La cédula debe contener solo números.");
    }

    // Valida el nombre o apellido
    private void validarNombreApellido(String texto) throws Exception {
        if (texto.isEmpty()) throw new Exception("El nombre/apellido no puede estar vacío.");
        if (!texto.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+")) throw new Exception("Solo se permiten letras.");
    }

    // Valida el teléfono
    private void validarTelefono(String telefono) throws Exception {
        if (telefono.isEmpty()) throw new Exception("El teléfono no puede estar vacío.");
        if (telefono.length() != 10) throw new Exception("El teléfono debe tener 10 dígitos.");
    }

    // Valida el email
    private void validarEmail(String email) throws Exception {
        if (email.isEmpty()) throw new Exception("El email no puede estar vacío.");
        if (!email.matches("^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,3}$")) throw new Exception("Formato de email incorrecto.");
    }

    // Registra un cliente en la base de datos
    private void registrarCliente(String cedula, String nombre, String apellido, String telefono, String email) {
        try (Connection connection = DriverManager.getConnection(jdbcURL)) {
            String insertQuery = "INSERT INTO Clientes (cedula, nombre_cliente, apellido_cliente, telefono, email) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, cedula);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, apellido);
            preparedStatement.setString(4, telefono);
            preparedStatement.setString(5, email);

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos: " + ex.getMessage());
        }
    }
}
