package com.sitecore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * This class is used to find and display the shortest path between two airports using Floyd Warshall Algorithm
 */
public class Graph {

    private static final int DEFAULT_VALUE = (int) 1e7;
    private static final String AIRPORT_SEPARATOR = "-";

    private final int NUMBER_OF_VERTICES;
    private final int[][] DISTANCE, NEXT_STOP, ROUTE_GRAPH;
    List<String> airportList;

    Graph(Map<String, Integer> routeMap) {
        airportList = getUniqueAirportList(routeMap);
        NUMBER_OF_VERTICES = airportList.size();

        DISTANCE = new int[NUMBER_OF_VERTICES][NUMBER_OF_VERTICES];
        NEXT_STOP = new int[NUMBER_OF_VERTICES][NUMBER_OF_VERTICES];
        ROUTE_GRAPH = getAirportRouteMatrix(routeMap);

        initialiseDistanceAndNextStop();
        calculateDistance();
    }

    /**
     * This method displays the shortest path between two airports.
     *
     * @param sourceAirport      IATA code of the source airport.
     * @param destinationAirport IATA coee of the destination airport.
     * @return path
     */

    int displayShortestPath(String sourceAirport, String destinationAirport) {
        if (!airportList.contains(sourceAirport) || !airportList.contains(destinationAirport) || sourceAirport.equals(destinationAirport)) {
            System.out.println("No Path found.");
            return DEFAULT_VALUE;
        }

        Vector<Edge> path = constructShortestPath(sourceAirport, destinationAirport);
        if (path == null) {
            System.out.println("No Path found.");
        } else {
            return printPath(path);
        }
        return DEFAULT_VALUE;
    }

    /**
     * This method prints the calculated path in readable format.
     *
     * @param path calculated path that needs to be printed in readable format
     */
    private int printPath(Vector<Edge> path) {
        int totalDistance = 0;
        System.out.println("Shortest route:");

        for (Edge edge : path) {
            totalDistance += ROUTE_GRAPH[edge.sourcePosition][edge.destinationPosition];
            System.out.println(airportList.get(edge.sourcePosition) + " -> " + airportList.get(edge.destinationPosition) + "(" + ROUTE_GRAPH[edge.sourcePosition][edge.destinationPosition] + ")");
        }

        System.out.println(totalDistance);
        return totalDistance;
    }

    /**
     * This method returns the list of unique airports
     *
     * @param routeMap map of the routes
     * @return list of unique airports
     */
    private List<String> getUniqueAirportList(Map<String, Integer> routeMap) {
        List<String> airportList = new ArrayList<>();
        for (Map.Entry<String, Integer> mapElement : routeMap.entrySet()) {
            String key = mapElement.getKey();
            String sourceAirport = key.split(AIRPORT_SEPARATOR)[0];
            String destinationAirpot = key.split(AIRPORT_SEPARATOR)[1];
            if (!airportList.contains(sourceAirport)) {
                airportList.add(sourceAirport);
            } else if (!airportList.contains((destinationAirpot))) {
                airportList.add(destinationAirpot);
            }
        }
        return airportList;
    }

    /**
     * This method create the Airport route matrix - distance of airports from each other.
     *
     * @param routeMap map of all the routes.
     * @return airport route matrix
     */
    private int[][] getAirportRouteMatrix(Map<String, Integer> routeMap) {
        int[][] graph = new int[NUMBER_OF_VERTICES][NUMBER_OF_VERTICES];
        for (int row = 0; row < airportList.size(); row++) {
            for (int col = 0; col < airportList.size(); col++) {
                String airportListKey = airportList.get(row) + AIRPORT_SEPARATOR + airportList.get(col);
                if (row == col) {
                    graph[row][col] = 0;
                } else {
                    graph[row][col] = routeMap.getOrDefault(airportListKey, DEFAULT_VALUE);
                }
            }
        }
        return graph;
    }

    /**
     * This method initialize the distance and next stop matrix
     */
    private void initialiseDistanceAndNextStop() {
        for (int i = 0; i < NUMBER_OF_VERTICES; i++) {
            for (int j = 0; j < NUMBER_OF_VERTICES; j++) {
                DISTANCE[i][j] = ROUTE_GRAPH[i][j];

                // No edge between node i and j
                if (ROUTE_GRAPH[i][j] == DEFAULT_VALUE)
                    NEXT_STOP[i][j] = -1;
                else
                    NEXT_STOP[i][j] = j;
            }
        }
    }

    /**
     * This  method constructs the shortest path between source node and destination node
     *
     * @param sourceAirport      IATA code for source airport
     * @param destinationAirport IATA code for destination airport
     * @return
     */
    private Vector<Edge> constructShortestPath(String sourceAirport, String destinationAirport) {

        int sourceIataPostion = airportList.indexOf(sourceAirport);
        int destinationIataPosition = airportList.indexOf(destinationAirport);

        // If there's no path between node source and destination, simply return an empty array
        if (NEXT_STOP[sourceIataPostion][destinationIataPosition] == -1)
            return null;

        // Storing the path in a vector
        Vector<Edge> path = new Vector<>();
        path.add(new Edge(sourceIataPostion, NEXT_STOP[sourceIataPostion][destinationIataPosition]));

        while (sourceIataPostion != destinationIataPosition) {
            sourceIataPostion = NEXT_STOP[sourceIataPostion][destinationIataPosition];
            if (sourceIataPostion != destinationIataPosition) {
                path.add(new Edge(sourceIataPostion, NEXT_STOP[sourceIataPostion][destinationIataPosition]));
            }
        }
        return path;
    }

    /**
     * This method calculate the distance and modify next stop.
     */
    private void calculateDistance() {
        for (int k = 0; k < NUMBER_OF_VERTICES; k++) {
            for (int i = 0; i < NUMBER_OF_VERTICES; i++) {
                for (int j = 0; j < NUMBER_OF_VERTICES; j++) {

                    // We cannot travel through edge that doesn't exist
                    if (DISTANCE[i][k] == DEFAULT_VALUE || DISTANCE[k][j] == DEFAULT_VALUE)
                        continue;

                    if (DISTANCE[i][j] > DISTANCE[i][k] + DISTANCE[k][j]) {
                        DISTANCE[i][j] = DISTANCE[i][k] + DISTANCE[k][j];
                        NEXT_STOP[i][j] = NEXT_STOP[i][k];
                    }
                }
            }
        }
    }

}
