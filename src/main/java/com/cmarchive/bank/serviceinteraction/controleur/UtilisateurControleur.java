package com.cmarchive.bank.serviceinteraction.controleur;

import com.cmarchive.bank.serviceinteraction.modele.Utilisateur;
import com.cmarchive.bank.serviceinteraction.modele.input.UtilisateurInput;
import com.cmarchive.bank.serviceinteraction.service.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UtilisateurControleur {

    private UtilisateurService utilisateurService;

    public UtilisateurControleur(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/utilisateurs")
    public List<Utilisateur> listerUtilisateurs() {
        return utilisateurService.listerUtilisateurs();
    }

    @GetMapping("/utilisateurs/{id}")
    public Utilisateur recupererUtilisateur(@PathVariable String id) {
        return utilisateurService.recupererUtilisateur(id);
    }

    @PostMapping("/utilisateurs")
    @ResponseStatus(HttpStatus.CREATED)
    public Utilisateur sauvegarderUtilisateur(@RequestBody UtilisateurInput utilisateurInput) {
        return utilisateurService.sauvegarderUtilisateur(utilisateurInput);
    }

    @PutMapping("/utilisateurs")
    public Utilisateur modifierUtilisateur(@RequestBody UtilisateurInput utilisateurInput) {
        return utilisateurService.sauvegarderUtilisateur(utilisateurInput);
    }

    @DeleteMapping("/utilisateurs")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimerUtilisateur(@RequestBody UtilisateurInput utilisateurInput) {
        utilisateurService.supprimerUtilisateur(utilisateurInput);
    }
}
