package be.helha.aemt.control;

import be.helha.aemt.ejb.UserEJB;
import be.helha.aemt.entities.Admin;
import be.helha.aemt.entities.Publication;
import be.helha.aemt.entities.User;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class UserControl implements Serializable
{
    private static final Logger log = Logger.getLogger( UserControl.class.getName() );
    private User user = null;
    @EJB
    private UserEJB userEJB;
    private Integer id;
    private Integer loggedInUserId;
    private String login;
    private String password;
    private String loggedInUserLogin;
    private String loggedInUserPassword;
    private boolean connected = false;
    private boolean adminConnected = false;


    public UserControl()
    {
    	createNewAdmin("testAdmin","testAdmin");
    }
    
    public String showRegisterPage()
    {
        return "register.xhtml?faces-redirect=true";
    }

    public List< User > showUserList()
    {
        return userEJB.getAllUser();
    }


    public boolean isConnected()
    {
        return connected;
    }

    public void setConnected( boolean connected )
    {
        this.connected = connected;
    }

    public boolean isAdminConnected()
    {
        return adminConnected;
    }

    public void setAdminConnected( boolean adminConnected )
    {
        this.adminConnected = adminConnected;
    }

    public User showUserById( Integer id )
    {
        if ( id == null )
        {
            return null;
        }
        return userEJB.findUserById( id );
    }

    public boolean isUserConnected()
    {
        return this.connected;
    }

    public boolean isUserAdmin()
    {
        Admin admin = userEJB.findAdminByLogin( this.user.getLogin() );
        return isUserConnected() && admin != null;

    }

    public boolean connection( String login )
    {
        if ( userEJB.findAdminByLogin( login ) != null )
        {
            this.adminConnected = true;
        }
        this.user = userEJB.findUserByLogin( login );
        return user != null;
    }

    
    // for login page
    public String createNewUser()
    {
        try
        {
            userEJB.createUser( login, password );

        } catch ( Exception e )
        {
            e.printStackTrace();
        }
        return "/login.xhtml?faces-redirect=true";
    }

    
    public String createNewAdmin()
    {
        try
        {
            userEJB.createAdmin( login, password );

        } catch ( Exception e )
        {
            e.printStackTrace();
        }
        return "/index.xhtml?faces-redirect=true";
    }
    
    //for admin page
    public String createNewUserControl()
    {
        try
        {
            userEJB.createUser( login, password );

        } catch ( Exception e )
        {
            e.printStackTrace();
        }
        return "/users_list.xhtml?faces-redirect=true";
    }

    //for admin page
    public String createNewAdminControl()
    {
        try
        {
            userEJB.createAdmin( login, password );

        } catch ( Exception e )
        {
            e.printStackTrace();
        }
        return "/users_list.xhtml?faces-redirect=true";
    }
    
    public String createNewAdmin(String username,String password)
    {
    	try
    	{
    		userEJB.createAdmin(username,password);

        } catch ( Exception e )
        {
            e.printStackTrace();
        }
        return "/index.xhtml?faces-redirect=true";
    }
    
    public String showProfilePage()
    {
        return "/user/profile.xhtml?faces-redirect=true";
    }

    public String userLogin()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = ( HttpServletRequest ) context
                .getExternalContext().getRequest();

        try
        {
            request.login( loggedInUserLogin, loggedInUserPassword );

        } catch ( ServletException e )
        {
            context.addMessage( null, new FacesMessage( FacesMessage.SEVERITY_WARN, "Login failed!", null ) );
            return "login";
        }

        //you can fetch user from database for authenticated principal and do some action  
        Principal principal = request.getUserPrincipal();
        log.info( "Authenticated user: " + principal.getName() );
        this.connected = true;
        this.adminConnected = false;
        if ( this.connection( loggedInUserLogin ) )
        {
            loggedInUserId = userEJB.findUserByLogin( loggedInUserLogin ).getId();
            return "/loginSucces.xhtml?faces-redirect=true";
        }
        return "/login_error.xhtml?faces-redirect=true";
    }

    public void logout()
    {
        //String result = "/logout_page.xhtml?faces-redirect=true";

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = ( HttpServletRequest ) context.getExternalContext().getRequest();

        try
        {
            request.logout();
        } catch ( ServletException e )
        {
            log.log( Level.SEVERE, "Failed to logout user!", e );
            //result = "/login_error.xhtml?faces-redirect=true";
        }

        user = null;
        this.connected = false;
        this.adminConnected = false;

        //return result;
    }


    public String deleteUser( Integer id )
    {
        if ( userEJB.deleteUser( id ) )
        {
            return "/users_list.xhtml"; //"/admin/users_list.xhtml"
        }
        return "/index.xhtml";
    }

    public String updateRedirect( String oldLogin )
    {
        User oldUser = userEJB.findUserByLogin( oldLogin );

        this.loggedInUserId = oldUser.getId();
        this.login = oldUser.getLogin();
        this.password = oldUser.getPassword();

        return "user_profile.xhtml?facets-redirect=true";
    }

    //subscribe publication
    public boolean subscribePublication( int idActivity )
    {
        return userEJB.subscribePublication( loggedInUserId, idActivity );
    }    

    public boolean unsubscribePublication(int idPublication)
    {
    	return userEJB.unsubscribePublication(loggedInUserId, idPublication);
    }
    

    public String updateUser(Integer id, boolean isAdmin)
    {
        User newUser = new User( login, password );

        userEJB.updateUser( id, newUser );

        return "user_profile.xhtml?facets-redirect=true";
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin( String login )
    {
        this.login = login;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId( Integer id )
    {
        this.id = id;
    }

    public Integer getLoggedInUserId()
    {
        return loggedInUserId;
    }

    public void setLoggedInUserId( Integer loggedInUserId )
    {
        this.loggedInUserId = loggedInUserId;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public String getLoggedInUserLogin()
    {
        return loggedInUserLogin;
    }

    public void setLoggedInUserLogin( String loggedInUserLogin )
    {
        this.loggedInUserLogin = loggedInUserLogin;
    }

    public String getLoggedInUserPassword()
    {
        return loggedInUserPassword;
    }

    public void setLoggedInUserPassword( String loggedInUserPassword )
    {
        this.loggedInUserPassword = loggedInUserPassword;
    }
    public User getUser()
    {
        return user;
    }

    public void setUser( User user )
    {
        this.user = user;
    }


}
