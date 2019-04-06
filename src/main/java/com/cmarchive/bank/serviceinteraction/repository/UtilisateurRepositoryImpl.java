package com.cmarchive.bank.serviceinteraction.repository;

import com.cmarchive.bank.serviceinteraction.modele.Utilisateur;
import com.cmarchive.bank.serviceinteraction.modele.Utilisateurs;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class UtilisateurRepositoryImpl implements UtilisateurRepository {

    private final String serviceUtilisateurUrl = "http://localhost:8100/utilisateurs";

    private RestTemplate restTemplate;

    public UtilisateurRepositoryImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Utilisateurs listerUtilisateurs() {
        return restTemplate.getForObject(serviceUtilisateurUrl, Utilisateurs.class);
    }

    @Override
    public Utilisateur recupererUtilisateur(String id) {
        return restTemplate.getForObject(serviceUtilisateurUrl + "/" + id, Utilisateur.class);
    }

    @Override
    public Utilisateur sauvegarderUtilisateur(Utilisateur utilisateur) {
        HttpEntity<Utilisateur> requete = new HttpEntity<>(utilisateur);
        return restTemplate.postForObject(serviceUtilisateurUrl, requete, Utilisateur.class);
    }

    @Override
    public void supprimerUtilisateur(Utilisateur utilisateur) {
        HttpEntity<Utilisateur> requete = new HttpEntity<>(utilisateur);
        restTemplate.delete(serviceUtilisateurUrl, requete);
    }
}
