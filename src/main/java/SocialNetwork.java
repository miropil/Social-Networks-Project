package main.java;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.*;

/**
 * Created by ilyami on 10/1/2016.
 */
public class SocialNetwork implements Graph {
    private HashMap<Integer, Node> nodes;
    private Queue<Integer> borderNodes = new LinkedList<Integer>();
    private HashSet<Integer> switchedNodes;
    private HashMap<Integer, Boolean> borderStatus;
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
        switchedNodes.addAll(borderNodes);
        borderStatus = new HashMap<Integer, Boolean>();
        for (int n: switchedNodes){
            borderStatus.put(n, true);
        }
        while (!borderNodes.isEmpty()){
            int node = borderNodes.poll();
            borderStatus.remove(node);
            visit(nodes.get(node), q);
            System.out.println(borderStatus);
            System.out.println(borderNodes);
            if (!borderStatus.containsValue(true)){
                break;
            }
        }
        return switchedNodes;
    }

    private boolean visit(Node node, double q){
        int overallSwitchedFriends = 0;
        int switchedFriends = 0;
        for (Node n: node.getFriends()){
            double willSwitch = n.willSwitch();
            if(willSwitch >= q){
                overallSwitchedFriends ++;
                if (!n.hasSwitched()){
                    n.doSwitch();
                    switchedFriends++;
                }
                switchedNodes.add(n.getId());
                if (willSwitch < 1 && !borderNodes.contains(n.getId())){
                    borderNodes.add(n.getId());
                    borderStatus.put(n.getId(), true);
                }
            }
        }
        if (overallSwitchedFriends != node.getFriends().size()){
            borderNodes.add(node.getId());
            borderStatus.put(node.getId(), switchedFriends != 0);
        }

        return switchedFriends != 0;
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

    public Set<Integer> getNodes(){
        return nodes.keySet();
    }

    public Queue<Integer>getBorderNodes(){
        return borderNodes;
    }
}
