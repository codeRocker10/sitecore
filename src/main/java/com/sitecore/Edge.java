package com.sitecore;

/**
 * This class represents the edge of the graph (sourcePosition->destinationPosition)
 */
class Edge {
    int sourcePosition;
    int destinationPosition;

    public Edge(int sourcePosition, int destinationPosition) {
        this.sourcePosition = sourcePosition;
        this.destinationPosition = destinationPosition;
    }
}