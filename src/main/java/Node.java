package main.java;

import java.util.HashSet;

/**
 * This object implements network node, node neighbors are stored as Node objects
 */
public class Node {
    private int id;
    private HashSet<Node> friends;
    //attribute indicating whether node has switched or not
    private boolean switched = false;

    public Node(int id) {
        this.id = id;
        friends = new HashSet<Node>();
    }

    public int getId() {
        return id;
    }

    public HashSet<Node> getFriends() {
        return friends;
    }

    /**
     * Calculates a portion of the node neighbors who has switched out of all node neighbors
     *
     * @return double (switched neighbors number/total neighbors number)
     */
    public double checkSwitchedPortion() {
        // addressing corner case of single node selected as a starter node
        if (friends.size() == 0) {
            return 1;
        }
        int switchedCount = 0;
        for (Node n : friends) {
            if (n.hasSwitched()) {
                switchedCount++;
            }
        }
        return (double) switchedCount / (double) friends.size();
    }

    public boolean hasSwitched() {
        return switched;
    }

    public void doSwitch() {
        switched = true;
    }

    public void turnOff() { switched = false; }
}
