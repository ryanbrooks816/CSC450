package testing;

import csc450lib.calc.CosFunction1D;
import csc450lib.calc.DomainOfDefinition;
import csc450lib.calc.Function1D;
import csc450lib.calc.PolynomialFunction1D;
import csc450lib.calc.RationalFunction1D;
import csc450lib.calc.RootFunction1D;
import csc450lib.calc.SinFunction1D;
import csc450lib.calc.FunctionComposition1D;
import csc450lib.calc.FunctionMultiplication1D;

/**
 * The randomizePoints class generates random points based on a given function
 * and domain of definition.
 */
public class randomizePoints {

    /**
     * The main method of the randomizePoints class.
     * It creates a various function objects and DomainOfDefinition objects, then
     * calls the generateRandomPoints method.
     *
     * @param args the command-line arguments
     */
    public static void main(String args[]) {
        // Basic polynomial function
        PolynomialFunction1D p1 = new PolynomialFunction1D(new float[] { 0, 2, 4, 2 });
        System.out.println(p1.getExpressionMMA());
        DomainOfDefinition d1 = new DomainOfDefinition(-10, 10);
        generateRandomPoints(p1, d1, "points1.txt");

        // Basic sin function
        SinFunction1D s1 = new SinFunction1D(4.3f, 1, 0, 3);
        System.out.println(s1.getExpressionMMA());
        DomainOfDefinition d2 = new DomainOfDefinition((float) (-2 * Math.PI), (float) (2 * Math.PI));
        generateRandomPoints(s1, d2, "points2.txt");

        // Composition of functions
        SinFunction1D s2 = new SinFunction1D(1, 1, 0, 0);
        PolynomialFunction1D p2 = new PolynomialFunction1D(new float[] { 5, -2, 0, 0, 1 });
        DomainOfDefinition d3 = new DomainOfDefinition(-1.5f, 1.5f);
        FunctionComposition1D fog = new FunctionComposition1D(s2, p2);

        PolynomialFunction1D p3 = new PolynomialFunction1D(new float[] { 0, 0, 1 });
        FunctionComposition1D foh = new FunctionComposition1D(p3, fog);
        System.out.println(foh.getExpressionMMA());
        generateRandomPoints(foh, d3, "points3.txt");

        // Function with high chance of error #1
        RootFunction1D r1 = new RootFunction1D(1, 1, 1, -1, 2);
        PolynomialFunction1D p6 = new PolynomialFunction1D(new float[] { 0, 0, 1 });
        DomainOfDefinition d5 = new DomainOfDefinition(-0.005f, 0.005f);
        FunctionComposition1D fog3 = new FunctionComposition1D(r1, p6);
        System.out.println(fog3.getExpressionMMA());
        generateRandomPoints(fog3, d5, "points4.txt");

        // Function with high chance of error #2
        // f[x_] := x^5 + 10^8 x^4 + 10^7 x^3 + 10^6 x^2 + 10^5 x + 10^4
        PolynomialFunction1D p4 = new PolynomialFunction1D(
                new float[] { 10000, 100000, 1000000, 10000000, 100000000, 1 });
        System.out.println(p4.getExpressionMMA());
        DomainOfDefinition d4 = new DomainOfDefinition(100000, 1000000);
        generateRandomPoints(p4, d4, "points5.txt");
    }

    /**
     * Generates 100 random points based on the given function and domain of
     * definition, and writes them to a file.
     *
     * @param f        the function to generate points for
     * @param d        the domain of definition for the points
     * @param fileName the name of the file to write the points to
     */
    public static void generateRandomPoints(Function1D f, DomainOfDefinition d, String fileName) {
        java.io.File file = new java.io.File(fileName);
        try (java.io.PrintWriter output = new java.io.PrintWriter(file)) {
            for (int i = 0; i < 100; i++) {
                float x = (float) (Math.random() * (d.getUpperBound() - d.getLowerBound()) + d.getLowerBound());
                output.println(x + ", " + f.func(x));
            }
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
