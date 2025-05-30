package models;

public class Manager {
    // Constructor
    public Manager() {
        // No se requiere inicialización específica por ahora.
    }

    /**
     * Realiza una operación matemática entre dos números.
     * @param num1Str Cadena del primer número.
     * @param num2Str Cadena del segundo número.
     * @param simbolo Símbolo de la operación ("+", "-", "x", "/").
     * @return El resultado de la operación como String.
     * @throws NumberFormatException Si num1Str o num2Str no son números válidos.
     * @throws ArithmeticException Si ocurre una división por cero.
     * @throws IllegalArgumentException Si el símbolo de la operación no es válido.
     */
    public String realizarOperacion(String num1Str, String num2Str, String simbolo) {
        double num1;
        double num2;
        try {
            num1 = Double.parseDouble(num1Str);
            num2 = Double.parseDouble(num2Str);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Uno de los números (" + num1Str + ", " + num2Str + ") no es válido.");
        }

        double solucion = 0;
        switch (simbolo) {
            case "+":
                solucion = num1 + num2;
                break;
            case "-":
                solucion = num1 - num2;
                break;
            case "x":
                solucion = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    throw new ArithmeticException("No se puede dividir por cero.");
                }
                solucion = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("Operación '" + simbolo + "' no válida.");
        }

        // Formatear el resultado: si es un entero (ej. 5.0), lo muestra como entero (5)
        if (solucion == (long) solucion) {
            return String.format("%d", (long) solucion);
        } else {
            return String.valueOf(solucion);
        }
    }
}