# CSC 450 - Assignment 2

## Code Organization

The code is organized into packages under `csc450lib`. 

The files of interest for this assignment are:
- `NonlinearSolver1D` and related `calc` classes
- All files under the `testing` package for testing each part of the assignment
- *As well as all the csv files* under the root folder as well. These will be required to view the graphs and animations in Mathematica if they animations break in the zip sharing process.

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
- NonlinearSolver1D: Represents an abstract NLE solver to be implemented in sub-classes.
  - NonlinearSolverRecord1D: A record object returned from a NLE solver that contains information about the process and the value found.
  - NonlinearSolver1D_NewtonRaphson: Extneds NonelinearSolver1D with the Newton-Raphson implementation.
  - NonlinearSolver1D_bisection: Extneds NonelinearSolver1D with bisection implementation.
  - NonlinearSolver1D_secant: Extneds NonelinearSolver1D with secant implementation.

The `physics` package contains classes used to model the behavior of objects.
- Projectile1D: Used to model in the behavior of a bouncing ball for assingment 2.

The `exceptions` package contains the CSC 450 exceptions classes provided.

The `testing` package contains unit tests for different parts of all the assignments, in sub-folders.
