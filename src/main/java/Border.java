package main.java;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * This object will store border nodes queue and provide information about border nodes being active
 * Border nodes queue will be also a set meaning that if node is already in the queue it won't be added again
 *
 */
public class Border {
    private Queue<Integer> queue;
    private Map<Integer, Boolean> activityMap;

    public Border() {
        queue = new LinkedList<Integer>();
        activityMap = new HashMap<Integer, Boolean>();
    }

    /**
     * Adds node to the queue and updates its value in activity map
     * Node will be added only if it is not in the queue yet
     * @param n int node id
     * @param nodeActive boolean indicates if nodes was active during last cascade step (meaning some of it's neighbors had switched during this step)
     */

    public void addNode(int n, boolean nodeActive){

        if (activityMap.get(n) == null){
            queue.add(n);
            activityMap.put(n, nodeActive);
        }
    }

    /**
     * Performs same operation as Queue interface method
     * Removes element from activityMap as well
     * @return Integer
     */
    public Integer poll(){
        Integer el = queue.poll();
        if (el == null){
            return null;
        }
        activityMap.remove(el);
        return el;
    }

    /**
     * Returns object's queue
     * @return Queue
     */

    public Queue<Integer> getQueue(){
        return queue;
    }

    /**
     * Similr to Queue interface method
     * @return Boolean
     */

    public Boolean isEmpty(){
        return queue.isEmpty();
    }

    /*
    Checks if any of the queue elements is active
     */
    public Boolean isActive(){
        return activityMap.containsValue(true);
    }


}
