package core;

import com.sun.istack.internal.NotNull;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;

import java.util.ArrayList;

public class TransactionMatrix
{
    private ArrayList<OrderedTransaction> transactionMatrix;

    public TransactionMatrix(@NotNull ArrayList<Element> listElement)
    {
        OrderedTransaction orderedTransaction = new OrderedTransaction(listElement);
    }

    public ArrayList<Element> getTransactionNumber(int transactionNumber)
    {
        // TODO
        return new ArrayList<Element>();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private class OrderedTransaction
    {
        private ArrayList<Element> listOfElement;

        private OrderedTransaction(@NotNull ArrayList<Element> listOfElement)
        {
            try
            {
                this.setListOfElement(listOfElement);
            }
            catch (Exception e)
            {
                Utilities.printError(String.valueOf(e));
            }
        }

        private void setListOfElement(@NotNull ArrayList<Element> listOfElement) throws Exception
        {
            int previousTransactionNumber = -1;

            for(Element element : listOfElement)
            {
                //Given that we retrieve characters from the arguments and that we have a strict policy, there can't be any minus
                if(previousTransactionNumber == -1)
                    previousTransactionNumber = element.getTransactionNumber();
                else if(element.getTransactionNumber() != previousTransactionNumber)
                    throw new Exception(element + " doesn't belong to this ordered sequence");
            }

            this.listOfElement = listOfElement;
        }
    }
}
