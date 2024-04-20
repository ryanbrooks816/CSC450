package testing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import csc450lib.calc.NonlinearSolver1D_NewtonRaphson;
import csc450lib.calc.NonlinearSolverRecord1D;
import csc450lib.calc.PolynomialFunction1D;
import csc450lib.physics.Projectile3D;

public class a3_p1_MortarV1Testing {

    public static void main(String[] args) throws IOException {
        float Vz = 20.0f; // Initial vertical velocity
        float H0 = 0.0f; // Initial height
        float Vx = 10.0f; // Initial X velocity
        float Vy = 10.0f; // Initial Y velocity
        float X0 = 0.0f; // Initial X position
        float Y0 = 0.0f; // Initial Y position

        Projectile3D p = new Projectile3D(Vx, Vy, Vz, X0, Y0, H0);
        PolynomialFunction1D pZ = p.resolve();
        NonlinearSolver1D_NewtonRaphson solver = new NonlinearSolver1D_NewtonRaphson();
        NonlinearSolverRecord1D solution = solver.solve(pZ, 0.0f, 100.0f, 100, 0.0001f);

        // Output the time when the shell hits the ground
        System.out.println("The shell hits the ground at time: " + solution.xStar);

        // Write the data to a file for visualization in Mathematica
        oututData(p, "output1.csv", 0.0f, solution.xStar, 0.05f);
        // Changing the delta t to a smaller value results in closer precicion to when
        // the shell extactly hits the ground, but results in far more data points and a
        // more expensive animation.
    }

    public static void oututData(Projectile3D p, String filename, float tStart, float tEnd, float tStep)
            throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (float t = tStart; t <= tEnd; t += tStep) {
                float[] coordinates = p.getCoordinates(t);
                writer.write(t + "," + coordinates[0] + "," + coordinates[1] + "," + coordinates[2] + "\n");
            }
        }
    }
}

// Given this info, create the height equation for the trajectory of the shell
// in 3D.
// Solve with a NLE solver
// Output when the shell hits the ground

// At time 0 ≤ t ≤ timpact, the shell occupies the 3D point of coordinates
// M(t) = ( X(t), Y(t), Z(t) ) = 1/2 ( 0, 0, g ) * t^2 + ( Vx, Vy, Vz ) * t + (
// X0, Y0, H(X0, Y0) )
// The point of impact, when t = timpact, lies on the surface defined by the
// elevation map:
// Z(timpact) = H ( X (timpact), Y (timpact) )
// If you substitute for X (timpact) and Y (timpact) in Equation (3) using the
// equation of the trajectory
// Equation (2), then you can get a nonlinear equation with a single unknown,
// timpact,
// f(timpact) = 0.
