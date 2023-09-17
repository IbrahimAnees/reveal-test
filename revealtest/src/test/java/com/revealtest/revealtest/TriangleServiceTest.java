package com.revealtest.revealtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test suite for TriangleService
 */
public class TriangleServiceTest {

    private TriangleService triangleService;

    @BeforeEach
    public void setUp() {
        triangleService = new TriangleService();
    }

    @Test
    public void testEquilateralTriangle() {
        assertEquals("EQUILATERAL", triangleService.classifyTriangle(5.0, 5.0, 5.0));
        assertEquals("EQUILATERAL", triangleService.classifyTriangle(1.0, 1.0, 1.0));
    }

    @Test
    public void testIsoscelesTriangle() {
        assertEquals("ISOCELES", triangleService.classifyTriangle(5.0, 5.0, 3.0));
        assertEquals("ISOCELES", triangleService.classifyTriangle(5.0, 3.0, 5.0));
        assertEquals("ISOCELES", triangleService.classifyTriangle(3.0, 5.0, 5.0));

        assertEquals("ISOCELES", triangleService.classifyTriangle(1.0, 1.0, 0.5));
        assertEquals("ISOCELES", triangleService.classifyTriangle(1.0, 0.5, 1.0));
        assertEquals("ISOCELES", triangleService.classifyTriangle(0.5, 1.0, 1.0));

        assertEquals("ISOCELES", triangleService.classifyTriangle(10.0, 10.0, 5.0));
        assertEquals("ISOCELES", triangleService.classifyTriangle(10.0, 5.0, 10.0));
        assertEquals("ISOCELES", triangleService.classifyTriangle(5.0, 10.0, 10.0));

        assertEquals("ISOCELES", triangleService.classifyTriangle(2.5, 2.5, 1.0));
        assertEquals("ISOCELES", triangleService.classifyTriangle(2.5, 1.0, 2.5));
        assertEquals("ISOCELES", triangleService.classifyTriangle(1.0, 2.5, 2.5));

        assertEquals("ISOCELES", triangleService.classifyTriangle(100.0, 100.0, 50.0));
        assertEquals("ISOCELES", triangleService.classifyTriangle(100.0, 50.0, 100.0));
        assertEquals("ISOCELES", triangleService.classifyTriangle(50.0, 100.0, 100.0));
    }


    @Test
    public void testScaleneTriangle() {
        assertEquals("SCALENE", triangleService.classifyTriangle(4.0, 5.0, 6.0));
        assertEquals("SCALENE", triangleService.classifyTriangle(5.0, 4.0, 6.0));
        assertEquals("SCALENE", triangleService.classifyTriangle(5.0, 6.0, 4.0));

        assertEquals("SCALENE", triangleService.classifyTriangle(10.0, 20.0, 15.0));
        assertEquals("SCALENE", triangleService.classifyTriangle(20.0, 15.0, 10.0));
        assertEquals("SCALENE", triangleService.classifyTriangle(15.0, 10.0, 20.0));

        assertEquals("SCALENE", triangleService.classifyTriangle(100.0, 150.0, 200.0));
        assertEquals("SCALENE", triangleService.classifyTriangle(150.0, 200.0, 100.0));
        assertEquals("SCALENE", triangleService.classifyTriangle(200.0, 100.0, 150.0));
    }


    @Test
    public void testInvalidTriangleSides() {
        assertThrows(IllegalArgumentException.class, () -> {
            triangleService.classifyTriangle(5.0, 3.0, 1.0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            triangleService.classifyTriangle(1.0, 5.0, 3.0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            triangleService.classifyTriangle(3.0, 1.0, 5.0);
        });
    }

    @Test
    public void testZeroSide() {
        assertThrows(IllegalArgumentException.class, () -> {
            triangleService.classifyTriangle(0.0, 5.0, 5.0);
        });
    }

    @Test
    public void testNegativeSide() {
        assertThrows(IllegalArgumentException.class, () -> {
            triangleService.classifyTriangle(-5.0, 5.0, 5.0);
        });
    }
}

