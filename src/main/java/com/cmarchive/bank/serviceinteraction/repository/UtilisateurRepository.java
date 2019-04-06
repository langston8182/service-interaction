package com.cmarchive.bank.serviceinteraction.repository;

import com.cmarchive.bank.serviceinteraction.modele.Utilisateur;
import com.cmarchive.bank.serviceinteraction.modele.Utilisateurs;

/**
 * Appel le service utilisateur pour gerer les utilisateur du systeme bank.
 */
public interface UtilisateurRepository {

    Utilisateurs listerUtilisateurs();
    Utilisateur recupererUtilisateur(String id);
    Utilisateur sauvegarderUtilisateur(Utilisateur utilisateur);
    void supprimerUtilisateur(Utilisateur utilisateur);

}
