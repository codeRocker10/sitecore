package com.sitecore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    private static final int DEFAULT_VALUE = (int) 1e7;

    Graph graph;

    @BeforeEach
    void setUp() {
        graph = new Graph(getRouteMap());
    }

    @Test
    void shouldReturnDefaultIfRouteNotFound() {
        int distance = graph.displayShortestPath("DUB", "STN");
        assertEquals(distance, DEFAULT_VALUE);
    }

    @Test
    void shouldReturnDefaultIfSourceAndDestinationAirportsAreSame() {
        int distance = graph.displayShortestPath("DUB", "DUB");
        assertEquals(distance, DEFAULT_VALUE);
    }

    @Test
    void shouldReturnDistanceIfDirectRouteFound() {
        int distance = graph.displayShortestPath("DUB", "LHR");
        assertEquals(distance, 1);
    }

    @Test
    void shouldReturnDistanceifConnectingRouteFound() {
        int distance = graph.displayShortestPath("DUB", "BOS");
        assertEquals(distance, 8);
    }

    @Test
    void shouldReturnDistanceifMultiRouteFound() {
        int distance = graph.displayShortestPath("DUB", "SYD");
        assertEquals(distance, 21);
    }

    private Map<String, Integer> getRouteMap() {
        Map<String, Integer> routeMap = new HashMap<>();
        routeMap.put("DUB-LHR", 1);
        routeMap.put("DUB-CDG", 2);
        routeMap.put("CDG-BOS", 6);
        routeMap.put("CDG-BKK", 9);
        routeMap.put("ORD-LAS", 2);
        routeMap.put("LHR-NYC", 5);
        routeMap.put("NYC-LAS", 3);
        routeMap.put("BOS-LAX", 4);
        routeMap.put("LHR-BKK", 9);
        routeMap.put("BKK-SYD", 11);
        routeMap.put("LAX-LAS", 2);
        routeMap.put("DUB-ORD", 6);
        routeMap.put("LAX-SYD", 13);
        routeMap.put("LAS-SYD", 14);
        return routeMap;
    }

}