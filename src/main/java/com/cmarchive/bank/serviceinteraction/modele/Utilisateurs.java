package com.cmarchive.bank.serviceinteraction.modele;

import java.util.List;

/**
 * Objet wrapper contenant la liste des utilisateurs retourne par le service utilisateur.
 */
public class Utilisateurs {

    private List<Utilisateur> utilisateurs;

    public Utilisateurs setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
        return this;
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }
}
