import main.java.SocialNetwork;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.GraphLoader;

import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;

/**
 * Created by ilyami on 10/1/2016.
 *
 */
public class Sanity {
    private SocialNetwork testData;
    private HashMap<Integer, HashSet<Integer>> expected;
    private int cascadesNum = 0;
    @Test
    public void runTest() throws Exception {
        System.out.println("Running test " + this.getClass().toString());
        testData.setCascadesNumber(cascadesNum);
        HashMap<Integer, HashSet<Integer>> actual = testData.cascade().exportGraph();
        Assert.assertEquals(
                "Comparing expected\n" +
                        expected.toString() +
                        "with actual\n" +
                        actual.toString(),
                expected,
                actual
        );
    }

    @Before
    public void setUp(){
        loadTestData();
        setExpectedOutput();
    }

    private void setExpectedOutput() {
        expected.put(0, new HashSet<Integer>());
        expected.get(0).add(1);
        expected.put(1, new HashSet<Integer>());
        expected.get(1).add(0);
    }

    private void loadTestData() {
        GraphLoader.loadGraph(testData, "src/test/resources/one_edge.txt");
    }

}
