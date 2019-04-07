package com.cmarchive.bank.serviceinteraction.resolveur;

import com.cmarchive.bank.serviceinteraction.modele.Utilisateur;
import com.cmarchive.bank.serviceinteraction.service.UtilisateurService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
public class UtilisateurQueryResolveur implements GraphQLQueryResolver {

    private UtilisateurService utilisateurService;

    public UtilisateurQueryResolveur(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    public List<Utilisateur> listerUtilisateurs() {
        return utilisateurService.listerUtilisateurs();
    }

    public Utilisateur recupererUtilisateur(@PathVariable String id) {
        return utilisateurService.recupererUtilisateur(id);
    }
}
