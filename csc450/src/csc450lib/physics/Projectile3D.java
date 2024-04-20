package csc450lib.physics;

import csc450lib.calc.FunctionND;
import csc450lib.calc.PolynomialFunction1D;
import csc450lib.exceptions.CSC450ErrorCode;
import csc450lib.exceptions.CSC450Exception;

/**
 * Represents a projectile in three-dimensional space.
 * This class extends the FunctionND class and provides methods to calculate the
 * coordinates of the projectile at a given time.
 */
public class Projectile3D extends FunctionND {
    private static final float g = 9.81f; // Acceleration due to gravity
    private float Vx, Vy, Vz, X0, Y0, H0;

    public Projectile3D(float Vx, float Vy, float Vz, float X0, float Y0, float H0) {
        super(3);
        this.Vx = Vx;
        this.Vy = Vy;
        this.Vz = Vz;
        this.X0 = X0;
        this.Y0 = Y0;
        this.H0 = H0;
    }

    /**
     * Calculates the coordinates of a projectile at a given time.
     * 
     * @param t the time at which to calculate the coordinates
     * @return an array containing the X, Y, and Z coordinates of the projectile
     * @throws CSC450Exception if an error occurs during the calculation
     */
    public float[] getCoordinates(float t) throws CSC450Exception {
        float X = Vx * t + X0;
        float Y = -0.5f * g * t * t + Vy * t + Y0;
        float Z = -0.5f * g * t * t + Vz * t + H0;
        return new float[] { X, Y, Z };
    }

    /**
     * Calculates the Z-coordinate of a projectile at a given time.
     * 
     * @param a An array containing the time value.
     * @return The Z-coordinate of the projectile at the given time.
     * @throws CSC450Exception If the length of the input array is not equal to 1.
     */
    @Override
    public float func(float[] a) throws CSC450Exception {
        // Check if the length is equal to one.
        // In general, func takes (x,y,z) and returns f(x,y,z),
        // but in this case, we are only interested in the Z-coordinate given time, so a
        // will be legth 1.
        if (a.length != 1) {
            throw new CSC450Exception(CSC450ErrorCode.ARRAY_LENGTH_DOES_NOT_EQUAL_FUNCTION_DIMENSION);
        }
        float t = a[0];
        float[] coordinates = getCoordinates(t);
        return coordinates[2]; // Return Z-coordinate
    }

    /**
     * Resolves the projectile motion equation and returns a PolynomialFunction1D
     * object.
     * The PolynomialFunction1D class represents a polynomial function of one
     * variable.
     * It can be used to evaluate the value of the polynomial at a given point.
     *
     * @return A PolynomialFunction1D object representing the resolved projectile
     *         motion equation.
     */
    public PolynomialFunction1D resolve() {
        return new PolynomialFunction1D(new float[] { H0, Vz, -0.5f * g });
    }
}
