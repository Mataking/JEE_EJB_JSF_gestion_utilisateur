package fr.blois.siad.jee.tp2.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import fr.blois.siad.jee.tp2.dto.*;
import fr.blois.siad.jee.tp2.entities.UtilisateurEntity;
import java.util.Random;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

@Remote(UtilisateurService.class)
@Stateless(mappedName = "UtilisateurService")
public class UtilisateurServiceBean implements UtilisateurService {

    @PersistenceContext
    EntityManager em;
    

    @PostConstruct
    private void _initMap() {
        //em.persist(new UtilisateurEntity("a.dupont@gmail.com", "dupont1234", "Alexandre DUPONT", new Date()));
        //em.persist(new UtilisateurEntity("b.thierry@gmail.com", "thierry1234", "Béatrice THIERRY", new Date()));
    }

    @Override
    public List<Utilisateur> listerTous() {
        List<Utilisateur> result = new ArrayList<>();
        Query q = em.createQuery("SELECT u FROM UtilisateurEntity u");
        List<UtilisateurEntity> entities = q.getResultList();
        if (entities != null) {
            for(UtilisateurEntity e : entities) result.add(e.getDTO());
        }
        return result;
    }

    @Override
    public Utilisateur lire(Integer id) {
        try {
            UtilisateurEntity entity = em.find(UtilisateurEntity.class, id);
            return entity.getDTO();
        }
        catch(NoResultException nre){}
        return null;
    }

    @Override
    public void ajouter(Utilisateur u) {     
       if ( u != null) {
        em.persist(new UtilisateurEntity(u.getEmail(), u.getMotDePasse(), u.getNom(), new Date(), u.getBanni()));
       }
    }

    @Override
    public void supprimer(Integer id) {
        try {
            UtilisateurEntity entity = em.find(UtilisateurEntity.class, id);
            em.remove(entity);
        }
        catch(NoResultException nre){}
    }
    
    @Override
    public void update(Integer id){
        try{
            UtilisateurEntity entity = em.find(UtilisateurEntity.class, id);
            entity.setMotDePasse(UUID.randomUUID().toString());
            em.merge(entity);
        }
        catch(NoResultException nre){}
    }
    
    @Override
    public void ban(Integer id){
        try{
            UtilisateurEntity entity = em.find(UtilisateurEntity.class, id);
            entity.setBanni(true);
            em.merge(entity);
        }catch(NoResultException nre){}
    }
    
    @Override
    public void unban(Integer id){
        try{
            UtilisateurEntity entity = em.find(UtilisateurEntity.class, id);
            entity.setBanni(false);
            em.merge(entity);
        }catch(NoResultException nre){}
    }
}
