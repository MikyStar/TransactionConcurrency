package core;

public  interface ConcurrencyProblems
{
    boolean isThereDirtyRead();
    boolean isThereLostUpdate();
    boolean isThereUnreapeatableRead();
    boolean isTherePhantomRead();
}
