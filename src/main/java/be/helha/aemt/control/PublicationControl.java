package be.helha.aemt.control;

import be.helha.aemt.ejb.PublicationEJB;
import be.helha.aemt.entities.Publication;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class PublicationControl implements Serializable
{
    @EJB
    private PublicationEJB publicationEJB;
    private Publication publication = new Publication();

    
    public PublicationEJB getPublicationEJB() {
		return publicationEJB;
	}

	public void setPublicationEJB(PublicationEJB publicationEJB) {
		this.publicationEJB = publicationEJB;
	}

	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}

	public List< Publication > showPublicationByUserId( Integer id )
    {
        return publicationEJB.getAllPublicationsByUserId( id );
    }   
    
    public String showListPublicationsPage()
    {
        return "listePublications.xhtml?faces-redirect=true";
    }

    public String showAddPage()
    {
        return "ajoutPublication.xhtml?faces-redirect=true";
    }

    public List< Publication > showPublicationList()
    {
        return publicationEJB.getAllPublications();
    }

    @PostConstruct
    public void createNewPublication()
    {
        try
        {
            publicationEJB.createPublication( publication.getTitre(), publication.getTexte(), publication.getType() );
        } catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    public Publication getSpecificPublication( int id )
    {
        return publicationEJB.getSpecificPublication( id );
    }

}
