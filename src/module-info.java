module Calculadora {
    requires java.desktop; // Necesario para AWT (java.awt.*) y Swing (javax.swing.*)
    exports controllers;
    exports models;
    exports views;
}