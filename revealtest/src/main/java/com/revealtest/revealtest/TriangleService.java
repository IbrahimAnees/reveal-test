package com.revealtest.revealtest;

import org.springframework.stereotype.Service;

/**
 * Service class for classifying triangle
 */
@Service
public class TriangleService {

    /**
     * Classifies triangle given sides
     * @param a side A
     * @param b side B
     * @param c side C
     * @return classification of triangle
     */
    public String classifyTriangle(double a, double b, double c) {
        // Check if sides can form a triangle
        if (!canFormTriangle(a, b, c)) {
            throw new IllegalArgumentException("The provided sides cannot form a triangle. Ensure the sum of any two sides is greater than the third side.");
        }

        if (a == b && b == c) {
            return "EQUILATERAL";
        } else if (a == b || b == c || a == c) {
            return "ISOCELES";
        } else {
            return "SCALENE";
        }
    }

    /**
     * Helper method to check if triangle is valid
     * @param a side A
     * @param b side B
     * @param c side C
     * @return true if valid, false if invalid
     */
    private boolean canFormTriangle(double a, double b, double c) {
        return a + b > c && b + c > a && a + c > b;
    }

}

