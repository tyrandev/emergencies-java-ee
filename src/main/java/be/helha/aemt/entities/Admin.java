package be.helha.aemt.entities;

import javax.persistence.Entity;

@Entity
public class Admin extends User
{
    public Admin()
    {
    }

    public Admin( String login, String password )
    {
        super( login, password );
    }
}
