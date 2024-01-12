package com.coursemanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.*;

public class HelloWorldTest {

    @Test
    public void testUsingJavaNewFeature() {

        Queue<String> alphabet = new PriorityQueue<>();

        alphabet.add("A");

        assertEquals("A", alphabet.poll());
    }
}
