package be.helha.aemt.dao;

import be.helha.aemt.entities.Publication;
import be.helha.aemt.entities.User;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Stateless
@LocalBean
public class UserDAO extends DAO< User >
{

    @EJB
    PublicationDAO publicationDAO;

    public User findUserByLogin( User u )
    {
        if ( u == null || u.getLogin() == null )
        {
            return null;
        }
        String loginQuery = "SELECT u FROM User u WHERE u.login = :login";
        Query query = em.createQuery( loginQuery );
        query.setParameter( "login", u.getLogin() );
        User resultUser = null;
        try
        {
            resultUser = ( User ) query.getSingleResult();
        } catch ( NoResultException nre )
        {
            nre.printStackTrace();
        }
        return resultUser;
    }

    public User findUserByLogin( String login )
    {
        if ( login == null )
        {
            return null;
        }
        String loginQuery = "SELECT u FROM User u WHERE u.login = :login";
        Query query = em.createQuery( loginQuery );
        query.setParameter( "login", login );
        User resultUser = null;
        try
        {
            resultUser = ( User ) query.getSingleResult();
        } catch ( NoResultException nre )
        {
            nre.printStackTrace();
        }
        return resultUser;
    }
    
    
    public boolean subscribePublication( Integer idUser, Integer idPublication )
    {
        User user = read( idUser );
        Publication a = publicationDAO.read( idPublication );
        if( publicationDAO.read( idPublication ) instanceof Publication )
        {
        	
            Publication publication = new Publication();
            publication.setId( a.getId() );
            publication.setTitre( a.getTitre() );
            publication.setTexte( a.getTexte() );
            publication.setType( a.getType() );

            if( user.subscribe( publication ) )
            {
                em.merge( user );
                return true;
            }
        }
        return false;
    }
   
    
    public boolean unsubscribePublication( Integer idUser, Integer idPublication )
    {
        try
        {
            User user = read( idUser );
            Publication a = publicationDAO.read( idPublication );
            if( a instanceof Publication )
            {
                Publication c = ( Publication ) a;
                if( user.unsubscribe( c ) )
                {
                    em.merge( user );
                    return true;
                }
                return false;
            }
            return false;
        } catch ( Exception e )
        {
            e.printStackTrace();
            return false;
        }
    }    
    
    

    @Override
    public User create( User user )
    {
        if ( findUserByLogin( user ) != null )
        {
            return null;
        }
        if ( user == null )
        {
            return null;
        }
        if ( user.getLogin() == null )
        {
            return null;
        }
        try
        {
            String passwordBase64 = new String( Base64.getEncoder().encode(
                    MessageDigest.getInstance( "SHA-256" )
                            .digest( user.getPassword().getBytes(
                                    StandardCharsets.UTF_8 ) ) ) );
            user.setPassword( passwordBase64 );
            em.persist( user );
            return user;
        } catch ( Exception e )
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User read( Integer id )
    {
        if ( id == null )
        {
            return null;
        }
        User utilisateur = em.find( User.class, id );
        if ( utilisateur != null )
        {
            /* TODO: Log? Maybe unnecessary... */
        }
        return utilisateur;
    }

    @Override
    public User update( User object1, User object2 )
    {

        if ( object1 == null || object2 == null || object1.getId() == null )
        {
            return null;
        }

        //Gérer une exeption ici

        User findUser1 = em.find( User.class, object1.getId() );

        if ( findUser1 == null )
        {
            return null;
        }

        findUser1.setLogin( object2.getLogin() );

        String passwordBase64;
        try
        {
            passwordBase64 = new String( Base64.getEncoder().encode(
                    MessageDigest.getInstance( "SHA-256" )
                            .digest( object2.getPassword().getBytes(
                                    StandardCharsets.UTF_8 ) ) ) );
            findUser1.setPassword( passwordBase64 );

        } catch ( NoSuchAlgorithmException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //findUser1.setRole( object2.getRole() );

        em.merge( findUser1 );
        return findUser1;

    }

    @Override
    public boolean delete( User object )
    {
        if ( object == null || object.getLogin() == null )
        {
            return false;
        }

        object = em.find( User.class, object.getId() );

        em.remove( object );
        return true;
    }

    @Override
    public List< User > findAll()
    {
        String loginQuery = "SELECT u FROM User u";
        TypedQuery< User > query = em.createQuery( loginQuery, User.class );
        return query.getResultList();
    }

}
