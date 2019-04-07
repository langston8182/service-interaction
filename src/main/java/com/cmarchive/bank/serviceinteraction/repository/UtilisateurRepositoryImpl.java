package com.cmarchive.bank.serviceinteraction.repository;

import com.cmarchive.bank.serviceinteraction.modele.Utilisateur;
import com.cmarchive.bank.serviceinteraction.modele.Utilisateurs;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import static java.util.Collections.singletonList;

@Repository
public class UtilisateurRepositoryImpl implements UtilisateurRepository {

    private final String serviceUtilisateurUrl = "http://localhost:8100/utilisateurs";

    private RestTemplate restTemplate;

    private ObjectMapper objectMapper;

    public UtilisateurRepositoryImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public Utilisateurs listerUtilisateurs() {
        return restTemplate.getForObject(serviceUtilisateurUrl, Utilisateurs.class, creerHeaders());
    }

    @Override
    public Utilisateur recupererUtilisateur(String id) {
        return restTemplate.getForObject(serviceUtilisateurUrl + "/" + id, Utilisateur.class, creerHeaders());
    }

    @Override
    public Utilisateur sauvegarderUtilisateur(Utilisateur utilisateur) {
        HttpEntity<Utilisateur> requete = new HttpEntity<>(utilisateur);
        return restTemplate.postForObject(serviceUtilisateurUrl, requete, Utilisateur.class, creerHeaders());
    }

    @Override
    public void supprimerUtilisateur(Utilisateur utilisateur) {
        HttpEntity<Utilisateur> requete = new HttpEntity<>(utilisateur);
        restTemplate.delete(serviceUtilisateurUrl, requete, creerHeaders());
    }

    private HttpHeaders creerHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
