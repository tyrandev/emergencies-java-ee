package be.helha.aemt.ejb;

import be.helha.aemt.dao.AdminDAO;
import be.helha.aemt.dao.UserDAO;
import be.helha.aemt.entities.Admin;
import be.helha.aemt.entities.User;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class UserEJB
{
    @EJB
    private UserDAO userDAO;
    @EJB
    private AdminDAO adminDAO;

    public UserEJB()
    {
    }

    public List< User > getAllUser()
    {
        return userDAO.findAll();
    }

    public User createUser( String login, String password )
    {
        return userDAO.create( new User( login, password ) );
    }

    public Admin createAdmin( String login, String password )
    {
        return adminDAO.create( new Admin( login, password ) );
    }

    public User findUserByLogin( String login )
    {
        return userDAO.findUserByLogin( login );
    }

    public User findUserById( Integer id )
    {
        return userDAO.read( id );
    }


    
    public boolean subscribePublication( Integer idUser, Integer idPublication)
    {
    	return userDAO.subscribePublication(idUser, idPublication);
    }

    /*
    public boolean unsubscribe( Integer idUser, Integer idActivity )
    {
        return userDAO.unsubscribeActivity( idUser, idActivity );
    }

    public List< ActivitySubscribable > findAllActivitiesFromUserId( Integer idUser )
    {
        return userDAO.findAllActivitiesByUserId( idUser );
    }    

    public boolean subscribe( Integer idUser, Integer idActivity )
    {
        return userDAO.subscribeActivity( idUser, idActivity );
    }
    */
    
    //unsub pub
    public boolean unsubscribePublication( Integer idUser, Integer idPublication)
    {
    	return userDAO.unsubscribePublication( idUser, idPublication );
    }

    public Admin findAdminByLogin( String login )
    {
        return adminDAO.findAdminByLogin( login );
    }

    public boolean deleteUser( Integer id )
    {
        return userDAO.delete( userDAO.read( id ) );
    }

    public User updateUser( Integer id, User newUser )
    {
        User oldUser = userDAO.read( id );

        return userDAO.update( oldUser, newUser );
    }


}
