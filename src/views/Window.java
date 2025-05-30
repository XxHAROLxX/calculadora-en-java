package views;

import java.awt.Button;
import java.awt.Choice; // Equivalente a JComboBox en AWT
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;  // El marco principal en AWT, reemplaza JFrame
import java.awt.GridLayout;
import java.awt.Label;   // Equivalente a JLabel en AWT, reemplaza JLabel
import java.awt.Panel;   // Equivalente a JPanel en AWT, reemplaza JPanel
import java.awt.TextField; // Campo de texto en AWT
import java.awt.BorderLayout; // Usamos BorderLayout de AWT
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter; // Para manejar el cierre de la ventana en AWT
import java.awt.event.WindowEvent;

public class Window {
    private Frame ventana; // Usamos Frame en lugar de JFrame
    private TextField txtNumero1; // Usamos TextField de AWT
    private TextField txtNumero2; // Usamos TextField de AWT
    private Choice comboOperacion; // Usamos Choice en lugar de JComboBox
    private Label lblResultado; // Usamos Label en lugar de JLabel
    private Button btnCalcular; // Usamos Button de AWT
    private Panel panelEntrada; // Usamos Panel en lugar de JPanel
    private Panel panelResultado; // Usamos Panel en lugar de JPanel
    private Panel panelBotones; // Usamos Panel en lugar de JPanel

 // Constantes de color (ajustadas a tonos de ROJO)
    private static final Color COLOR_FONDO_VENTANA = new Color(30, 30, 30); // Un negro suave, no puro, para evitar un contraste demasiado duro.    private static final Color COLOR_PANEL_FONDO = new Color(255, 200, 200);    // Rojo pálido, más rosado
    private static final Color COLOR_TEXTFIELDS_FONDO = Color.WHITE;             // Blanco puro para campos de texto
    private static final Color COLOR_LABEL_RESULTADO_FONDO = new Color(170, 0, 0); // Rojo oscuro y profundo para el resultado
    private static final Color COLOR_BOTON_CALCULAR = new Color(220, 20, 60);   // Rojo carmesí vibrante para el botón
    private static final Color COLOR_TEXTO_NUMEROS_SIMBOLOS = Color.BLACK;       // Texto negro para números y símbolos
    private static final Color COLOR_TEXTO_RESULTADO = Color.WHITE;              // Texto blanco para el resultado
    // Constructor
    public Window(ActionListener controller) {
        configurarVentana();
        configurarComponentes(controller);
        ventana.setVisible(true);
    }

    private void configurarVentana() {
        ventana = new Frame("Calculadora AWT"); // Creamos un Frame (AWT)
        ventana.setSize(400, 300);
        ventana.setLocationRelativeTo(null); // Centrar en la pantalla
        // Para AWT, necesitas un WindowListener para manejar el cierre de la ventana
        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0); // Termina la aplicación al cerrar la ventana
            }
        });
        ventana.setBackground(COLOR_FONDO_VENTANA); // Establece el color de fondo del Frame
        ventana.setLayout(new BorderLayout(10, 10)); // Usamos BorderLayout de AWT
    }

    private void configurarComponentes(ActionListener controller) {
        // Panel para la entrada de números y selección de operación
        panelEntrada = new Panel(new GridLayout(3, 2, 5, 5)); // Usamos Panel y GridLayout de AWT
        panelEntrada.setBackground(COLOR_FONDO_VENTANA); // Color de fondo del panel de entrada

        Label labelNum1 = new Label("Número 1:"); // Label de AWT
        labelNum1.setFont(new Font("Arial", Font.PLAIN, 16));
        panelEntrada.add(labelNum1);
        txtNumero1 = new TextField(10); // TextField de AWT
        txtNumero1.setFont(new Font("Arial", Font.BOLD, 20));
        txtNumero1.setBackground(COLOR_TEXTFIELDS_FONDO);
        txtNumero1.setForeground(COLOR_TEXTO_NUMEROS_SIMBOLOS);
        panelEntrada.add(txtNumero1);

        Label labelOperacion = new Label("Operación:"); // Label de AWT
        labelOperacion.setFont(new Font("Arial", Font.PLAIN, 16));
        panelEntrada.add(labelOperacion);
        comboOperacion = new Choice(); // Choice de AWT
        comboOperacion.add("+");
        comboOperacion.add("-");
        comboOperacion.add("x");
        comboOperacion.add("/");
        comboOperacion.setFont(new Font("Arial", Font.BOLD, 20));
        comboOperacion.setBackground(COLOR_TEXTFIELDS_FONDO);
        comboOperacion.setForeground(COLOR_TEXTO_NUMEROS_SIMBOLOS);
        panelEntrada.add(comboOperacion);

        Label labelNum2 = new Label("Número 2:"); // Label de AWT
        labelNum2.setFont(new Font("Arial", Font.PLAIN, 16));
        panelEntrada.add(labelNum2);
        txtNumero2 = new TextField(10); // TextField de AWT
        txtNumero2.setFont(new Font("Arial", Font.BOLD, 20));
        txtNumero2.setBackground(COLOR_TEXTFIELDS_FONDO);
        txtNumero2.setForeground(COLOR_TEXTO_NUMEROS_SIMBOLOS);
        panelEntrada.add(txtNumero2);

        ventana.add(panelEntrada, BorderLayout.NORTH); // Añade el panel de entrada en la parte superior

        // Panel para el botón de cálculo
        panelBotones = new Panel(); // Panel de AWT, usa FlowLayout por defecto
        panelBotones.setBackground(COLOR_FONDO_VENTANA); // Mismo color de fondo que la ventana
        btnCalcular = new Button("Calcular"); // Button de AWT
        btnCalcular.setFont(new Font("Arial", Font.BOLD, 24));
        btnCalcular.setBackground(COLOR_BOTON_CALCULAR); // Color lila oscuro para el botón
        btnCalcular.setForeground(Color.WHITE); // Texto del botón blanco
        // En AWT, no hay setOpaque o setBorderPainted como en Swing
        btnCalcular.setActionCommand("CALCULAR"); // Comando de acción para el controlador
        btnCalcular.addActionListener(controller); // Asigna el ActionListener
        panelBotones.add(btnCalcular);
        ventana.add(panelBotones, BorderLayout.CENTER); // Añade el panel de botones en el centro

        // Panel para mostrar el resultado
        panelResultado = new Panel(new BorderLayout()); // Panel de AWT
        panelResultado.setBackground(COLOR_LABEL_RESULTADO_FONDO); // Fondo lila intermedio
        
        lblResultado = new Label("Resultado:", Label.CENTER); // Label de AWT, centrado
        lblResultado.setFont(new Font("Arial", Font.BOLD, 36));
        lblResultado.setForeground(COLOR_TEXTO_RESULTADO); // Texto del resultado blanco
        panelResultado.add(lblResultado, BorderLayout.CENTER); // Añade la etiqueta al panel de resultado

        ventana.add(panelResultado, BorderLayout.SOUTH); // Añade el panel de resultado en la parte inferior
    }

    // Métodos para que el Controller obtenga los valores de los TextField
    public String getNumero1() {
        return txtNumero1.getText();
    }

    public String getNumero2() {
        return txtNumero2.getText();
    }

    // Método para que el Controller obtenga la operación seleccionada del Choice
    public String getOperacionSeleccionada() {
        return comboOperacion.getSelectedItem(); // Método de Choice de AWT
    }

    // Método para que el Controller establezca el resultado en el Label
    public void setResultado(String resultado) {
        lblResultado.setText("Resultado: " + resultado);
    }
    
    // Método para que el Controller pueda obtener el Frame (ventana principal)
    // Esto es necesario para los diálogos de error de AWT (showAWTMessage)
    public Frame getFrame() {
        return ventana;
    }
}