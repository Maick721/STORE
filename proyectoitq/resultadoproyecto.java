/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.*;
import java.awt.*;
import proyectoitq.MainGUI;
import proyectoitq.GUI;

public class ResultadoProyecto {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Tipos de Usuarios");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(400, 300);
        ventana.setLocationRelativeTo(null);

        // Crear un panel con imagen de fondo
        JPanel panel = new JPanel(new GridBagLayout()) {
            private Image backgroundImage;

            {
                // Cargar la imagen desde el paquete "proyectoitq.img"
                try {
                    backgroundImage = new ImageIcon(getClass().getResource("/proyectoitq/img/fondo.jpg")).getImage();
                } catch (Exception e) {
                    System.err.println("No se pudo cargar la imagen: " + e.getMessage());
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        panel.setOpaque(false); // Hace transparente el panel para mostrar la imagen
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Componentes del login
        JLabel userLabel = new JLabel("Usuario: ");
        userLabel.setForeground(Color.CYAN);
        userLabel.setFont(userLabel.getFont().deriveFont(Font.BOLD));
        JTextField userField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Contraseña: ");
        passwordLabel.setForeground(Color.CYAN);
        passwordLabel.setFont(passwordLabel.getFont().deriveFont(Font.BOLD));
        JPasswordField passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Iniciar sesión");
        JLabel messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.CYAN);

        // Añadir componentes al panel
        gbc.gridx = 0; gbc.gridy = 0; panel.add(userLabel, gbc);
        gbc.gridx = 1; panel.add(userField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panel.add(passwordLabel, gbc);
        gbc.gridx = 1; panel.add(passwordField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; panel.add(loginButton, gbc);
        gbc.gridy = 3; panel.add(messageLabel, gbc);

        // Lógica de inicio de sesión
        loginButton.addActionListener(e -> {
            String username = userField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                messageLabel.setText("Por favor, complete todos los campos.");
            } else if (username.equals("admin") && password.equals("itq123")) {
                JOptionPane.showMessageDialog(ventana, "Inicio de sesión exitoso como Admin");
                ventana.dispose();
                new MainGUI();
            } else if (username.equals("vendedor") && password.equals("123")) {
                JOptionPane.showMessageDialog(ventana, "Inicio de sesión exitoso como Vendedor");
                ventana.dispose();
                new GUI();
            } else {
                messageLabel.setText("Usuario o contraseña incorrectos.");
            }
        });

        // Estilo del botón
        loginButton.setBackground(new Color(190,114,29));
        loginButton.setForeground(Color.BLACK);
        loginButton.setFocusPainted(false);

        // Añadir el panel al marco
        ventana.add(panel);
        ventana.setVisible(true);
    }
}

// Notas:
/*
 * Los atributos son private para seguir el principio de encapsulación.
 * Esto significa que no se pueden acceder directamente desde fuera de la clase,
 * sino a través de métodos públicos como los getters y setters.
 *
 * 
 * 
 */
