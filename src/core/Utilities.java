package core;

public abstract class Utilities
{
    public static void printError(String error)
    {
        System.out.println(error);
        printHelp();
    }

    private static void printHelp()
    {
        System.out.println("\nUsage : ./checkViewSerializedSequence.py elementOfASequence1 elementOfASequence2 " +
                "elementOfASequence3 ...");
        System.out.println("An element of a sequence is composed of three informations : the type of the action " +
                "(r for read, w for write) ; the transaction number ; the resource used between parethesis");
        System.out.println("Exemple : ./checkViewSerializedSequence.py w0(x), r2(x), r1(x), w2(x), w2(z)\\n");

        System.exit(-1);
    }

    // From https://goo.gl/8zUFXA
    public static boolean isNumeric(String strNum)
    {
        try
        {
            double d = Double.parseDouble(strNum);
        }
        catch (NumberFormatException | NullPointerException nfe)
        {
            return false;
        }

        return true;
    }

    // From https://goo.gl/tvj1wx
    public static boolean isAlpha(String name)
    {
        return name.matches("[a-zA-Z]+");
    }
}
