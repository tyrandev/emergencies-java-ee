package be.helha.aemt.dao;

import be.helha.aemt.entities.Publication;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@LocalBean
public class PublicationDAO extends DAO< Publication >
{
    public Publication findPublicationByTitle( Publication p )
    {
        if ( p == null || p.getTitre() == null )
        {
            return null;
        }
        String loginQuery = "SELECT p FROM Publication p WHERE p.titre = :titre";
        Query query = em.createQuery( loginQuery );
        query.setParameter( "titre", p.getTitre() );
        Publication resultPublication = null;
        try
        {
            resultPublication = ( Publication ) query.getSingleResult();
        } catch ( NoResultException nre )
        {
            nre.printStackTrace();
        }
        return resultPublication;
    }

    @Override
    public Publication create( Publication publication )
    {
        if ( findPublicationByTitle( publication ) != null )
        {
            return null;
        }
        if ( publication == null )
        {
            return null;
        }
        if ( publication.getTitre() == null )
        {
            return null;
        }
        em.persist( publication );
        return publication;
    }

    @Override
    public Publication read( Integer id )
    {
        if ( id == null )
        {
            return null;
        }
        Publication publication = em.find( Publication.class, id );
        if ( publication != null )
        {
        }
        return publication;
    }

    @Override
    public Publication update( Publication object1, Publication object2 )
    {
        return super.update( object1, object2 );
    }

    @Override
    public boolean delete( Publication object )
    {
        return delete( object );
    }
    
    @Override
    public List< Publication > findAll()
    {
        String loginQuery = "SELECT p FROM Publication p";
        TypedQuery< Publication > query = em.createQuery( loginQuery, Publication.class );
        return query.getResultList();
    }

    public List< Publication > findAllPublicationsByUserId ( Integer idUser )
    {
        String queryString = "SELECT u.publications FROM User u WHERE u.id = :id";
        TypedQuery< Publication > query = em.createQuery( queryString, Publication.class );
        query.setParameter( "id", idUser );
        return query.getResultList();
    }
    
}
