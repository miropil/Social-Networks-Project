import java.util.HashSet;

/**
 * Created by ilyami on 10/2/2016.
 */
public class CascadeNetworkAllIgnite extends Sanity{
    @Override
    public void setExpectedOutput() {
        expected = new HashSet<Integer>();
        expected.add(0);
        expected.add(1);
    }

    @Override
    public void setTestData() {
        super.setQ(0.4);
        super.setTestFileName("src/test/resources/one_edge.txt");
    }
}
