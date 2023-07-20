package be.helha.aemt.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Publication
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    private String titre;
    private String texte;
    private String type;
    //private String datePublication;

    public Publication()
    {
    	//this.datePublication = generateDate();
    }
    
	public Publication( String titre, String texte, String type )
    {
        this.titre = titre;
        this.texte = texte;
        this.type = type;
        //this.datePublication = generateDate();
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String generateDate()
	{
    	SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    	Date date = new Date(System.currentTimeMillis());
    	System.out.println(formatter.format(date));
		return formatter.format(date).toString();
	}

    public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	@Override
    public Publication clone()
    {
    	Publication p = new Publication(getTitre(),getTexte(),getType());
    	return p;
    }
    
    public void setId(Integer id) {
		this.id = id;
	}

    /*
    @Override
    public boolean subscribing(User user)
    {
    	return
    }
    */
    
    public Integer getId()
    {
        return id;
    }

    public String getTitre()
    {
        return titre;
    }

    public void setTitre( String titre )
    {
        this.titre = titre;
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Publication other = (Publication) obj;
		/*
		if (datePublication == null) {
			if (other.datePublication != null)
				return false;
		} else if (!datePublication.equals(other.datePublication))
			return false;
			*/
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (texte == null) {
			if (other.texte != null)
				return false;
		} else if (!texte.equals(other.texte))
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	
	
	
	
	

}
