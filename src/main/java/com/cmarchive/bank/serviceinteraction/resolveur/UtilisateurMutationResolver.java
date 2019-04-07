package com.cmarchive.bank.serviceinteraction.resolveur;

import com.cmarchive.bank.serviceinteraction.modele.Utilisateur;
import com.cmarchive.bank.serviceinteraction.modele.input.UtilisateurInput;
import com.cmarchive.bank.serviceinteraction.service.UtilisateurService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UtilisateurMutationResolver implements GraphQLMutationResolver {

    private UtilisateurService utilisateurService;

    public UtilisateurMutationResolver(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/utilisateurs")
    @ResponseStatus(HttpStatus.CREATED)
    public Utilisateur sauvegarderUtilisateur(@RequestBody UtilisateurInput utilisateurInput) {
        return utilisateurService.sauvegarderUtilisateur(utilisateurInput);
    }

    @PutMapping("/utilisateurs")
    public Utilisateur modifierUtilisateur(@RequestBody UtilisateurInput utilisateurInput) {
        return utilisateurService.modifierUtilisateur(utilisateurInput);
    }

    @DeleteMapping("/utilisateurs")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimerUtilisateur(@RequestBody UtilisateurInput utilisateurInput) {
        utilisateurService.supprimerUtilisateur(utilisateurInput);
    }

}
