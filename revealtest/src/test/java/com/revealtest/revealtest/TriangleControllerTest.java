package com.revealtest.revealtest;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test suite for TriangleController
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TriangleController.class)
public class TriangleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TriangleService triangleService;

    @Test
    public void testEquilateralTriangle() throws Exception {
        Mockito.when(triangleService.classifyTriangle(5.0, 5.0, 5.0)).thenReturn("EQUILATERAL");

        mockMvc.perform(MockMvcRequestBuilders.get("/triangle")
                        .param("a", "5")
                        .param("b", "5")
                        .param("c", "5"))
                .andExpect(status().isOk())
                .andExpect(content().string("EQUILATERAL"));
    }

    @Test
    public void testIsoscelesTriangle() throws Exception {
        Mockito.when(triangleService.classifyTriangle(5.0, 5.0, 3.0)).thenReturn("ISOSCELES");

        mockMvc.perform(MockMvcRequestBuilders.get("/triangle")
                        .param("a", "5")
                        .param("b", "5")
                        .param("c", "3"))
                .andExpect(status().isOk())
                .andExpect(content().string("ISOSCELES"));
    }

    @Test
    public void testScaleneTriangle() throws Exception {
        Mockito.when(triangleService.classifyTriangle(4.0, 5.0, 6.0)).thenReturn("SCALENE");

        mockMvc.perform(MockMvcRequestBuilders.get("/triangle")
                        .param("a", "4")
                        .param("b", "5")
                        .param("c", "6"))
                .andExpect(status().isOk())
                .andExpect(content().string("SCALENE"));
    }

    @Test
    public void testInvalidTriangleSides() throws Exception {
        Mockito.when(triangleService.classifyTriangle(5.0, 3.0, 1.0))
                .thenThrow(new IllegalArgumentException("The provided sides cannot form a triangle. Ensure the sum of any two sides is greater than the third side."));

        mockMvc.perform(MockMvcRequestBuilders.get("/triangle")
                        .param("a", "5")
                        .param("b", "3")
                        .param("c", "1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("The provided sides cannot form a triangle. Ensure the sum of any two sides is greater than the third side."));
    }

    @Test
    public void testNonPositiveSideA() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/triangle")
                        .param("a", "-5")
                        .param("b", "5")
                        .param("c", "5"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Triangle sides should be positive numbers."));
    }

    @Test
    public void testNonPositiveSideB() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/triangle")
                        .param("a", "5")
                        .param("b", "0")
                        .param("c", "5"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Triangle sides should be positive numbers."));
    }

    @Test
    public void testNonPositiveSideC() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/triangle")
                        .param("a", "5")
                        .param("b", "5")
                        .param("c", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Triangle sides should be positive numbers."));
    }

    @Test
    public void testAllNonPositiveSides() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/triangle")
                        .param("a", "-5")
                        .param("b", "0")
                        .param("c", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Triangle sides should be positive numbers."));
    }

    @Test
    public void testUnexpectedError() throws Exception {
        Mockito.when(triangleService.classifyTriangle(3.0, 4.0, 5.0))
                .thenThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(MockMvcRequestBuilders.get("/triangle")
                        .param("a", "3")
                        .param("b", "4")
                        .param("c", "5"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Unexpected error occurred. Please contact the system administrator."));
    }

    @Test
    public void testMultipleMissingParameters() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/triangle")
                        .param("a", "5"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Missing parameters: b c"));
    }

    @Test
    public void testGeneralErrorHandling() throws Exception {
        Mockito.when(triangleService.classifyTriangle(4.0, 5.0, 6.0)).thenThrow(new RuntimeException("General exception"));

        mockMvc.perform(MockMvcRequestBuilders.get("/triangle")
                        .param("a", "4")
                        .param("b", "5")
                        .param("c", "6"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Unexpected error occurred. Please contact the system administrator."));
    }

    @Test
    public void testNonNumericInput() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/triangle")
                        .param("a", "four")
                        .param("b", "5")
                        .param("c", "6"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("All triangle sides should be valid numbers."));
    }

}


