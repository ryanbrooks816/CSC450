package testing;

import csc450lib.calc.NonlinearSolver1D_NewtonRaphson;
import csc450lib.calc.NonlinearSolverID_bisection;
import csc450lib.calc.NonlinearSolverID_secant;
import csc450lib.calc.NonlinearSolverRecord1D;
import csc450lib.calc.PolynomialFunction1D;

public class test2b {
    public static void main(String[] args) {
        // Bisection
        // Polynomial 1: y = 2x^3 + 4x^2 + 2x
        float[] a = { 0, 2, 4, 2 };
        PolynomialFunction1D p1 = new PolynomialFunction1D(a); // Expecting very close to X = 0
        System.out.println(p1.getExpressionMMA());

        System.out.println("Bisection Method: ");
        NonlinearSolverID_bisection bisection = new NonlinearSolverID_bisection();
        NonlinearSolverRecord1D record1 = bisection.solve(p1, -0.2f, 0.4f, 20, 0.0001f);
        System.out.println(record1.xStar + "\n" + record1.valStar + "\n" + record1.numIter + "\n" + record1.isSuccess);

        System.out.println("Newton-Raphson Method: ");
        NonlinearSolver1D_NewtonRaphson newtonRaphson = new NonlinearSolver1D_NewtonRaphson();
        NonlinearSolverRecord1D record3 = newtonRaphson.solve(p1, -0.2f, 0.4f, 20, 0.0001f);
        System.out.println(record3.xStar + "\n" + record3.valStar + "\n" + record3.numIter + "\n" + record3.isSuccess);

        System.out.println("Secant Method: ");
        NonlinearSolverID_secant secant = new NonlinearSolverID_secant();
        NonlinearSolverRecord1D record5 = secant.solve(p1, -0.2f, 0.4f, 20, 0.0001f);
        System.out.println(record5.xStar + "\n" + record5.valStar + "\n" + record5.numIter + "\n" + record5.isSuccess);

        // Polynomial 2: y = 3x^2 - 4
        float[] b = { -4, 0, 3 };
        PolynomialFunction1D p2 = new PolynomialFunction1D(b); // Expcting very close to X = -1.15
        System.out.println(p2.getExpressionMMA());

        System.out.println("Bisection Method: ");
        NonlinearSolverID_bisection bisection2 = new NonlinearSolverID_bisection();
        NonlinearSolverRecord1D record2 = bisection2.solve(p2, -2f, 0.5f, 20, 0.0001f);
        System.out.println(record2.xStar + "\n" + record2.valStar + "\n" + record2.numIter + "\n" + record2.isSuccess);

        System.out.println("Newton-Raphson Method: ");
        NonlinearSolver1D_NewtonRaphson newtonRaphson2 = new NonlinearSolver1D_NewtonRaphson();
        NonlinearSolverRecord1D record4 = newtonRaphson2.solve(p2, -2f, 0.5f, 20, 0.0001f);
        System.out.println(record4.xStar + "\n" + record4.valStar + "\n" + record4.numIter + "\n" + record4.isSuccess);

        System.out.println("Secant Method: ");
        NonlinearSolverID_secant secant2 = new NonlinearSolverID_secant();
        NonlinearSolverRecord1D record6 = secant2.solve(p2, -2f, 0.5f, 20, 0.0001f);
        System.out.println(record6.xStar + "\n" + record6.valStar + "\n" + record6.numIter + "\n" + record6.isSuccess);

        /// Make a note in the report that choosing well defined points for the
        /// Newton-Raphson method can involve
        /// not choosing a guess point too far from the expected root, knowing if the
        /// function has complex behavior ner the root,
        /// including multiple rotos or asymptotes, or other singularities.
        /// This also includes knowing that the function is continuous and
        /// differentiable in the interval of interest.
        /// The derivative is not too computationally expensive to calculate, and the
        /// function is not too computationally expensive to evaluate.

        /// I chose to use the midpoint between the interval [a, b] for Newton-Raphson
        /// to give a good guess that will cover most cases.
        /// The interval, of course, will determine the bounds of which the root lies.
    }
}
