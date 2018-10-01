import core.Element;
import core.Sequence;

import java.util.ArrayList;

public class Main
{
    public static void main( String[] args )
    {
        ArrayList<Element> testList = new ArrayList<>();
        testList.add(new Element("w1(u)"));
        testList.add(new Element("w1u)"));
        testList.add(new Element("w2(u)"));

        Sequence test = new Sequence(testList);

        System.out.println(test.getOrder());
    }
}
