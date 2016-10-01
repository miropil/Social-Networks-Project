package main.java;

import java.util.*;

/**
 * Created by ilyami on 10/1/2016.
 */
public class SocialNetwork implements Graph {
    private HashMap<Integer, Node> nodes;
    private Queue<Integer> borderNodes = new LinkedList<Integer>();
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
        int cascadeLength = borderNodes.size();
        int count = 0;

        while (!borderNodes.isEmpty() && !exit){
            exit = true;
            int node = borderNodes.poll();

            count++;
            if (visit(nodes.get(node), q)){
                  exit = false;
            }

            if (cascades > 0 && count >= cascadeLength){
                cascadesPerformed++;
                cascadeLength = borderNodes.size();
                count = 0;
                if (cascadesPerformed >= cascades){
                    exit = true;
                }
            }
        }
        System.out.println("Number of cascades performed: " + cascadesPerformed);
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
                if (willSwitch < 1 && !borderNodes.contains(n.getId())){
                    borderNodes.add(n.getId());
                }
            }
        }
        if (visitedFriends != node.getFriends().size() && !borderNodes.contains(node.getId())){
            borderNodes.add(node.getId());
        }

        return visitedFriends != 0;
    }

    public void addStartingNode(int node){
        if (nodes.get(node) == null){
            throw new IllegalArgumentException("Node " + node + "not in the network and thus can not be a starter node");
        }
        if (borderNodes == null){
            borderNodes = new LinkedList<Integer>();
            }
        borderNodes.add(node);
        nodes.get(node).doSwitch();
    }

    public Queue<Integer>getBorderNodes(){
        return borderNodes;
    }
}
