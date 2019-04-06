package com.cmarchive.bank.serviceinteraction.service;

import com.cmarchive.bank.serviceinteraction.modele.Utilisateur;

import java.util.List;

public interface UtilisateurService {

    List<Utilisateur> listerUtilisateurs();
    Utilisateur recupererUtilisateur(String id);
    Utilisateur sauvegarderUtilisateur(Utilisateur utilisateur);
    void supprimerUtilisateur(Utilisateur utilisateur);

}
