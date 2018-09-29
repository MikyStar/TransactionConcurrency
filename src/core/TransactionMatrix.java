package core;

import com.sun.istack.internal.NotNull;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;

import java.util.ArrayList;

public class TransactionMatrix
{
    private ArrayList<OrderedTransaction> transactionMatrix = new ArrayList<>();

    public TransactionMatrix(@NotNull ArrayList<Element> listElement)
    {
        this.setTransactionMatrix(listElement);
    }

    private void setTransactionMatrix(ArrayList<Element> listOfElement)
    {
        for(Element element : listOfElement)
        {
            // Initialize
            if(transactionMatrix.size() == 0)
                transactionMatrix.add(new OrderedTransaction(element));
            else
            {
                boolean doesAnExistingOrderedTransactionSuits = false;

                // Adding an element to an existing ordered transaction
                for(OrderedTransaction orderedTransaction : this.transactionMatrix)
                {
                    if(orderedTransaction.getTransactionNumber() == element.getTransactionNumber())
                    {
                        orderedTransaction.addElement(element);
                        doesAnExistingOrderedTransactionSuits = true;
                    }
                }

                // Creating a new ordered transaction if needed
                if(!doesAnExistingOrderedTransactionSuits)
                    transactionMatrix.add(new OrderedTransaction(element));
            }
        }
    }

    public ArrayList<Element> getTransaction(int transactionNumber)
    {
        // TODO
        return new ArrayList<Element>();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private class OrderedTransaction
    {
        private ArrayList<Element> listOfElement = new ArrayList<>();

        private OrderedTransaction() {}

        private OrderedTransaction(ArrayList<Element> listOfElement)
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

        private OrderedTransaction(Element element)
        {
            listOfElement.add(element);
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

        public void addElement(Element element)
        {
            if(element.getTransactionNumber() == this.getTransactionNumber())
                listOfElement.add(element);
        }

        private int getTransactionNumber()
        {
            return listOfElement.get(0).getTransactionNumber();
        }
    }
}
