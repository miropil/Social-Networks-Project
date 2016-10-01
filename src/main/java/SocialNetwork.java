package main.java;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by ilyami on 10/1/2016.
 */
public class SocialNetwork implements Graph {
    private HashMap<Integer, Node> nodes;

    @Override
    public void addVertex(int num) {
        if (num < 0) {
            throw new IllegalArgumentException(
                    "Node id should be a non negative integer. " + num + " is not a legal argument");
        }
        if (nodes.get(num) != null) {
            nodes.put(num, new Node(num));
        }
    }

    @Override
    public void addEdge(int from, int to) {
        if (from < 0) {
            throw new IllegalArgumentException(
                    "Node id should be a non negative integer. " + from + " is not a legal argument");
        }
        if (to < 0) {
            throw new IllegalArgumentException(
                    "Node id should be a non negative integer. " + to + " is not a legal argument");
        }
        if (nodes.get(from) == null) {
            throw new IllegalArgumentException("Node " + from + "is not a part of the network");
        }
        if (nodes.get(to) == null) {
            throw new IllegalArgumentException("Node " + to + "is not a part of the network");
        }
        nodes.get(from).getFriends().add(nodes.get(to));
    }

    @Override
    public HashMap<Integer, HashSet<Integer>> exportGraph() {
        return null;
    }

    public HashSet<Integer> cascade(int cascades) {
        return null;
    }
}
