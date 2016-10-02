package main.java;

import java.util.*;

/**
 * This class represents social network data in form of adjacency list
 */
public class SocialNetwork implements Graph {
    //Adjacency list of the network
    private HashMap<Integer, Node> nodes;
    // Border nodes of the cascade
    private Border borderNodes = new Border();
    // Set of nodes switched to new feature
    private HashSet<Integer> switchedNodes;

    //Constructor
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

        //Adding nodes in both directions since data on facebook is non-directional
        nodes.get(from).getFriends().add(nodes.get(to));
        nodes.get(to).getFriends().add(nodes.get(from));
    }

    /**
     * This mthod will simulate a cascade throught the network and return a set of all nodes who switched
     *
     * @param q double between 0 and 1
     * @return
     */
    public HashSet<Integer> cascade(double q) {
        if (q >= 1 || q < 0) {
            throw new IllegalArgumentException("q should be less than one and grater than zero");
        }
        // initializing switched nodes with initiating nodes
        switchedNodes = new HashSet<>();
        switchedNodes.addAll(borderNodes.getQueue());
        while (!borderNodes.isEmpty()) {
            // here node is removed from border queue
            // will be re added in visit method only of it is still on the border after visit runs
            int node = borderNodes.poll();
            visit(nodes.get(node), q);
            // Checking if some nodes switched at the last cascade step
            if (!borderNodes.isActive()) {
                // If not cascade had stopped
                break;
            }
        }
        // zeroing borderNodes
        borderNodes = null;
        return switchedNodes;
    }

    /**
     * This method will iterate through all the node neighbors and switch those passing the threshold
     *
     * @param node border node to check all its neighbors
     * @param q    reward threshold
     */

    private void visit(Node node, double q) {
        // number of neighbors who switched overall
        int overallSwitchedFriends = 0;
        // number of neighbor who switch during this call of the method
        int switchedFriends = 0;
        for (Node n : node.getFriends()) {
            double switchedPortion = n.checkSwitchedPortion();
            if (switchedPortion >= q) {
                overallSwitchedFriends++;

                if (!n.hasSwitched()) {
                    n.doSwitch();
                    switchedFriends++;
                }

                switchedNodes.add(n.getId());
                if (switchedPortion < 1) {
                    // No need to check if node is already in the orderNodes, Border object will do this
                    borderNodes.addNode(n.getId(), true);
                }
            }
        }
        if (overallSwitchedFriends != node.getFriends().size()) {
            // re-adding node back to the border queue it is still on the border
            borderNodes.addNode(node.getId(), switchedFriends != 0);
        }

    }

    /**
     * Adding initiating node to the initiating nodes set
     * Should be ran at least once before cascade is called
     * Be sure that you added all initiating nodes for your simulation before running cascade
     * @param node
     */
    public void addStartingNode(int node) {
        if (nodes.get(node) == null) {
            throw new IllegalArgumentException("Node " + node + "not in the network and thus can not be a starter node");
        }
        if (borderNodes == null) {
            borderNodes = new Border();
        }
        borderNodes.addNode(node, true);
        nodes.get(node).doSwitch();
    }

}
