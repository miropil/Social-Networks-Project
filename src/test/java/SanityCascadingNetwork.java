
import main.java.CascadingNetwork;
import main.java.SocialNetwork;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.GraphLoader;

import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 */
public class SanityCascadingNetwork {
    CascadingNetwork testData = new CascadingNetwork();
    HashSet<Integer> expected;
    private double q = 0.5;
    private String fileName = "src/test/resources/one_edge.txt";
    private Set<Integer> startingNodes = new HashSet<Integer>(){{add(0);}};
    @Test
    public void runTest() throws Exception {
        System.out.println("Running test " + this.getClass().toString());
        Set<Integer> actual;
        if (startingNodes.isEmpty()){
            actual = testData.findMinimalIgnitingSet(q);
        }
        else{
            actual = testData.findMinimalIgnitingSet(startingNodes, q);
        }
        Assert.assertEquals(
                "Failed",
                expected,
                actual
        );
        System.out.println("Pass");
    }

    @Before
    public void setUp(){
        setTestData();
        loadTestData();
        setExpectedOutput();
    }


    public void setExpectedOutput() {
        expected = new HashSet<Integer>();
        expected.add(0);
    }

    public void loadTestData() {
        GraphLoader.loadGraph(testData, fileName);
    }

    public void setTestData(){
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
