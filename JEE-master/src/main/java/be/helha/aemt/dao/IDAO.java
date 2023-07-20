package be.helha.aemt.dao;

import java.util.List;

public interface IDAO< T >
{
    T create( T object );

    T read( Integer id );

    T update( T object1, T object2 );

    boolean delete( T object );

    List< T > findAll();
}
