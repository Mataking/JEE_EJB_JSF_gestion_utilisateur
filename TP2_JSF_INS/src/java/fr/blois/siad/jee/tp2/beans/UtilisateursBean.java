package fr.blois.siad.jee.tp2.beans;

import fr.blois.siad.jee.tp2.dto.Utilisateur;
import fr.blois.siad.jee.tp2.services.UtilisateurService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@ManagedBean
@RequestScoped
public class UtilisateursBean {

    private String email;
    private String motDePasse;
    private String nom;
    
    private boolean trie = false;
    
    List<Utilisateur> uListUsers = new ArrayList<>();
   
    private static final long serialVersionUID = 1L;

    public UtilisateursBean() {  
        
    }

    public List<Utilisateur> getUtilisateurs() {
        return getService().listerTous();
    }

    private UtilisateurService getService() {
        try {
            UtilisateurService beanRemote = (UtilisateurService) new InitialContext().lookup("UtilisateurService");
            return beanRemote;
        } catch (NamingException ne) {
            System.err.println(ne);
        }
        return null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isTrie() {
        return trie;
    }

    public void setTrie(boolean trie) {
        this.trie = trie;
    }   

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {

    }

    public String checkAddUser() {

        // Validation
        FacesContext context = FacesContext.getCurrentInstance();        
        if (email == null || email.isEmpty()) {
            context.addMessage(null, new FacesMessage("Email obligatoire"));
        }
        if (motDePasse == null || motDePasse.isEmpty()) {
            context.addMessage(null, new FacesMessage("MDP obligatoire"));
        }
        if (nom == null || nom.isEmpty()) {
            context.addMessage(null, new FacesMessage("Nom obligatoire"));
        }
        if (motDePasse.length() < 8) {
            context.addMessage(null, new FacesMessage("MDP trop court !"));
        }
        if (!email.matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+" )) {
            context.addMessage(null, new FacesMessage("Email non valide"));
        }
        if (!context.getMessageList().isEmpty()) {
            return null;
        }

        // Cas nominal
        try{
            getService().ajouter(new Utilisateur(null, email, motDePasse, nom, new Date(), false));
        }catch(Exception e){
            
            context.addMessage(null, new FacesMessage("Email ou pseudonyme déjà utilisé ! Please change !"));
            return "ajout";
        }
        return "index";
    }
    
    public String supprimer(Integer id) {
        getService().supprimer(id);
        return "index";
    }
    
    public String update(Integer id){
        getService().update(id);
        return "index";
    }
    
    public String ban(Integer id){
       getService().ban(id);
       return "index";
    }
    
    public String unban(Integer id){
       getService().unban(id);
       return "index";
    }
    
    public List<Utilisateur> display(){  
        if(isTrie())
            return uListUsers;  
        else {
            uListUsers = getService().listerTous();
            return uListUsers;
        }        
    }
       
    public String trieByName(){       
        setTrie(true);        
        Collections.sort(uListUsers, (a, b) -> a.getNom().compareToIgnoreCase(b.getNom()));   
        return "index";
    }
    
    public String trieBasic(){       
        setTrie(false);
        return "index";
    }
}
