package main.java;

import util.GraphLoader;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by ilyami on 10/15/2016.
 *
 */
public class CascadingNetwork extends SocialNetwork{
    private Boolean cascadeStartingFrom(Set <Integer> ignitingNodes, double q){
        setStartingNodes(ignitingNodes);
        cascade(q);
        return switchedNodes.size() != ignitingNodes.size();
    }

    public Set<Integer> findMinimalIgnitingSet(Set<Integer> ignitingSet, double q){
        cascadeStartingFrom(ignitingSet, q);
        if (switchedNodes.size() == nodes.keySet().size()){
            return ignitingSet;
        }
        Border ignitingNodes = new Border();
        System.out.println(borderNodes.getSet());
        Set<Integer> border = borderNodes.getSet();
        for (Object node: border) {
            for (Node friend: nodes.get(node).getFriends()){
                ignitingNodes.addNode(friend.getId(), true);
            }
        }
        Set <Integer> probingSet = new HashSet<Integer>();
        while (!cascadeStartingFrom(probingSet, q)){
            int next =ignitingNodes.poll();
            probingSet.add(next);
            ignitingSet.add(next);
        }
        return findMinimalIgnitingSet(ignitingSet, q);
    }

    private void setStartingNodes(Set<Integer> ignitingNodes) {
        borderNodes = new Border();
        for(Integer node: ignitingNodes){
            addStartingNode(node);
        }
    }

    public Set<Integer> getBorder(){ return borderNodes.getSet(); }

    public static void main(String[] args){
        double q = (double) 2/ (double) 5;
        CascadingNetwork testData = new CascadingNetwork();
        GraphLoader.loadGraph(testData, "src/test/resources/seventeen_nodes.txt");
        testData.addStartingNode(1);
        testData.addStartingNode(2);
        testData.addStartingNode(3);
        System.out.println(testData.findMinimalIgnitingSet(testData.getBorder(), q));

    }


}
