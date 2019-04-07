package com.cmarchive.bank.serviceinteraction.service;

import com.cmarchive.bank.serviceinteraction.mapper.UtilisateurMapper;
import com.cmarchive.bank.serviceinteraction.modele.Utilisateur;
import com.cmarchive.bank.serviceinteraction.modele.Utilisateurs;
import com.cmarchive.bank.serviceinteraction.modele.input.UtilisateurInput;
import com.cmarchive.bank.serviceinteraction.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;
    private UtilisateurMapper utilisateurMapper;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, UtilisateurMapper utilisateurMapper) {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurMapper = utilisateurMapper;
    }

    @Override
    public List<Utilisateur> listerUtilisateurs() {
        Utilisateurs utilisateurs = utilisateurRepository.listerUtilisateurs();
        return ofNullable(utilisateurs)
                .map(Utilisateurs::getUtilisateursDtos)
                .orElse(Collections.emptyList());
    }

    @Override
    public Utilisateur recupererUtilisateur(String id) {
        return utilisateurRepository.recupererUtilisateur(id);
    }

    @Override
    public Utilisateur sauvegarderUtilisateur(UtilisateurInput utilisateurInput) {
        Utilisateur utilisateur = utilisateurMapper.mapVersUtilisateur(utilisateurInput);
        return utilisateurRepository.sauvegarderUtilisateur(utilisateur);
    }

    @Override
    public Utilisateur modifierUtilisateur(UtilisateurInput utilisateurInput) {
        Utilisateur utilisateur = utilisateurMapper.mapVersUtilisateur(utilisateurInput);
        return utilisateurRepository.modifierUtilisateur(utilisateur);
    }

    @Override
    public void supprimerUtilisateur(UtilisateurInput utilisateurInput) {
        Utilisateur utilisateur = utilisateurMapper.mapVersUtilisateur(utilisateurInput);
        utilisateurRepository.supprimerUtilisateur(utilisateur);
    }
}
