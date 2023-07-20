package be.helha.aemt.dao;

import be.helha.aemt.entities.Admin;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;

@Stateless
@LocalBean
public class AdminDAO extends DAO< Admin >
{
	
	public AdminDAO()
	{
		
	}
	
    public Admin findAdminByLogin( Admin a )
    {
        if ( a == null || a.getLogin() == null )
        {
            return null;
        }
        String loginQuery = "SELECT a FROM Admin a WHERE a.login = :login";
        Query query = em.createQuery( loginQuery );
        query.setParameter( "login", a.getLogin() );
        Admin resultAdmin = null;
        try
        {
            resultAdmin = ( Admin ) query.getSingleResult();
        } catch ( NoResultException nre )
        {
            nre.printStackTrace();
        }
        return resultAdmin;
    }

    public Admin findAdminByLogin( String login)
    {
        if (login == null )
        {
            return null;
        }
        String loginQuery = "SELECT a FROM Admin a WHERE a.login = :login";
        Query query = em.createQuery( loginQuery );
        query.setParameter( "login", login );
        Admin resultAdmin = null;
        try
        {
            resultAdmin = ( Admin ) query.getSingleResult();
        } catch ( NoResultException nre )
        {
            nre.printStackTrace();
        }
        return resultAdmin;
    }
    @Override
    public Admin create( Admin admin )
    {
        if ( findAdminByLogin( admin ) != null )
        {
            return null;
        }
        if ( admin == null )
        {
            return null;
        }
        if ( admin.getLogin() == null )
        {
            return null;
        }
        try
        {
            String passwordBase64 = new String( Base64.getEncoder().encode(
                    MessageDigest.getInstance( "SHA-256" )
                            .digest( admin.getPassword().getBytes(
                                    StandardCharsets.UTF_8 ) ) ) );
            admin.setPassword( passwordBase64 );
            em.persist( admin );
            return admin;
        } catch ( Exception e )
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Admin read( Integer id )
    {
        if ( id == null )
        {
            return null;
        }
        Admin utilisateur = em.find( Admin.class, id );
        if ( utilisateur != null )
        {
            /* TODO: Log? Maybe unnecessary... */
        }
        return utilisateur;
    }

    @Override
    public Admin update( Admin object1, Admin object2 )
    {

        if ( object1 == null || object2 == null || object1.getId() == null )
        {
            return null;
        }

        //Gérer une exeption ici

        Admin findAdmin1 = em.find( Admin.class, object1.getId() );

        if ( findAdmin1 == null )
        {
            return null;
        }

        findAdmin1.setLogin( object2.getLogin() );
        findAdmin1.setPassword( object2.getPassword() );

        em.merge( findAdmin1 );
        return findAdmin1;

    }

    @Override
    public boolean delete( Admin object )
    {
        if ( object == null || object.getLogin() == null )
        {
            return false;
        }

        object = em.find( Admin.class, object.getId() );

        em.remove( object );
        return true;
    }

    @Override
    public List< Admin > findAll()
    {
        String loginQuery = "SELECT a FROM Admin a";
        TypedQuery< Admin > query = em.createQuery( loginQuery, Admin.class );
        return query.getResultList();
    }
}
