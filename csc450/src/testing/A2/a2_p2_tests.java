package testing.A2;

import csc450lib.calc.NonlinearSolver1D_NewtonRaphson;
import csc450lib.calc.NonlinearSolverID_bisection;
import csc450lib.calc.NonlinearSolverID_secant;
import csc450lib.calc.NonlinearSolverRecord1D;
import csc450lib.calc.PolynomialFunction1D;

public class a2_p2_tests {
        public static void main(String[] args) {
                // Bisection
                // Polynomial 1: y = 2x^3 + 4x^2 + 2x
                float[] a = { 0, 2, 4, 2 };
                PolynomialFunction1D p1 = new PolynomialFunction1D(a); // Expecting very close to X = 0
                System.out.println(p1.getExpressionMMA());

                System.out.println("Bisection Method: ");
                NonlinearSolverID_bisection bisection = new NonlinearSolverID_bisection();
                long startTime = System.nanoTime();
                NonlinearSolverRecord1D record1 = bisection.solve(p1, -0.2f, 0.4f, 20, 0.0001f);
                long endTime = System.nanoTime();
                System.out.println(record1.xStar + " | " + record1.valStar + " | " + record1.numIter + " | "
                                + record1.isSuccess);
                long durationBisection = (endTime - startTime); // compute the elapsed time
                System.out.println("Bisection Method took: " + durationBisection + " nanoseconds");

                System.out.println("Newton-Raphson Method: ");
                NonlinearSolver1D_NewtonRaphson newtonRaphson = new NonlinearSolver1D_NewtonRaphson();
                startTime = System.nanoTime();
                NonlinearSolverRecord1D record3 = newtonRaphson.solve(p1, -0.2f, 0.4f, 20, 0.0001f);
                endTime = System.nanoTime();
                System.out.println(record3.xStar + " | " + record3.valStar + " | " + record3.numIter + " | "
                                + record3.isSuccess);
                long durationNewtonRaphson = (endTime - startTime); // compute the elapsed time
                System.out.println("Newton-Raphson Method took: " + durationNewtonRaphson + " nanoseconds");

                System.out.println("Secant Method: ");
                NonlinearSolverID_secant secant = new NonlinearSolverID_secant();
                startTime = System.nanoTime();
                NonlinearSolverRecord1D record5 = secant.solve(p1, -0.2f, 0.4f, 20, 0.0001f);
                endTime = System.nanoTime();
                System.out.println(record5.xStar + " | " + record5.valStar + " | " + record5.numIter + " | "
                                + record5.isSuccess);
                long durationSecant = (endTime - startTime); // compute the elapsed time
                System.out.println("Secant Method took: " + durationSecant + " nanoseconds");

                // Polynomial 2: y = 3x^2 - 4
                float[] b = { -4, 0, 3 };
                PolynomialFunction1D p2 = new PolynomialFunction1D(b); // Expcting very close to X = -1.15
                System.out.println("\n" + p2.getExpressionMMA());

                System.out.println("Bisection Method: ");
                NonlinearSolverID_bisection bisection2 = new NonlinearSolverID_bisection();
                startTime = System.nanoTime();
                NonlinearSolverRecord1D record2 = bisection2.solve(p2, -2f, 0.5f, 20, 0.0001f);
                endTime = System.nanoTime();
                System.out.println(record2.xStar + " | " + record2.valStar + " | " + record2.numIter + " | "
                                + record2.isSuccess);
                durationBisection = (endTime - startTime); // compute the elapsed time
                System.out.println("Bisection Method took: " + durationBisection + " nanoseconds");

                System.out.println("Newton-Raphson Method: ");
                NonlinearSolver1D_NewtonRaphson newtonRaphson2 = new NonlinearSolver1D_NewtonRaphson();
                startTime = System.nanoTime();
                NonlinearSolverRecord1D record4 = newtonRaphson2.solve(p2, -2f, 0.5f, 20, 0.0001f);
                endTime = System.nanoTime();
                System.out.println(record4.xStar + " | " + record4.valStar + " | " + record4.numIter + " | "
                                + record4.isSuccess);
                durationNewtonRaphson = (endTime - startTime); // compute the elapsed time
                System.out.println("Newton-Raphson Method took: " + durationNewtonRaphson + " nanoseconds");

                System.out.println("Secant Method: ");
                NonlinearSolverID_secant secant2 = new NonlinearSolverID_secant();
                startTime = System.nanoTime();
                NonlinearSolverRecord1D record6 = secant2.solve(p2, -2f, 0.5f, 20, 0.0001f);
                endTime = System.nanoTime();
                System.out.println(record6.xStar + " | " + record6.valStar + " | " + record6.numIter + " | "
                                + record6.isSuccess);
                durationSecant = (endTime - startTime); // compute the elapsed time
                System.out.println("Secant Method took: " + durationSecant + " nanoseconds");
        }
}
