package fr.blois.siad.jee.tp2.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-14T09:05:18")
@StaticMetamodel(UtilisateurEntity.class)
public class UtilisateurEntity_ { 

    public static volatile SingularAttribute<UtilisateurEntity, String> motDePasse;
    public static volatile SingularAttribute<UtilisateurEntity, Date> dateInscription;
    public static volatile SingularAttribute<UtilisateurEntity, Boolean> banni;
    public static volatile SingularAttribute<UtilisateurEntity, Integer> id;
    public static volatile SingularAttribute<UtilisateurEntity, String> nom;
    public static volatile SingularAttribute<UtilisateurEntity, String> email;

}