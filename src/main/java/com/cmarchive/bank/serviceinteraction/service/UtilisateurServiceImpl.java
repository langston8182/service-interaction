package com.cmarchive.bank.serviceinteraction.service;

import com.cmarchive.bank.serviceinteraction.modele.Utilisateur;
import com.cmarchive.bank.serviceinteraction.modele.Utilisateurs;
import com.cmarchive.bank.serviceinteraction.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public List<Utilisateur> listerUtilisateurs() {
        Utilisateurs utilisateurs = utilisateurRepository.listerUtilisateurs();
        return ofNullable(utilisateurs)
                .map(Utilisateurs::getUtilisateurs)
                .orElse(Collections.emptyList());
    }

    @Override
    public Utilisateur recupererUtilisateur(String id) {
        return utilisateurRepository.recupererUtilisateur(id);
    }

    @Override
    public Utilisateur sauvegarderUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.sauvegarderUtilisateur(utilisateur);
    }

    @Override
    public void supprimerUtilisateur(Utilisateur utilisateur) {
        utilisateurRepository.supprimerUtilisateur(utilisateur);
    }
}
