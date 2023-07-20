package be.helha.aemt.ejb;

import be.helha.aemt.dao.PublicationDAO;
import be.helha.aemt.entities.Publication;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class PublicationEJB
{
    @EJB
    private PublicationDAO publicationDAO;

    public PublicationEJB()
    {
    }

    public List< Publication > getAllPublications()
    {
        return publicationDAO.findAll();
    }

    public Publication createPublication( String titre, String texte, String type)
    {
        return publicationDAO.create( new Publication( titre, texte, type) );
    }

    public Publication getSpecificPublication( int id )
    {
        return publicationDAO.read( id );
    }
    
    public List< Publication > getAllPublicationsByUserId( Integer id )
    {
        return publicationDAO.findAllPublicationsByUserId( id );
    }
    
}
