package main.java;

/**
 *
 *
 */
import java.util.HashMap;
import java.util.HashSet;

public interface Graph {
    /* Creates a vertex with the given number. */
    public void addVertex(int num);

    /* Creates an edge from the first vertex to the second. */
    public void addEdge(int from, int to);
}
