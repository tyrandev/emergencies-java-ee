package be.helha.aemt.entities;

public interface ISubscribable
{
    boolean subscribing( User user );

    boolean unsubscribing( User user );

    ISubscribable clone();
}
