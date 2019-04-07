package com.cmarchive.bank.serviceinteraction.modele.input;

public class UtilisateurInput {

    private String id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public UtilisateurInput setId(String id) {
        this.id = id;
        return this;
    }

    public UtilisateurInput setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public UtilisateurInput setPrenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public UtilisateurInput setEmail(String email) {
        this.email = email;
        return this;
    }

    public UtilisateurInput setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
        return this;
    }
}
