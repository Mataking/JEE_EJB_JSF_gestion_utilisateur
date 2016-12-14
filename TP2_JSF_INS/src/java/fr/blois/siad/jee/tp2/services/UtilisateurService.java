package fr.blois.siad.jee.tp2.services;

import fr.blois.siad.jee.tp2.dto.Utilisateur;
import java.util.List;

public interface UtilisateurService {

    List<Utilisateur> listerTous();

    Utilisateur lire(Integer id);
    
    void ajouter(Utilisateur u);

    public void supprimer(Integer id);
    
    public void update(Integer id);
    
    public void ban(Integer id);
    
    public void unban(Integer id);
    
    public List<Utilisateur> trie();
}
