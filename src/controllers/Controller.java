package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dialog; // Para mensajes de diálogo en AWT
import java.awt.Label;
import java.awt.Button;
import java.awt.Frame; // Necesario para el Dialog, ya que la ventana principal es un Frame

import models.Manager;
import views.Window;

public class Controller implements ActionListener {

    private Window window;
    private Manager manager;

    // Constructor del controlador
    public Controller() {
        manager = new Manager();
        window = new Window(this); // Le pasamos 'this' (esta instancia del controlador) a la ventana para los eventos
    }

    // Método principal para iniciar la aplicación
    public static void main(String[] args) {
        new Controller();
    }

    // Método para mostrar un mensaje de diálogo simple (AWT)
    // Se le pasa el Frame de la ventana para que el diálogo sea modal y se centre correctamente
    private void showAWTMessage(String message, String title, Frame owner) {
        Dialog dialog = new Dialog(owner, title, true); // true para modal
        dialog.setLayout(new java.awt.FlowLayout()); // Un layout simple para el diálogo
        dialog.add(new Label(message));
        Button okButton = new Button("OK");
        okButton.addActionListener(e -> dialog.dispose()); // Cierra el diálogo al presionar OK
        dialog.add(okButton);
        dialog.setSize(300, 150); // Tamaño del diálogo
        dialog.setLocationRelativeTo(owner); // Centrar respecto a la ventana principal
        dialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // En AWT, el ActionCommand del botón "Calcular" debería ser "CALCULAR"
        String command = e.getActionCommand(); 

        if ("CALCULAR".equals(command)) { // Si se presionó el botón "CALCULAR"
            // Obtener los valores de los TextField de AWT
            String num1Str = window.getNumero1();
            String num2Str = window.getNumero2();
            String operacion = window.getOperacionSeleccionada(); // Obtiene la operación seleccionada del Choice

            try {
                // Validación de entrada para números vacíos o nulos
                if (num1Str.trim().isEmpty() || num2Str.trim().isEmpty()) {
                    showAWTMessage("Por favor, ingrese valores en ambos campos numéricos.", 
                                  "Error de Entrada", window.getFrame()); // Pasamos el Frame de la ventana
                    window.setResultado("Error"); // Mostrar "Error" en el Label de resultado
                    return;
                }

                // Llamar al Manager para realizar la operación
                // Manager se mantiene igual, lanza excepciones para errores
                String resultado = manager.realizarOperacion(num1Str, num2Str, operacion);
                window.setResultado(resultado); // Mostrar el resultado si todo fue bien

            } catch (NumberFormatException ex) {
                showAWTMessage("Error: Los números ingresados no son válidos. Use solo dígitos y un punto para decimales.", 
                              "Error de Formato", window.getFrame());
                window.setResultado("Error");
            } catch (ArithmeticException ex) {
                showAWTMessage("Error de cálculo: " + ex.getMessage(), 
                              "Error Aritmético", window.getFrame());
                window.setResultado("Error");
            } catch (Exception ex) { // Captura cualquier otra excepción inesperada
                showAWTMessage("Ocurrió un error inesperado: " + ex.getMessage(), 
                              "Error General", window.getFrame());
                window.setResultado("Error");
            }
        }
    }
}