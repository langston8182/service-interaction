package com.cmarchive.bank.serviceinteraction.service;

import com.cmarchive.bank.serviceinteraction.modele.Utilisateur;
import com.cmarchive.bank.serviceinteraction.modele.input.UtilisateurInput;

import java.util.List;

public interface UtilisateurService {

    List<Utilisateur> listerUtilisateurs();
    Utilisateur recupererUtilisateur(String id);
    Utilisateur sauvegarderUtilisateur(UtilisateurInput utilisateurInput);
    Utilisateur modifierUtilisateur(UtilisateurInput utilisateurInput);
    void supprimerUtilisateur(UtilisateurInput utilisateurInput);

}
