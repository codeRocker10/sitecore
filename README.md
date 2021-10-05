In this assignment I used Floyd warshall algorithm for find out the shortest path between source to destination airport.
This Algorithm works fine with directed weighted graph with positive and negative edge weights.
It calculates the shortest path in between all pairs of vertices(route) in graph.
Steps which we followed in this assignment with Floyd Warshall algorithm:-

1. Initialize 2D Matrix(graph) with the routes distance which is given in this case if there is no path in between two
   vertices then initialize with Infinity(In this case I used 1e7) and for same verties pair I used 0(like DUB-> DUB).
2. In 1st step initialize solution matrix same as graph matrix(which is created in above step) then pick all vertices
   one by one  and update all shortest paths which include the picked vertex as an intermediate vertex in the shortest
   path.
3. When we pick vertex number k as an intermediate vertex, we already have considered all vertices before k as intermediate
   vertices.
4. For every pair (i, j) of the source and destination vertices respectively, there are two possible cases.
    1) k is not an intermediate vertex in the shortest path from i to j. We will not change in this case.
    2) k is an intermediate vertex in the shortest path from i to j. We update the value of
       dist[i][j] as dist[i][k] + dist[k][j] if dist[i][j] > dist[i][k] + dist[k][j]
       The time complexity for this solution is O(V3).

Program Flow:
1. Our main entry class is src/main/java/core/sitecore/ShortestDistance.java. You have to execute this class to run the program.
   This is the entry class. main logic is in Graph.java
2. Unit tests are in src/test/java/core/sitecore/GraphTest.java

