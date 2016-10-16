package main.java;

import util.GraphLoader;

import java.util.*;

/**
 * Created by ilyami on 10/15/2016.
 * Allows calculation of igniting nodes set which insures cascacde propagation through all the network
 */
public class CascadingNetwork extends SocialNetwork {
    // nodes chosen to initiate an algorithm (will be removed from igniting nodes set as soon as used once)
    private HashSet<Integer> algorithmInitializingNodes = new HashSet<Integer>();

    /**
     * Simulates cascade with intial conditions of ignitingNodes and threshold q
     *
     * @param ignitingNodes - Set<Integer>, nodes to start a cascade with
     * @param q             - double, cascade threshold
     * @return true if cascade was initiated
     */
    private Boolean cascadeStartingFrom(Set<Integer> ignitingNodes, double q) {
        setStartingNodes(ignitingNodes);
        cascade(q);
        return switchedNodes.size() != ignitingNodes.size();
    }

    /**
     * Picks two random nodes which are not friends and intiates an algorithm with those two nodes
     *
     * @param q - double, cascade threshold
     * @return Set<Integer>, igniting nodes set
     */
    public Set<Integer> findMinimalIgnitingSet(double q) {
        Random generator = new Random();
        int friends = 0;
        Node value = new Node(1);
        // pick random node with friends
        while (value.getFriends().size() == 0) {
            int n = generator.nextInt(nodes.size());
            int key = (int) nodes.keySet().toArray()[n];
            value = nodes.get(key);
        }
        Set<Integer> ignitingSet = new HashSet<Integer>();
        ignitingSet.add(value.getId());
        Node value1 = new Node(1);

        // pick another node which is not friend with the first
        while (value1.getFriends().size() == 0 && value1.getId() != value.getId()) {
            int n = generator.nextInt(nodes.size());
            int key = (int) nodes.keySet().toArray()[n];
            value1 = value.getFriends().contains(nodes.get(key)) ? value1 : nodes.get(key);
        }
        ignitingSet.add(value1.getId());
        return findMinimalIgnitingSet(ignitingSet, q);
    }

    /**
     * Calculates semi optimal igniting set for propagating a cascade through the given network
     *
     * @param ignitingSet - Set<Integer>, initiating set so far
     * @param q           - douoble, cascade threshold
     * @return Set<Integer>, set of nodes to initiate a cascade which will propagate through all the network
     */
    public Set<Integer> findMinimalIgnitingSet(Set<Integer> ignitingSet, double q) {
        // Check if current ignitingSet is enough
        cascadeStartingFrom(ignitingSet, q);                                                                            // O(n^2)
        if (switchedNodes.size() == nodes.keySet().size()) {
            return ignitingSet;
        }

        Border notSwitchedBorder = new Border();

        // Report on progress:
        System.out.println("Igniting Set: " + ignitingSet);
        System.out.println("Igniting Set Length: " + ignitingSet.size());
        System.out.println("Switched Nodes: " + switchedNodes);
        System.out.println("Switched Length: " + switchedNodes.size());
        System.out.println("Border Nodes: " + borderNodes.getSet());

        // remove randomly selected nodes for algorithm initialization
        for (int n : algorithmInitializingNodes) {                                                                         //O(1)
            ignitingSet.remove(n);
        }

        // Since nodes were removed we won't remove them again if they appear as a result of running the algorithm
        algorithmInitializingNodes = new HashSet<Integer>();

        // get all not switched nodes adjecent to the border
        for (Integer node : borderNodes.getSet()) {
            for (Node friend : nodes.get(node).getFriends()) {
                if (!switchedNodes.contains(friend.getId())) {
                    notSwitchedBorder.addNode(friend.getId(), true);                                                    //O(n)
                }
            }
        }

        Set<Integer> probingSet = new HashSet<Integer>();

        // Find semi optimal nodes set to propagate the cascade further and add to igniting set
        while (!cascadeStartingFrom(probingSet, q) && !notSwitchedBorder.isEmpty()) {                                      //O(n^3)
            int next = notSwitchedBorder.poll();
            probingSet.add(next);
            ignitingSet.add(next);
        }

        // Run again with new igniting set
        return findMinimalIgnitingSet(ignitingSet, q);
    }

    /**
     * Set given nodes set as starting nodes, zeroize border
     *
     * @param ignitingNodes - Set<Integer>
     */
    private void setStartingNodes(Set<Integer> ignitingNodes) {
        borderNodes = new Border();
        for (Integer node : ignitingNodes) {
            // use super explicitly to avoid adding node to algorithmInitializingNodes because this call to addStartingNode is part of main algorithm
            super.addStartingNode(node);
        }
    }

    @Override
    public void addStartingNode(int node) {
        super.addStartingNode(node);

        // add randomly selected initializing nodes to starting set to remove at the first iteration of the algorithm
        if (algorithmInitializingNodes == null) {
            algorithmInitializingNodes = new HashSet<Integer>();
        }
        algorithmInitializingNodes.add(node);

    }


    public static void main(String[] args) {
        double q = 0.2;
        CascadingNetwork testData = new CascadingNetwork();
        GraphLoader.loadGraph(testData, "src/main/resources/facebook_combined.txt");
        System.out.println(testData.findMinimalIgnitingSet(q));

    }


}
