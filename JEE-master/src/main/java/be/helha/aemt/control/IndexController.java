package be.helha.aemt.control;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.Lob;

@Named
@SessionScoped
public class IndexController implements Serializable {
	
	//starting page
    private String srcPage="accueil.xhtml";

    
    public void showListPublicationsPage()
    {
    	this.srcPage="publication/listePublications.xhtml";
    }
    
    public String getSrcPage() {
		return srcPage;
	}

	public void setSrcPage(String srcPage) {
		this.srcPage = srcPage;
	}

	public void doGoEquipe() {
    	this.srcPage="equipe.xhtml";
    }
    
    public void contantUs() {
    	this.srcPage="contact_us.xhtml";
    }

    public void doGoLogOut() {
    	this.srcPage="logout_page.xhtml";
    }

    public void doGoAcceuil() {
        this.srcPage="accueil.xhtml";
    }

    public void doGoAdminHome() {
        this.srcPage="admin_home.xhtml"; // removed admin
    }

    public void doGoLogIn() {
        this.srcPage="login.xhtml";
    }

    public void doGoUserProfile() {
    	this.srcPage="profile.xhtml"; // removed user
    }

    public void doGoGlobalAgenda()
    {
        this.srcPage = "agenda_global.xhtml";
    }


}
