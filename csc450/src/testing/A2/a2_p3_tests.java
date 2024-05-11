package testing.A2;

import csc450lib.calc.FunctionComposition1D;
import csc450lib.calc.PolynomialFunction1D;
import csc450lib.calc.RootFunction1D;
import csc450lib.calc.SinFunction1D;
import csc450lib.physics.Projectile1D;

public class a2_p3_tests {
    public static void main(String[] args) {
        // Create a ground y = 0
        PolynomialFunction1D ground = new PolynomialFunction1D(new float[] { 0 });
        System.out.println(ground.getExpressionMMA());

        // Create a projectile with initial position (0, 10), speed 10, angle 45
        // degrees, and reflection coefficient 1
        Projectile1D projectile = new Projectile1D(0, 10, 10, (float) Math.PI / 4, 0.85f);

        // Simulate the motion of the projectile for 10 seconds with a time step of 0.01
        // seconds
        projectile.simulate(ground, 0.005f, 100, "output1.csv");

        // Create a ground y = 1/4x
        PolynomialFunction1D ground2 = new PolynomialFunction1D(new float[] { 0, 0.25f });
        System.out.println(ground2.getExpressionMMA());

        Projectile1D projectile2 = new Projectile1D(0, 10, 10, (float) Math.PI / 4, 0.85f);
        projectile2.simulate(ground2, 0.005f, 100, "output2.csv");

        // Create a ground y = sin(2x^(1/2))
        RootFunction1D ground3g = new RootFunction1D(2, 1, 0, 0, 2);
        SinFunction1D ground3f = new SinFunction1D();
        FunctionComposition1D ground3 = new FunctionComposition1D(ground3f, ground3g);
        System.out.println(ground3.getExpressionMMA());

        Projectile1D projectile3 = new Projectile1D(0, 10, 10, (float) Math.PI / 4, 0.85f);
        projectile3.simulate(ground3, 0.005f, 100, "output3.csv");

        // Create a ground y = 1/6x^2
        PolynomialFunction1D ground4 = new PolynomialFunction1D(new float[] { 0, 0, 0.16f });
        System.out.println(ground4.getExpressionMMA());

        Projectile1D projectile4 = new Projectile1D(0, 10, 10, (float) Math.PI / 4, 0.85f);
        projectile4.simulate(ground4, 0.005f, 100, "output4.csv");
    }
}
