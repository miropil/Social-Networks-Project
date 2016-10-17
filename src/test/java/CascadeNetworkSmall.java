import java.util.HashSet;

/**
 * Created by ilyami on 10/2/2016.
 */
public class CascadeNetworkSmall extends Sanity{
    @Override
    public void setExpectedOutput() {
        expected = new HashSet<Integer>();
        expected.add(4);
        expected.add(5);
        expected.add(6);
        expected.add(7);
        expected.add(8);
        expected.add(9);
        expected.add(10);
    }

    @Override
    public void setTestData() {
        super.setQ(0.4);
        super.setStartingNodes(new HashSet<Integer>(){{add(7); add(8);}});
        super.setTestFileName("src/test/resources/seventeen_nodes.txt");
    }
}
