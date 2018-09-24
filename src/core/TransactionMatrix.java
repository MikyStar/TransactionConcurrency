package core;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;

public class TransactionMatrix
{
    private ArrayList<ArrayList<Element>> matrix;

    public TransactionMatrix(@NotNull ArrayList<Element> listElement)
    {
        for (int i = 0; i < listElement.size(); i++)
        {
            int transactionNumber = listElement.get(i).getTransactionNumber();

            if( matrix[i][0] == null )
            {

            }
        }
    }
}
