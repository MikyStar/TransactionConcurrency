package core;

import com.sun.istack.internal.NotNull;
import java.util.ArrayList;

public class Sequence
{
    private ArrayList<Element> listElement;
    private TransactionMatrix transactionMatrix;
    private ArrayList<Integer> order;

    ///////////////////////////////////////////////

    public Sequence(@NotNull String string)
    {
        try
        {
            this.setListElement(string);
            this.checkConcurrencyProblems();
            this.setTransactionMatrix();
            this.setOrder();
        }
        catch (Exception e)
        {
            Utilities.printError(String.valueOf(e));
        }
    }

    public Sequence(@NotNull ArrayList<Element> listElement)
    {
        try
        {
            this.listElement = listElement;
            this.checkConcurrencyProblems();
            this.setTransactionMatrix();
            this.setOrder();
        }
        catch (Exception e)
        {
            Utilities.printError(String.valueOf(e));
        }
    }

    ////////////////////////////////////////////////

    public void setListElement(ArrayList<Element> listElement) {
        this.listElement = listElement;
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
        this.transactionMatrix = new TransactionMatrix(this.listElement);
    }

    private void setOrder()
    {
        /*
            Story

            This setOrder will return null if this Sequence is not ordered. Being ordered means that the order of
            every Elements follows the order of the transaction matrix.
            I'm going to pass through every element of the Sequence.
            I'm going to check that the transaction number of the first one is equal to the first number of the order of
            the transaction matrix.
            Then for each one, I'm going to check that its transaction number matches the actual number of the
            order on the transaction matrix.
            If not then it's means that I've passed to a new transaction so I've to check that the transaction number
            matches the following one in the order of the transaction matrix.
         */

        ArrayList<Integer> orderOfTransactionMatrix = this.transactionMatrix.getOrder();
        int indexOnTheOrderOfTransactionMatrix = 0;
        boolean everythingWentFine = true;

        for(Element element : this.listElement)
        {
            int transactionOfTheElement = element.getTransactionNumber();
            int currentTransactionInTheOrderOfTransactionMutrix = orderOfTransactionMatrix.get(indexOnTheOrderOfTransactionMatrix);

            if(transactionOfTheElement != currentTransactionInTheOrderOfTransactionMutrix)
            {
                if( (indexOnTheOrderOfTransactionMatrix + 1) <= (orderOfTransactionMatrix.size() -1)) //The -1 is because I access the first element on the list with the index 0 not 1
                {
                    int nextTransactionNumberInTheOrderOfTransactionMatrix = orderOfTransactionMatrix.get(indexOnTheOrderOfTransactionMatrix + 1);

                    if( (transactionOfTheElement == nextTransactionNumberInTheOrderOfTransactionMatrix) )
                        indexOnTheOrderOfTransactionMatrix += 1;
                    else
                        everythingWentFine = false;
                }
                else
                    everythingWentFine = false;
            }
        }

        this.order = everythingWentFine ? orderOfTransactionMatrix : null;
    }

    public ArrayList<Integer> getOrder()
    {
        return this.order;
    }

    public ArrayList<Element> getReadFroms()
    private ArrayList<Element> getAllActionsOnResource(@NotNull char resource)
    {
        ArrayList<Element> listElement = new ArrayList<>();

        for(Element element : this.listElement)
            if(element.getResourceUsed() == resource)
                listElement.add(element);

        return listElement;
    }

    private ArrayList<Character> getAllResourcesUsed()
    {
        ArrayList<Character> listResources = new ArrayList<>();

        for(Element element : this.listElement)
            listResources.add(element.getResourceUsed());

        return listResources;
    }

    public ArrayList<Element> getFinalWrites()
    {
       return new ArrayList<>();//TODO
    }

    private void checkConcurrencyProblems() throws Exception
    {
        if(isThereDirtyRead())
            throw new Exception("This sequence contains a dirty read problem");

        if(isThereLostUpdate())
            throw new Exception("This sequence contains a lost update problem");

        if(isThereUnreapeatableRead())
            throw new Exception("This sequence contains an unreapeatable read problem");

        if(isTherePhantomRead())
            throw new Exception("This sequence contains a phantom read problem");
    }

    private boolean isThereDirtyRead()
    {
        return false; //TODO
    }

    private boolean isThereLostUpdate()
    {
        return false; //TODO
    }

    private boolean isThereUnreapeatableRead()
    {
        return false; //TODO
    }

    private boolean isTherePhantomRead()
    {
        return false; //TODO
    }
}
