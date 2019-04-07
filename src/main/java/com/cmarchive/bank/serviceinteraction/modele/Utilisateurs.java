package com.cmarchive.bank.serviceinteraction.modele;

import java.util.List;

/**
 * Objet wrapper contenant la liste des utilisateurs retourne par le service utilisateur.
 */
public class Utilisateurs {

    private List<Utilisateur> utilisateursDtos;

    public Utilisateurs setUtilisateursDtos(List<Utilisateur> utilisateursDtos) {
        this.utilisateursDtos = utilisateursDtos;
        return this;
    }

    public List<Utilisateur> getUtilisateursDtos() {
        return utilisateursDtos;
    }
}
