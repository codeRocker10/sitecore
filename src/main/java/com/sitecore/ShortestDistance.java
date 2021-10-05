package com.sitecore;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ShortestDistance {

    public static void main(String[] args) throws IOException {

        ShortestDistance shortestDistance = new ShortestDistance();
        Map<String, Integer> routeHashMap = shortestDistance.getHashmap();
        Graph graph = new Graph(routeHashMap);
        String moreSearch = "y";
        while (moreSearch.equals("y")) {
            Scanner reader = new Scanner(System.in);
            System.out.println("Enter IATA code of source airport: ");
            String sourceAirport = reader.next();
            System.out.println("Enter IATA code of destination airport: ");
            String destinationAirport = reader.next();
            graph.displayShortestPath(sourceAirport, destinationAirport);
            System.out.println("Do you want to search more (y/n)? ");
            moreSearch = reader.next();
        }
    }

    private Map<String, Integer> getHashmap() {
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
