package main.java;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Created by ilyami on 10/2/2016.
 *
 */
public class Border {
    private Queue<Integer> queue;
    private Map<Integer, Boolean> activityMap;

    public Border() {
        queue = new LinkedList<Integer>();
        activityMap = new HashMap<Integer, Boolean>();
    }

    public void addNode(int n, boolean nodeActive){
        queue.add(n);
        activityMap.put(n, nodeActive);
    }

    public int element(){
        return queue.element();
    }

    public Integer poll(){
        Integer el = queue.poll();
        if (el == null){
            return null;
        }
        activityMap.remove(el);
        return el;
    }

    public Queue<Integer> getQueue(){
        return queue;
    }

    public Boolean isEmpty(){
        return queue.isEmpty();
    }

    public Boolean isActive(){
        return activityMap.containsValue(true);
    }


}
