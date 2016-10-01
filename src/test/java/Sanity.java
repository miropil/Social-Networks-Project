import main.java.SocialNetwork;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.GraphLoader;

import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;

/**
 * Created by ilyami on 10/1/2016.
 *
 */
public class Sanity {
    private SocialNetwork testData;
    private HashSet<Integer> expected;
    private int cascadesNum = 0;
    private double q = 0.5;
    @Test
    public void runTest() throws Exception {
        System.out.println("Running test " + this.getClass().toString());
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
        testData = new SocialNetwork();
        loadTestData();
        testData.addStartingNode(0);
        setExpectedOutput();
    }

    private void setExpectedOutput() {
        expected = new HashSet<Integer>();
        expected.add(0);
        expected.add(1);
    }

    private void loadTestData() {
        GraphLoader.loadGraph(testData, "src/test/resources/one_edge.txt");
    }

}
