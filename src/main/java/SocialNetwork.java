package main.java;

import java.util.*;

/**
 * Created by ilyami on 10/1/2016.
 *
 */
public class SocialNetwork implements Graph {
    private HashMap<Integer, Node> nodes;
    private Border borderNodes = new Border();
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

    public HashSet<Integer> cascade(double q) {
        switchedNodes = new HashSet<>();
        switchedNodes.addAll(borderNodes.getQueue());
        while (!borderNodes.isEmpty()){
            int node = borderNodes.poll();
            visit(nodes.get(node), q);
            if (!borderNodes.isActive()){
                break;
            }
        }
        borderNodes = null;
        return switchedNodes;
    }

    private boolean visit(Node node, double q){
        int overallSwitchedFriends = 0;
        int switchedFriends = 0;
        for (Node n: node.getFriends()){
            double switchedPortion = n.checkToSwitch();
            if(switchedPortion >= q){
                overallSwitchedFriends ++;
                if (!n.hasSwitched()){
                    n.doSwitch();
                    switchedFriends++;
                }
                switchedNodes.add(n.getId());
                if (switchedPortion < 1 && !borderNodes.getQueue().contains(n.getId())){
                    borderNodes.addNode(n.getId(), true);
                }
            }
        }
        if (overallSwitchedFriends != node.getFriends().size()){
            borderNodes.addNode(node.getId(), switchedFriends != 0);
        }

        return switchedFriends != 0;
    }

    public void addStartingNode(int node){
        if (nodes.get(node) == null){
            throw new IllegalArgumentException("Node " + node + "not in the network and thus can not be a starter node");
        }
        if (borderNodes == null){
            borderNodes = new Border();
            }
        borderNodes.addNode(node, true);
        nodes.get(node).doSwitch();
    }

    public Set<Integer> getNodes(){
        return nodes.keySet();
    }

    public Queue<Integer>getBorderNodes(){
        return borderNodes.getQueue();
    }
}
