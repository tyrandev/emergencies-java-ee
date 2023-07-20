package be.helha.aemt.dao;

import be.helha.aemt.entities.Organisateur;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class OrganisateurDAO extends DAO< Organisateur >
{
    public OrganisateurDAO()
    {
        super();
    }

    @Override
    public Organisateur create( Organisateur object )
    {
        return super.create( object );
    }

    @Override
    public Organisateur read( Integer id )
    {
        return super.read( id );
    }

    @Override
    public Organisateur update( Organisateur object1, Organisateur object2 )
    {
        return super.update( object1, object2 );
    }

    @Override
    public boolean delete( Organisateur object )
    {
        return super.delete( object );
    }

    @Override
    public List< Organisateur > findAll()
    {
        return super.findAll();
    }
}
