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
        super.setStartingNodes(new HashSet<Integer>(){{add(0);add(2);add(300);add(299);add(346);add(10);add(20);add(3000);add(3001);add(3013);}});
        super.setTestFileName("src/main/resources/facebook_combined.txt");
    }

}
