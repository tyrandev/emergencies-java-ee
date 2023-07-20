package be.helha.aemt.entities;

import javax.persistence.Entity;

@Entity
public class Organisateur extends Admin
{
    public Organisateur()
    {
    }

    public Organisateur( String login, String password )
    {
        super( login, password );
    }
}
