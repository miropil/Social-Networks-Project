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
    }

    public int getId(){
        return id;
    }

    public HashSet<Node> getFriends(){
        return friends;
    }

    public boolean willSwitch(double q){
        // addressing corner case of single node selected as a starter node
        if (friends.size() == 0){
            return true;
        }
        int switchedCount = 0;
        for (Node n: friends){
            if (n.hasSwitched()){
                switchedCount++;
            }
        }
        return switchedCount/friends.size() > q;
    }

    public boolean hasSwitched(){
        return switched;
    }

    public void doSwitch(){
        switched = true;
    }
}
