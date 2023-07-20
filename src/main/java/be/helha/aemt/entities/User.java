package be.helha.aemt.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    private String login;
    private String password;
    @ManyToMany( cascade = CascadeType.PERSIST, fetch = FetchType.EAGER )
    private List< Publication > publications;    
    
    
    public User()
    {
        publications = new ArrayList<Publication>();
    }

    public User( String login, String password )
    {
        publications = new ArrayList<Publication>();
        this.login = login;
        this.password = password;
    }

    public User( String login, String password, String role )
    {
        publications = new ArrayList<Publication>();
        this.login = login;
        this.password = password;
    }    
    
    public User(Integer id2, String login2, String password2, List<Publication> publications) {
        this.publications = new ArrayList< Publication >();
        for ( Publication pub : publications )
        {
            this.publications.add( pub.clone() );
        }

        this.id = id;
        this.login = login;
        this.password = password;
	}
 
    public boolean subscribe (Publication p)
    {
    	if( !publications.contains(p))
    	{
    		publications.add(p);
    		//return p.subscribing( this );
    		return true;
    	}
    	return false;
    }    
    
    public boolean unsubscribe( Publication i ) throws Exception
    {
        /*if ( activities.contains( i ) )
        {*/
        if ( !publications.remove( i ) )
        {
            throw new Exception();
        }
        return true;
        /*}*/
        //return false;
    }
    


    public User clone()
    {
        return new User( this.id, this.login, this.password, this.publications );
    }



    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        User other = ( User ) obj;
        return  Objects.equals( id, other.id )
                && Objects.equals( login, other.login ) && Objects.equals( password, other.password );
    }

    public Integer getId()
    {
        return id;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin( String login )
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "Utilisateur{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
