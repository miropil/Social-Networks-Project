package main.java;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by ilyami on 10/1/2016.
 *
 */
public class SocialNetwork implements Graph{
    private int cascadesNumber;
    @Override
    public void addVertex(int num) {

    }

    @Override
    public void addEdge(int from, int to) {

    }

    @Override
    public HashMap<Integer, HashSet<Integer>> exportGraph() {
        return null;
    }

    public void setCascadesNumber(int cascades) {
        cascadesNumber = cascades;
    }

    public SocialNetwork cascade() {
        return null;
    }
}
