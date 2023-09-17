package com.revealtest.revealtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for classifying triangle
 */
@RestController
@CrossOrigin
public class TriangleController {

    private final TriangleService triangleService;

    @Autowired
    public TriangleController(TriangleService triangleService) {
        this.triangleService = triangleService;
    }

    /**
     * GET /triangle endpoint which classifies a triangle based on given sides
     * @param a side A
     * @param b side B
     * @param c side C
     * @return classification of triangle
     */
    @GetMapping("/triangle")
    public ResponseEntity<String> getTriangleType(@RequestParam(required = false) String a,
                                                  @RequestParam(required = false) String b,
                                                  @RequestParam(required = false) String c) {
        // Check for missing parameters
        if (a == null || b == null || c == null) {
            StringBuilder missingParams = new StringBuilder("Missing parameters: ");
            if (a == null) missingParams.append("a ");
            if (b == null) missingParams.append("b ");
            if (c == null) missingParams.append("c ");
            return new ResponseEntity<>(missingParams.toString().trim(), HttpStatus.BAD_REQUEST);
        }

        try {
            double sideA = Double.parseDouble(a);
            double sideB = Double.parseDouble(b);
            double sideC = Double.parseDouble(c);

            // Validate parsed numbers
            if (sideA <= 0 || sideB <= 0 || sideC <= 0) {
                return new ResponseEntity<>("Triangle sides should be positive numbers.", HttpStatus.BAD_REQUEST);
            }

            String result = triangleService.classifyTriangle(sideA, sideB, sideC);
            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (NumberFormatException e) {
            return new ResponseEntity<>("All triangle sides should be valid numbers.", HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Unexpected error occurred. Please contact the system administrator.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
