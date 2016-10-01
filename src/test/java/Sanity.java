
import main.java.SocialNetwork;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.GraphLoader;

import java.util.HashSet;
import java.util.StringJoiner;

/**
 * Created by ilyami on 10/1/2016.
 *
 */
public class Sanity {
    SocialNetwork testData = new SocialNetwork();
    HashSet<Integer> expected;
    private int cascadesNum = 0;
    private double q = 0.5;
    private String fileName = "src/test/resources/one_edge.txt";
    private HashSet<Integer> startingNodes;
    @Test
    public void runTest() throws Exception {
        System.out.println("Running test " + this.getClass().toString());
        if (startingNodes == null || startingNodes.isEmpty()){
            testData.addStartingNode(0);
        }
        HashSet<Integer> actual = testData.cascade(cascadesNum, q);
        Assert.assertEquals(
                "Failed",
                expected,
                actual
        );
        System.out.println("Pass");
    }

    @Before
    public void setUp(){
        this.setTestData();
        this.loadTestData();
        this.setExpectedOutput();
        for (int node: startingNodes){
            testData.addStartingNode(node);
        }

    }


    public void setExpectedOutput() {
        expected = new HashSet<Integer>();
        expected.add(0);
        expected.add(1);
    }

    public void loadTestData() {
        GraphLoader.loadGraph(testData, fileName);
    }

    public void setTestData(){
    }

    public void setCascadesNumber(int i){
        cascadesNum = i;
    }

    public void setQ(double que){
        q = que;
    }

    public void setStartingNodes(HashSet<Integer> nodes){
        startingNodes = nodes;
    }

    public void setTestFileName(String filePath){
        fileName = filePath;
    }
}
