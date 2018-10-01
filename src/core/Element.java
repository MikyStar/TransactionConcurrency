package core;

import com.sun.istack.internal.NotNull;

public class Element
{
    private ActionType action;
    private int transactionNumber;
    private char resourceUsed;

    public Element(String string)
    {
        try
        {
            if( !string.isEmpty() )
            {
                setAction(string);
                setTransactionNumber(string);
                setResourceUsed(string);
            }
            else
                throw new Exception("string is empty");
        }
        catch (Exception exception)
        {
            Utilities.printError(String.valueOf(exception));
        }
    }

    ////////////////////////////////////////////////////////

    private void setAction(@NotNull String string) throws Exception
    {
        char action;

        action = string.charAt(0);

        if( action == 'r' )
            this.action = ActionType.READ ;
        else if (action == 'w')
            this.action = ActionType.WRITE ;
        else
            throw new Exception( string + " should contains an action, read (r) or write (w)");
    }

    private void setTransactionNumber(@NotNull String string) throws Exception
    {
        if( isThereTheRightAmountOfBraces(string) )
        {
            String[] partsOfString = string.split("\\(");
            String transactionNumber = partsOfString[0].substring(1);

            if( Utilities.isNumeric(transactionNumber) )
            {
                this.transactionNumber = Integer.parseInt(transactionNumber);
            }
            else
                throw new Exception(string + " should have a transaction number");
        }
        else
            throw new Exception(string + " should only contains one opening and one closing parenthesis");
    }

    private void setResourceUsed(@NotNull String string) throws Exception
    {
        if( isThereTheRightAmountOfBraces(string) )
        {
            String resource = string.substring(string.indexOf("(")+1, string.indexOf(")"));

            if( (resource.length() == 1) && (Utilities.isAlpha(resource)) )
                this.resourceUsed = resource.charAt(0);
            else
                throw new Exception( string + " should contains only one resource and it should be a letter");
        }
        else
            throw new Exception(string + " should only contains one opening and one closing parenthesis");
    }

    private boolean isThereTheRightAmountOfBraces(@NotNull String string)
    {
        int numberOfOpenBraces = string.length() - string.replace("(", "").length(); // See https://goo.gl/bVjfdT
        int numberOfClosingBraces = string.length() - string.replace(")", "").length(); // See https://goo.gl/bVjfdT

        return (numberOfOpenBraces == 1) && (numberOfClosingBraces == 1);
    }

    ////////////////////////////////////////////////////////

    public ActionType getAction()
    {
        return action;
    }

    public int getTransactionNumber()
    {
        return transactionNumber;
    }

    public char getResourceUsed()
    {
        return resourceUsed;
    }

    @Override
    public String toString()
    {
        String stringAction = this.action == ActionType.READ ? "r" : "w";

        return stringAction + this.getTransactionNumber() + "(" + this.getResourceUsed() + ")";
    }
}
