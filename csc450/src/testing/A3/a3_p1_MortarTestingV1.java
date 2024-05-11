package testing.A3;

import java.io.FileWriter;
import java.io.IOException;

import csc450lib.calc.Function1D;
import csc450lib.calc.NonlinearSolverID_bisection;
import csc450lib.calc.NonlinearSolverRecord1D;
import csc450lib.calc.PolynomialFunctionND;
import csc450lib.exceptions.CSC450Exception;
import csc450lib.physics.ProjectileND;

public class a3_p1_MortarTestingV1 {

    public static void main(String args[]) throws IOException {
        simulate((float) Math.PI / 4, (float) Math.PI / 4, 10, 1, 2, new float[] { 0, 0, 0, },
                new float[][] { { 0 }, { 0 } }, "a3_p1_f1");
        simulate((float) Math.PI / 3, (float) Math.PI / 3, 40, 1, 10, new float[] { 0, 0, 0 },
                new float[][] { { 0, 0.25f }, { 0, 0.25f } }, "a3_p1_f2");
        simulate((float) Math.PI / 3, (float) Math.PI / 3, 40, 0, 5, new float[] { 0, 0, 0 },
                new float[][] { { 0, 0, 0, 1 }, { 0, 1, -2 } }, "a3_p1_f5");
    }

    public static void simulate(float theta, float phi, float V, float searchStart, float searchEnd, float[] position,
            float[][] groundCoefficients, String filename) throws IOException {
        // The terrain is an elevation function where Z = H(X, Y ) is the elevation at
        // (X, Y ).

        // A mortar is installed at coordinates (X0, Y0) on the map. Therefore the
        // occupies a 3D point at coordinates M0 = (X0, Y0, Z0) = (X0, Y0, H(X0, Y0)).

        // Our mortar fires a shell with an initial velocity vector such that its
        // vertical coordinate (along the Z axis) is positive.

        PolynomialFunctionND ground = new PolynomialFunctionND(2, groundCoefficients);

        // We are going to specify this initial velocity vector using an azimuth angle θ
        // ∈ [0, 2π], an elevation angle ϕ ∈ [0, π/2] (since it must be pointing“up”)
        // and a magnitude V.

        // Projectile has 2 constructors. One accepts the azimuth, elevation, and
        // magnitude and convers to Cartesian coordinates using the expression
        // V0 = (Vx,Vy, Vz) = V (cos θ cos ϕ, sin θ cos ϕ, sin ϕ), and the other
        // constructor directly accepts Vx, Vy, Vz.

        // Create a projectile with initial position (0, 0, 0) and velocity (Vx, Vy, Vz)
        ProjectileND projectile = new ProjectileND(position, theta, phi, V, ground);

        // Along with the Function ND class, we need to determine the coordinates of the
        // impact point of the mortar’s shell.

        // At time 0 ≤ t ≤ timpact, the shell occupies the 3D point of coordinates
        // M(t) = (X(t), Y(t), Z(t)) = 1/2(0, 0, -g)*t^2 + (Vx, Vy, Vz)*t + (X0, Y0,
        // H(X0, Y0)). (Equation 2)

        // The point of impact, when t = timpact, lies on the surface defined by the
        // elevation map: Z(timpact) = h (X(timpact), Y(timpact)). (Equation 3)

        // If you substitute for X(timpact) and Y(timpact) in Equation 3 using the
        // equation of the trajectory in Equation (2),
        // then you can get a nonlinear equation with a single unknown, timpact,
        // f(timpact) = 0.

        // This method simulates the trajectory of the mortar shell and outputs its
        // results to a CSV to be read in Mathematica and plotted.
        try {
            float[] endingPos = projectile.simulateTrjaectory(filename + ".csv");

            // Write the ground representation to a CSV file for testing
            float stepXSize = endingPos[0] / 10;
            float stepYSize = endingPos[1] / 10;

            try (FileWriter writer = new FileWriter(filename + "_ground.csv")) {
                // Generate x and y values from the initial position to the ending position
                for (float x = position[0]; x <= endingPos[0]; x += stepXSize) {
                    for (float y = position[1]; y <= endingPos[1]; y += stepYSize) {
                        // Calculate the z value
                        float z = ground.func(new float[] { x, y });

                        // Write the x, y, and z values to the CSV file
                        writer.append(x + "," + y + "," + z + "\n");
                    }
                }
            } catch (IOException | CSC450Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        // Last we use an NLE solver to find the specific point of impact. This is a
        // different implementation to the simulation above, but it achieves the same
        // result.

        // This version simulates in 1D, using time and the height of the projecile. It
        // follows the same logic as the 3D simulation.
        Function1D f = new Function1D() {
            @Override
            public float func(float t) {
                float[] position = projectile.func(t);
                float groundHeight = ground.func(new float[] { position[0], position[1] });
                return position[2] - groundHeight;
            }
        };

        projectile.simulateTrjaectory1D(filename + "_1D.csv", f);

        NonlinearSolverID_bisection solver = new NonlinearSolverID_bisection();
        NonlinearSolverRecord1D record = solver.solve(f, searchStart, searchEnd, 100, 0.01f);
        System.out.println("Impact time: " + record.xStar + " seconds");
    }
}
