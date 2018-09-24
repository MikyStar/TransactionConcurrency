package core;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;

public class Sequence
{
    private ArrayList<Element> listElement;
    private TransactionMatrix transactionMatrix;

    public Sequence(@NotNull String string)
    {
        setListElement(string);
    }

    private void setListElement(@NotNull String string)
    {
        String[] words = string.split("\\s+");

        for ( String word : words )
        {
            this.listElement.add( new Element(word) );
        }
    }

    private void setTransactionMatrix()
    {

    }
}
