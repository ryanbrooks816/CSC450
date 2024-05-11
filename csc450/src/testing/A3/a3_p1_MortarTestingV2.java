package testing.A3;

import csc450lib.calc.Function1D;
import csc450lib.physics.Projectile1DFromND;
import csc450lib.calc.NonlinearSolverID_bisection;
import csc450lib.calc.NonlinearSolverRecord1D;
import csc450lib.calc.PolynomialFunctionND;

import java.io.FileWriter;
import java.io.IOException;

public class a3_p1_MortarTestingV2 {

    public static void main(String args[]) throws IOException {
        simulate((float) Math.PI / 4, 10, 1, 2, new float[] { 0, 0 }, new float[][] { { 0 }, { 0 } }, "a3_p1_f3");
        simulate((float) Math.PI / 3, 40, 0, 5, new float[] { 0, 0 },
                new float[][] { { 0, 0, 0, 1 }, { 0, 1, -2 } }, "a3_p1_f6");
    }

    public static void simulate(float theta, float V, float searchStart, float searchEnd, float[] position,
            float[][] groundCoefficients, String filename) throws IOException {

        final float G = -9.81f;

        // This approach exploits the fact that that our mortar shell travels along a
        // parabola, which is a planar curve. The plane of that curve is the vertical
        // plane of azimuthal direction θ that passes through the initial point, at
        // coordinates M0.

        // The azimuthal direction θ defines a new axis in the XY plane, the U axis, and
        // set its origin at the start point of our parabola: u = 0 for X = X0 and Y =
        // Y0. The parabola lies in the plane UY.
        // It starts from the point u0 = 0, Z0 = H(X0, Y0).

        // Similar to the 3D version, we need to define the terrain as an elevation
        // function h(x,y) = z.
        PolynomialFunctionND ground = new PolynomialFunctionND(2, groundCoefficients);

        // We don't need a particle this time, nor velocity or time. We'll define a
        // Function1DFromND object.

        // When we are at coordinate u along the U axis, we are at coordinates (X = X0 +
        // u cos θ, Y = Y0 + u sin θ) in the XY plane. The elevation at that point is
        // therefore h(u) = H(X0 + u cos θ, Y0 + u sin θ).

        // We'll make uv = (cos θ, sin θ) and x0 = (X0, Y0) in order to use them like
        // the position array from ProjectileND. We'll also need an annonymous 1D
        // function to compute the trajectory of the mortar shell.
        Projectile1DFromND projectile = new Projectile1DFromND(ground, position,
                new float[] { (float) Math.cos(theta), (float) Math.sin(theta) });

        Function1D f = new Function1D() {
            @Override
            public float func(float u) {
                float position = u * V * (float) Math.sin(theta) + 0.5f * G * u * u;
                float groundHeight = ground
                        .func(new float[] { u * (float) Math.cos(theta), u * (float) Math.sin(theta) });
                return position - groundHeight;
            }
        };

        // Once again, we'll use a method to simulate the trajectory of the mortar
        // shell. This time, we only need to give it u and the Projectile1DFromND object
        // will take that, calculate the position (X, Y) based on the uv array, then
        // call the ground function's func method to compute the Z value, or the
        // projectile's height.

        try {
            projectile.simulateTrjaectory(filename + ".csv", f);
        } catch (Exception e) {
            System.err.println(e);
        }

        NonlinearSolverID_bisection solver = new NonlinearSolverID_bisection();
        NonlinearSolverRecord1D record = solver.solve(f, searchStart, searchEnd, 100, 0.01f);
        System.out.println("Impact time: " + record.xStar + " seconds");
    }
}
