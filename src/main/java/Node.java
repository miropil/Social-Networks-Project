package main.java;

import java.util.HashSet;

/**
 * Created by ilyami on 10/1/2016.
 *
 */
public class Node {
    private int id;
    private HashSet<Node> friends;
    private boolean switched = false;

    public Node(int id) {
        this.id = id;
        friends = new HashSet<Node>();
    }

    public int getId(){
        return id;
    }

    public HashSet<Node> getFriends(){
        return friends;
    }

    public double checkToSwitch(){
        // addressing corner case of single node selected as a starter node
        if (friends.size() == 0){
            return 1;
        }
        int switchedCount = 0;
        for (Node n: friends){
            if (n.hasSwitched()){
                switchedCount++;
            }
        }
        return (double) switchedCount/ (double)friends.size();
    }

    public boolean hasSwitched(){
        return switched;
    }

    public void doSwitch(){
        switched = true;
    }
}
