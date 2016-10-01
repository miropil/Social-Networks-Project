package main.java;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by ilyami on 10/1/2016.
 */
public class SocialNetwork implements Graph {
    private HashMap<Integer, Node> nodes;
    private HashSet<Integer> borderNodes;
    private HashSet<Integer> switchedNodes;

    public SocialNetwork() {
        nodes = new HashMap<Integer, Node>();
    }


    @Override
    public void addVertex(int num) {
        if (num < 0) {
            throw new IllegalArgumentException(
                    "Node id should be a non negative integer. " + num + " is not a legal argument");
        }
        if (nodes.get(num) != null) {
            return;
        }
        nodes.put(num, new Node(num));

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
        addVertex(from);
        addVertex(to);
        nodes.get(from).getFriends().add(nodes.get(to));
    }

    @Override
    public HashMap<Integer, HashSet<Integer>> exportGraph() {
        return null;
    }

    public HashSet<Integer> cascade(int cascades, double q) {
        switchedNodes = new HashSet<>();
        switchedNodes.addAll(borderNodes);
        boolean exit = false;
        int cascadesPerformed = 0;

        while (!borderNodes.isEmpty() && !exit){
            exit = true;
            for (int node: borderNodes){
                if (visit(nodes.get(node), q)){
                    exit = false;
                }
            }
            if (cascades > 0){
                cascadesPerformed++;
                if (cascadesPerformed >= cascades){
                    exit = true;
                }
            }
        }
        return switchedNodes;
    }

    private boolean visit(Node node, double q){
        int visitedFriends = 0;
        for (Node n: node.getFriends()){
            double willSwitch = n.willSwitch();
            if(willSwitch >= q ){
                visitedFriends++;
                n.doSwitch();
                switchedNodes.add(n.getId());
                if (willSwitch < 1){
                    borderNodes.add(n.getId());
                }
            }
        }
        if (visitedFriends == node.getFriends().size()){
            borderNodes.remove(node.getId());
        }

        return visitedFriends != 0;
    }

    public void addStartingNode(int node){
        if (nodes.get(node) == null){
            throw new IllegalArgumentException("Node " + node + "not in the network and thus can not be a starter node");
        }
        if (borderNodes == null){
            borderNodes = new HashSet<Integer>();
        }
        borderNodes.add(node);
        nodes.get(node).doSwitch();
    }
}
