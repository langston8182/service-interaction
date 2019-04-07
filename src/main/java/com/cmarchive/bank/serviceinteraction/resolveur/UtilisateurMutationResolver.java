package com.cmarchive.bank.serviceinteraction.resolveur;

import com.cmarchive.bank.serviceinteraction.modele.Utilisateur;
import com.cmarchive.bank.serviceinteraction.modele.input.UtilisateurInput;
import com.cmarchive.bank.serviceinteraction.service.UtilisateurService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class UtilisateurMutationResolver implements GraphQLMutationResolver {

    private UtilisateurService utilisateurService;

    public UtilisateurMutationResolver(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    public Utilisateur sauvegarderUtilisateur(@RequestBody UtilisateurInput utilisateurInput) {
        return utilisateurService.sauvegarderUtilisateur(utilisateurInput);
    }

    public Utilisateur modifierUtilisateur(@RequestBody UtilisateurInput utilisateurInput) {
        return utilisateurService.modifierUtilisateur(utilisateurInput);
    }

    public void supprimerUtilisateur(@RequestBody UtilisateurInput utilisateurInput) {
        utilisateurService.supprimerUtilisateur(utilisateurInput);
    }

}
