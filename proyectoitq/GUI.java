/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoitq;
import javax.swing.*;  //GUI
import java.awt.*; //Bordes y Estilos
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList; //Lista
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import proyectoitq.RegistroUsuarioGUI2;
import proyectoitq.RegistroProductoGUI2;

public class GUI extends JFrame {

    private JTextArea areaRegistros; // Área de texto para mostrar registros generales
    private ArrayList<String> registros = new ArrayList<>(); // Lista para almacenar los registros
    private JTextArea areaInformacionUsuarios; // Área de texto para los registros de información de usuarios
    private JTextArea areaInformeProductos; // Área de texto para los registros de informe de productos

    // Estilos globales
    private Font fuenteTitulo = new Font("Tahoma", Font.BOLD, 22);  
    private Font fuenteTexto = new Font("Arial", Font.PLAIN, 14);
    private Color colorPrimario = Color.RED;
    private Color colorTexto = new Color(0, 0, 0);

    public GUI() {
        // Configuración de el marco principal
        //Configura el título de la ventana principal de la GUI. En caso de no tener esta opción, la ventana tendrá un título por defecto
        setTitle("Registro WOF-SHOP");

        //Define las dimensiones de la ventana. En caso de no tener esta opción tendrá un tamaño por defecto
        setSize(500, 500);

        //Configura el comportamiento de cierre de la ventana para que el programa termine.
        // En caso de no tener, la ventana se cerrará, pero la aplicación podría seguir ejecutándose en segundo plano.
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Centra la ventana en la pantalla al iniciarse.
        //En caso de no tenerla, la ventana aparecerá en la posición por defecto del sistema operativo, normalmente en la esquina superior izquierda.
        setLocationRelativeTo(null);

        //Establece el diseño de la ventana
        //En caso de tener en default, los componentes se colocarán de manera desordenada, ya que no habrá un diseño definido.
        setLayout(new BorderLayout());

        //Icon de la ventana, en caso de no tener esto, el icon vendrá con la forma de la taza de caféx 
        setIconImage(new ImageIcon(getClass().getResource("img/perrito.jpeg")).getImage());

        //Llama al método que crea y configura la barra de menú con sus submenús y opciones.
        //Como está configurado, si no se le llema entonces la ventana no tendrá barra de menú
        crearBarraDeMenu();

        //Llamar al método de la BDD
        // Panel Central
        //Añade el panel central con componentes como etiquetas, botones y áreas de texto.
        //En caso de no tener esta opción en la configuración entonces la ventana estará vacía, sin contenido  ni funciones
        JPanel panelCentral = crearPanelCentral();
        add(panelCentral, BorderLayout.CENTER);

        //Areas de Información
        //Inicializa las áreas de texto que mostrarán información de usuarios y productos.
        //En caso de no tener esta opción, no se podrán mostrar los registros de usuarios ni de productos.
        areaInformacionUsuarios = new JTextArea(10, 40);
        areaInformeProductos = new JTextArea(10, 40);

        // Mostrar la ventana
        // true, se muestra la ventana
        // false o no poner, no se abrirá nada
        setVisible(true);
    }

        //Crea una nueva interfaz gráfica que es para almacenar el registro del usuario
    private void abrirRegistroUsuario2() {  //Colaboración con la clase Registro Usuario GUI
        new RegistroUsuarioGUI2(this);
    }

    //lo mismo pero de producto
    private void abrirRegistroProducto2() { //Colaboración con la clase Registro Producto GUI
        new RegistroProductoGUI2(this);
    }
    
    GUI(String admin) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    //Crea una etiqueta con estilo para usar como título en la interfaz.
    //En caso de no tener esto, no se podrán añadir títulos propios y personalizados en los paneles.
    private JLabel crearEtiquetaTitulo(String texto, Font fuente, Color color) {
        JLabel etiqueta = new JLabel(texto); //Aquí vendrá el texto
        etiqueta.setFont(fuente); //Usaremos la fuente
        etiqueta.setForeground(color); //Usaremos el color para las letras
        etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT); //Estará alineado de manera horizontal-central
        return etiqueta; //Nos devuelve la variable etiqueta (label)
    }
//lo mismo pero con el texto de introducción

    private JLabel crearEtiquetaTexto(String texto, Font fuente, Color color) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(fuente);
        etiqueta.setForeground(color);
        etiqueta.setAlignmentX(Component.LEFT_ALIGNMENT); //Se alineara de manera horizontal a la izquierda
        return etiqueta;
    }

    @SuppressWarnings("unused") //Sirve solo para quitar las alertas

    //Método para hacer la Barra de Menú y sus componentes
    private void crearBarraDeMenu() {
        //Creamos la barra de menú
        JMenuBar menuBar = new JMenuBar();

        // Menú llamado Intro
        JMenu menuArchivo = new JMenu("Intro");

        // Submenú Creadores con íconos
        JMenu subMenuCreadores = new JMenu("Equipo");

        //Creamos un item al menu de creador1 que va a contener una imagen que obtenemos de la clase, obtenemos de recursos y la dirección en donde se encuentra ubicada la imagen
        //En caso de no tener esto o tener una mala estructura para sacar la imágen, no se verá y se pondrá la default
        JMenuItem creador1 = new JMenuItem("Creador", new ImageIcon(getClass().getResource("/proyectoitq/img/Alejologo.jpg")));
        creador1.addActionListener(
                e -> mostrarInformacionCreador("Creador Código General y Funciones: Alejandro Bedoya"));

        JMenuItem creador2 = new JMenuItem("Gerente", new ImageIcon(getClass().getResource("/proyectoitq/img/Martinlogo.jpg")));
        creador2.addActionListener(
                e -> mostrarInformacionCreador("Ayudante Clases Principales: Martín Rodriguez"));

        JMenuItem creador3 = new JMenuItem("Junior", new ImageIcon(getClass().getResource("/proyectoitq/img/Alanlogo.jpg")));
        creador3.addActionListener(
                e -> mostrarInformacionCreador("Ayudante en crear código base: Alan Velasco"));

        JMenuItem creador4 = new JMenuItem("Diseñador", new ImageIcon(getClass().getResource("/proyectoitq/img/Maiccollogo.jpg")));
        creador4.addActionListener(
                e -> mostrarInformacionCreador("Organizador de Diseño: Maiccol Zurita"));

        // Añadir los creadores al submenú con margen
        subMenuCreadores.add(creador1);
        subMenuCreadores.addSeparator(); // Margen visual
        subMenuCreadores.add(creador2);
        subMenuCreadores.addSeparator();
        subMenuCreadores.add(creador3);
        subMenuCreadores.addSeparator();
        subMenuCreadores.add(creador4);

        // Añadir el submenú a Archivo
        menuArchivo.add(subMenuCreadores);

        // Opción Salir
        //Creamos un item al menu de Salir que va a contener una imagen que obtenemos de la clase, obtenemos de recursos y la dirección en donde se encuentra ubicada la imagen
        //En caso de no tener esto o tener una mala estructura para sacar la imágen, no se verá y se pondrá la default
        JMenuItem itemSalir = new JMenuItem("Salir", new ImageIcon(getClass().getResource("/proyectoitq/img/salida.png")));
        //Configuramos para que cuando de click al botón salir, salga de la aplicación
        itemSalir.addActionListener(e -> System.exit(0));
        //Añadimos Salir al Menu Principal
        menuArchivo.add(itemSalir);

        // Creamos el Menú de Ayuda
        JMenu menuAyuda = new JMenu("Ayuda");
        //Añadimos una imagen dentro del item para un mejor entendimiento del usuario  con una imágen 
        JMenuItem itemAcercaDe = new JMenuItem("Acerca de", new ImageIcon(getClass().getResource("/proyectoitq/img/ayuda.png")));
        //Hacemos que cuando de click al botón, se abra un mensaje de diálogo que pone WofStore Versión Beta Desarrollado por Alejandro Bedoya
        itemAcercaDe.addActionListener(e -> JOptionPane.showMessageDialog(
                this, //Como es propio de la ventana, en vez de colocar un JFrame, solo lo integramos ya que estamos dentro del constructor
                "WofStore\nVersión Beta\nDesarrollado por Alejandro Bedoya",
                "Acerca de",
                JOptionPane.INFORMATION_MESSAGE));
        menuAyuda.add(itemAcercaDe);

        // Añadir menús a la barra
        menuBar.add(menuArchivo);
        menuBar.add(menuAyuda);

        // Establecer la barra de menú
        setJMenuBar(menuBar);
    }

    @SuppressWarnings("unused") //Quita las alertas
    //Crea el panel central con un diseño de cajas para organizar los componentes verticalmente
    //En caso de no tenerlo, No habrá una estructura clara para los componentes del panel.
    private JPanel crearPanelCentral() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); //// Configura el diseño vertical
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Márgenes globales

        // Título
        JLabel lblTitulo = crearEtiquetaTitulo("Bienvenido al Registro de WofStore", fuenteTitulo, colorPrimario); //usamos una fuente global

        // Introducción
        JPanel panelIntroduccion = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Diseño de flujo alineado a la izquierda
        JLabel lblIntroduccion1 = crearEtiquetaTexto(
                "Le damos la bienvenida al sistema de registro de la tienda WofStore", fuenteTexto, colorTexto); //Usamos la otra fuenta global
        panelIntroduccion.add(lblIntroduccion1); //Añadimos el label de introducción al panel de introducción para que se pueda ver el texto

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Diseño de flujo centrado
        JButton btnRegistroUsuario = new JButton("Registrar Usuario"); // Botón para registrar usuario
        JButton btnRegistrarProducto = new JButton("Registrar Producto"); // Botón para registrar producto

        //Métodos de los botones
        btnRegistroUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("prueba para ver si abre");
                abrirRegistroUsuario2();
            }

        }); // Acción al hacer clic en "Registrar Usuario"
        
        btnRegistrarProducto.addActionListener(e -> abrirRegistroProducto2()); // Acción al hacer clic en "Registrar Producto"
        BotonGoti(btnRegistroUsuario); // Damos el estilo al botón
        BotonGoti(btnRegistrarProducto); // Damos el estilo al botón

        panelBotones.add(btnRegistroUsuario); // Agrega el botón al panel
        panelBotones.add(btnRegistrarProducto); // Agrega el botón al panel

        // Área de registros
        JLabel lblRegistros = new JLabel("Objetivo");
        lblRegistros.setAlignmentX(Component.CENTER_ALIGNMENT); // Alineación centrada
        areaRegistros = new JTextArea(10, 40); // Área de texto para mostrar registros
        areaRegistros.setEditable(false); // El área no es editable por el usuario
        areaRegistros.setText("Las mascotas son nuestra prioridad." + "\r\n" + "Sabemos que cada peludo, emplumado"
                + "\r\n" + "o escamoso miembro de tu familia" + "\r\n" + " merece lo mejor, por eso nos dedicamos a ofrecer" + "\r\n"
                + "productos de la más alta calidad como" + "\r\n"
                + "alimentos especializados hasta juguetes diseñados para estimular su bienestar," + "\r\n"
                + "trabajamos para cubrir todas las necesidades de tu compañero" + "\r\n"
                + "garantizando que su vida sea feliz y saludable."); // Mensaje del objetivo de WofStore :)

        areaRegistros.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // Borde alrededor del área de texto
        areaRegistros.setBackground(Color.WHITE); // Color de fondo
        areaRegistros.setForeground(Color.DARK_GRAY); // Color del texto

        JScrollPane scrollPane = new JScrollPane(areaRegistros); //Barra de desplazamiento

        // Agregamos otros botones que son: "Información" e "Informe"
        JPanel panelNuevosBotones = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Diseño de flujo centrado
        
        JButton btnInformacion = new JButton("Personas"); // Botón para mostrar información de personas
        JButton btnInforme = new JButton("Productos"); // Botón para mostrar informe de productos

        btnInformacion.addActionListener(e -> mostrarInformacionUsuarios()); // Acción al hacer clic en "Personas"
        btnInforme.addActionListener(e -> mostrarInformeProductos()); // Acción al hacer clic en "Productos"
   
        BotonGoti(btnInformacion); // Damos el estilo al botón
        BotonGoti(btnInforme);

        panelNuevosBotones.add(btnInformacion); // Agrega el botón al panel
        panelNuevosBotones.add(btnInforme); // Agrega el botón al panel

// Añadir todo al panel principal
//Si quitamos el createRigidArea los componentes se agruparían más estrechamente entre sí, lo que podría hacer que la interfaz se vea más "apretada" o desordenada
        panel.add(lblTitulo); // Agrega el título principal "Bienvenido al Registro de WofStore" al panel.

        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Añade un espaciador vertical de 20 píxeles para separar visualmente el título del siguiente elemento.

        panel.add(panelIntroduccion); // Agrega el panel de introducción que contiene un mensaje de bienvenida.

        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Añade un espaciador vertical de 10 píxeles para separar el panel de introducción de los botones.

        panel.add(panelBotones); // Agrega el panel de botones, que incluye los botones "Registrar Usuario" y "Registrar Producto".

        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Añade un espaciador vertical de 20 píxeles para separar los botones del siguiente elemento.

        panel.add(lblRegistros); // Agrega una etiqueta con el texto "Objetivo" al panel.

        panel.add(scrollPane); // Agrega el área de texto desplazable que muestra el mensaje sobre las prioridades de la tienda.

        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Añade un espaciador vertical de 20 píxeles para separar el área de texto del siguiente elemento.

        panel.add(panelNuevosBotones); // Agrega el panel con los nuevos botones, "Personas" y "Productos".

        return panel; // Devuelve el panel configurado
    }

    public void agregarRegistro(String registro) {

        registros.add(registro); // Añade el registro recibido como parámetro (registro) a una lista llamada registros. Esta lista almacena los registros que se mostrarán en el área de texto.

        areaRegistros.setText(""); // Limpiar el área de texto

        for (String r : registros) { //Recorre cada registro en la lista registros

            areaRegistros.append(r + "\n"); // Mostrar todos los registros en el área de texto
        }
    }

    public void agregarRegistroUsuario(String registro) {
        //Asegura que el registro de usuario se muestre correctamente, añadiéndolo al final del área de texto y separándolo con un salto de línea (\n).
        //Si se elimina este método, no se podrán agregar nuevos registros de usuarios al área de texto areaInformacionUsuarios. (Es la información del botón que dice Información)
        areaInformacionUsuarios.append(registro + "\n"); // Añadir registro de usuario
    }

    public void agregarRegistroProducto(String registro) {
        //Este método funciona de manera similar al de los registros de usuario, pero en este caso, agrega un registro de producto al área de texto areaInformeProductos. 
        //Al igual que el método anterior, añade el texto del registro con un salto de línea (\n).
        //Si eliminamos este método, no se podrán agregar registros de productos al área de texto areaInformeProductos. (Es la información del botón que dice Informe)
        areaInformeProductos.append(registro + "\n"); // Añadir registro de producto
    }

    //Marco en la parte de team-creadores
    private void mostrarInformacionCreador(String mensaje) {
        JFrame ventanaCreador = new JFrame("Estudiantes de ITQ");
        ventanaCreador.setSize(400, 200);
        ventanaCreador.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ventanaCreador.setLocationRelativeTo(this);

        JLabel lblMensaje = new JLabel("<html><center>" + mensaje + "</center></html>", SwingConstants.CENTER);
        lblMensaje.setFont(new Font("Roboto", Font.PLAIN, 16));

        ventanaCreador.add(lblMensaje);
        ventanaCreador.setVisible(true);
    }

   private void mostrarInformacionUsuarios() {
    // Crear la ventana para mostrar la tabla
    JFrame ventanaUsuarios = new JFrame("Información de Usuarios");
    ventanaUsuarios.setSize(800, 400); // Ajustamos el tamaño para que la tabla quepa bien
    ventanaUsuarios.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    ventanaUsuarios.setLocationRelativeTo(this);

    // Crear el modelo de la tabla
    DefaultTableModel modeloTabla = new DefaultTableModel();
    JTable tablaUsuarios = new JTable(modeloTabla);

    // Añadir columnas a la tabla (incluyendo la columna de Cédula)
    modeloTabla.addColumn("Cédula");
    modeloTabla.addColumn("Nombre");
    modeloTabla.addColumn("Email");

    // Cadena de conexión a la base de datos
    String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=WOFSTORE;encrypt=true;trustServerCertificate=true;integratedSecurity=true";

    try (Connection connection = DriverManager.getConnection(jdbcURL)) {
        // Realizar la consulta SQL
        String query = "SELECT * FROM Clientes";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        // Comprobar si la consulta devuelve datos
        boolean hayDatos = false;

        // Recorrer los resultados y añadir filas a la tabla
        while (resultSet.next()) {
            String cedula = resultSet.getString("cedula");
            String nombre = resultSet.getString("nombre_cliente");
            String email = resultSet.getString("email");

            // Añadir la fila con los datos obtenidos de la base de datos
            modeloTabla.addRow(new Object[]{cedula, nombre, email});
            hayDatos = true;
        }

        // Si no hay datos, mostrar mensaje
        if (!hayDatos) {
            JOptionPane.showMessageDialog(null, "No se encontraron usuarios en la base de datos.");
        }

        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al consultar los datos: " + e.getMessage());
    }

    // Crear un JScrollPane para la tabla
    JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
    ventanaUsuarios.add(scrollPane);

    // Hacer visible la ventana
    ventanaUsuarios.setVisible(true);
}
   
   private void mostrarInformeProductos() {
    String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=WOFSTORE;"
            + "encrypt=true;trustServerCertificate=true;integratedSecurity=true";

    JFrame ventanaProductos = new JFrame("Información de Productos");
    ventanaProductos.setSize(800, 400);
    ventanaProductos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    ventanaProductos.setLocationRelativeTo(this);

    // Crear la tabla
    DefaultTableModel modeloTabla = new DefaultTableModel();
    JTable tablaProductos = new JTable(modeloTabla);

    // Definir las columnas
    modeloTabla.addColumn("Código");
    modeloTabla.addColumn("Nombre Producto");
    modeloTabla.addColumn("Precio");
    modeloTabla.addColumn("Stock");

    // Consultar los productos en la base de datos
    try (Connection connection = DriverManager.getConnection(jdbcURL)) {
        String selectQuery = "SELECT * FROM Productos";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = preparedStatement.executeQuery();

        // Llenar la tabla con los resultados de la consulta
        while (resultSet.next()) {
            Object[] fila = new Object[4];
            fila[0] = resultSet.getString("id_producto");
            fila[1] = resultSet.getString("nombre_producto");
            fila[2] = resultSet.getDouble("precio");
            fila[3] = resultSet.getInt("cantidad_en_stock");

            modeloTabla.addRow(fila);
        }

        preparedStatement.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error en la base de datos: " + ex.getMessage());
    }

    // Añadir la tabla a un JScrollPane para hacerla desplazable
    ventanaProductos.add(new JScrollPane(tablaProductos));

    // Hacer visible la ventana
    ventanaProductos.setVisible(true);
}
    private void BotonGoti(JButton boton) {
        boton.setFocusPainted(false); //Evita que el botón muestre un borde de enfoque cuando se selecciona.
        boton.setForeground(Color.WHITE); //Establece el color del texto del botón a blanco.
        boton.setBackground(colorPrimario); // Establece el color de fondo del botón usando una variable
        boton.setFont(new Font("Arial", Font.BOLD, 14)); //Establece la fuente del texto del botón a Arial, en negrita y tamaño 14.
    }

}
