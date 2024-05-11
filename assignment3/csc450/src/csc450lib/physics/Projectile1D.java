package csc450lib.physics;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import csc450lib.calc.Function1D;

/**
 * The Projectile class represents a projectile's motion in a 2D space.
 * It tracks the position, speed, and reflection coefficient of the projectile.
 * This is a modified version of the bllasitic function
 * 
 */
public class Projectile1D {
    private float x, y; // Current position
    private float Vx, Vy; // Current speed in x and y direction
    private float α; // Reflection coefficient

    /**
     * Construct a ballistic projectile for the Projectile class
     * 
     * @param x0 Initial x-coordinate
     * @param y0 Initial y-coordinate
     * @param V  Initial speed
     * @param γ  Launch angle
     * @param α  Reflection coefficient
     */
    public Projectile1D(float x0, float y0, float V, float γ, float α) {
        this.x = x0;
        this.y = y0;
        this.Vx = V * (float) Math.cos(γ);
        this.Vy = V * (float) Math.sin(γ);
        this.α = α;
    }

    /**
     * Updates the position and speed of the projectile after a time interval dt
     */
    public void func(float dt) {
        // Update position
        x += Vx * dt;
        y += Vy * dt - 0.5f * 9.81f * dt * dt; // Subtract gravitational acceleration

        // Update vertical speed
        Vy -= 9.81f * dt;
    }

    /**
     * Updates the speed of the projectile after a bounce by computing the angle
     * incident to the ground and the angle of reflection.
     * 
     * @param ground The ground Fucntion1D
     */
    public void getOutgoingVelocity(Function1D ground) {
        // Calculate the angle of the ground at the point of contact
        float groundAngle = (float) Math.atan(ground.dfunc(x));

        // Calculate the angle of incidence
        float incidenceAngle = (float) Math.atan2(-Vy, Vx) - groundAngle;

        // Calculate the speed after the bounce
        float V = α * (float) Math.hypot(Vx, Vy);

        // Calculate the angle of reflection
        float reflectionAngle = Math.abs(-incidenceAngle);

        // Calculate the new speed components
        Vx = V * (float) Math.cos(reflectionAngle + groundAngle);
        Vy = V * (float) Math.sin(reflectionAngle + groundAngle);
    }

    /**
     * Simulates the motion of the projectile and writes the position to a file
     * 
     * @param ground   The ground Function1D
     * @param dt       Time step
     * @param timeout  Maximum time to simulate
     * @param filename Name of the file to write the position to
     * @throws IOException
     */
    public void simulate(Function1D ground, float dt, float timeout, String filename) {
        float timeElapsed = 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Continue until the speed is below a threshold or timeout is reached (for
            // testing)
            while (Math.hypot(Vx, Vy) > 0.01 && timeElapsed < timeout) {
                func(dt);
                if (y < ground.func(x)) { // Check if y is below the ground level
                    y = ground.func(x); // Set y to the ground level
                    // Calculate the new speed and direction of the projectile based on the
                    // reflection coefficient and the ground
                    getOutgoingVelocity(ground);
                }
                // Writes the position to the file for each time step
                writer.write(x + ", " + y + "\n");
                timeElapsed += dt;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the x-coordinate of the projectile
     * 
     * @return x-coordinate
     */
    public void setReflectionCoefficient(float α) {
        this.α = α;
    }
}