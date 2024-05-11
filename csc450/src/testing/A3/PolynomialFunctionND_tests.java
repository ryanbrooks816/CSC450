package testing.A3;

import java.io.FileWriter;
import java.io.IOException;

import csc450lib.calc.PolynomialFunctionND;
import csc450lib.exceptions.CSC450Exception;

public class PolynomialFunctionND_tests {
    public static void main(String[] args) {
        // Define the coefficients for the function x^2 + y^2
        float[][] coefficients = { { 0, 1 }, { 0, 0 } };

        // Create a new instance of PolynomialFunctionND
        PolynomialFunctionND function = new PolynomialFunctionND(2, coefficients);

        try (FileWriter writer = new FileWriter("7.csv")) {
            // Generate x and y values from -8 to 8
            for (float x = -8; x <= 8; x++) {
                for (float y = -8; y <= 8; y++) {
                    // Calculate the z value
                    float z = function.func(new float[] { x, y });

                    // Write the x, y, and z values to the CSV file
                    writer.append(x + "," + y + "," + z + "\n");
                }
            }
        } catch (IOException | CSC450Exception e) {
            e.printStackTrace();
        }
    }
}