import java.util.HashSet;

/**
 * Created by ilyami on 10/1/2016.
 *
 */
public class AllSwitch extends Sanity {

    @Override
    public void setExpectedOutput() {
        expected = new HashSet<Integer>();
        expected.add(0);
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
    }

    @Override
    public void setTestData() {
        super.setCascadesNumber(1);
        super.setQ((double) 2/ (double) 5);
        super.setStartingNodes(new HashSet<Integer>(){{add(0); add(3);}});
        super.setTestFileName("src/test/resources/several_edges.txt");
    }
}
