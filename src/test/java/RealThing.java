import java.util.HashSet;

/**
 * Created by ilyami on 10/2/2016.
 *
 */
public class RealThing extends Sanity{
    @Override
    public void setExpectedOutput() {
        expected = new HashSet<Integer>();
    }

    @Override
    public void setTestData() {
        super.setQ((double) 2/ (double) 5);
        super.setStartingNodes(new HashSet<Integer>(){{add(0); add(3);}});
        super.setTestFileName("src/main/resources/facebook_combined.txt");
    }

}
