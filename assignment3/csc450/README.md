# CSC 450 - Assignment 3

## Code Organization

The code is organized into packages under `csc450lib`. 

The files of interest for this assignment are:
- `physics/Project3D.java`: for the mortar simulation
- `calc/Norm1Function1D.java`: for the partial registration application
- `calc/Norm2Function1D.java`: for the partial registration application
- `calc/NormInfinityFunction1D.java`: for the partial registration application
- `./a3_notebook.nb`: The 3 Mathematica notebooks containing the report for all parts of the assignment.
- The testing A3 package contains all the unit tests for this assignment.
  - 2 files for both Mortar implemtnations
  - `a3_p3_optimization_tests.java` for unit testing optimization methods.
  - `a3_p4_PartialRegistration_tests` for functions to help test the partial registration application.
  - `a3_p4_PartialRegistration_tests_2` for functions to help test the partial registration application with multiple points.
- *As well as all the csv files* under the root folder as well. These will be required to view the graphs and animations in Mathematica if they break in the zip sharing process.

## csc450 library

The `calc` package contains all the classes required for computation throughout all assignments.
- Function1D: Represents an abstract function object to be implemented in sub-classes. Also includes a derivative approximation implementation.
  - PolynomialFunction1D: Extends Function1D and provides a polynomial function implementation.
  - SinFunction1D: Extends Function1D and provides the sin function implementation.
  - CosFunction1D: Extends Function1D and provides the cosine function implementation
  - RootFunction1D: Extends Function1D and provides root function implemtnations.
  - DomainOfDefinition: Represents the domain of definition for a mathematical function.
  - FunctionComposition1D: Allows for creating composite functions and extends Function1D behavior.
  - FunctionMultiplication1D: Allows for multiplying functions and extends Function1D behavior.
- FunctionND: Represents an abstract function object in ND space to be implemented in sub-classes.
- FunctionNDfrom1D: Allows for computing and evaluating a 1D function given a function in ND space.
- NonlinearSolver1D: Represents an abstract NLE solver to be implemented in sub-classes.
  - NonlinearSolverRecord1D: A record object returned from a NLE solver that contains information about the process and the value found.
  - NonlinearSolver1D_NewtonRaphson: Extneds NonelinearSolver1D with the Newton-Raphson implementation.
  - NonlinearSolver1D_bisection: Extneds NonelinearSolver1D with bisection implementation.
  - NonlinearSolver1D_secant: Extneds NonelinearSolver1D with secant implementation.
- Optimizer1D: Represents an abstract optimizer function to be implemented in sub-classes.
  - OptimizerRecord1D: A record object returned from the minimize method that contains information about the process and the value found.
  - Optimizer1D_steepestDescent: Implements the steepest descent method to minimize the function.
  - Optimizer1D_gss: Implementes the GSS method to minimize the function
  - Optimizer1D_parabolic: Implements the parabolic appriximation method to minimize the function.
- Norm1Function1D: Extends Function1D behavior with a func method to compute the point in the norm 1 function.
- Norm2Function1D: Extends Function1D behavior with a func method to compute the point in the norm 2 function.
- NormInfinityFunction1D: Extends Function1D behavior with a func method to compute the point in the norm infinity function.

The `physics` package contains classes used to model the behavior of objects.
- Projectile1D: Used to model in the behavior of a bouncing ball for assingment 2.
- Projectile3D: Extends FunctionND with an implementation to compute the position and value of the projectile over time. Used in assignment 3 to model the mortar.
- Projectile1DFrom3D: Used to model the behavior of the mortar shell from ND to 1D as part of assignment 3.

The `exceptions` package contains the CSC 450 exceptions classes provided.

The `testing` package contains unit tests for different parts of the assignments. Files limited to only assignment 3 for clarity.
