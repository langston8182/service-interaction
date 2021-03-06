package com.cmarchive.bank.serviceinteraction.service;

import com.cmarchive.bank.serviceinteraction.mapper.UtilisateurMapper;
import com.cmarchive.bank.serviceinteraction.modele.Utilisateur;
import com.cmarchive.bank.serviceinteraction.modele.Utilisateurs;
import com.cmarchive.bank.serviceinteraction.modele.input.UtilisateurInput;
import com.cmarchive.bank.serviceinteraction.repository.UtilisateurRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UtilisateurServiceImplTest {

    @InjectMocks
    private UtilisateurServiceImpl utilisateurService;

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private UtilisateurMapper utilisateurMapper;

    @Test
    public void listerUtilisateurs() {
        Utilisateurs utilisateurs = creerUtilisateurs();
        given(utilisateurRepository.listerUtilisateurs()).willReturn(utilisateurs);

        List<Utilisateur> resultat = utilisateurService.listerUtilisateurs();

        then(utilisateurRepository).should().listerUtilisateurs();
        assertThat(resultat).isNotEmpty()
                .isEqualTo(utilisateurs.getUtilisateursDtos());
    }

    @Test
    public void recupererUtilisateur() {
        String idUtilisateur = "1";
        Utilisateur utilisateur = creerUtilisateur();
        given(utilisateurRepository.recupererUtilisateur(idUtilisateur)).willReturn(utilisateur);

        Utilisateur resultat = utilisateurService.recupererUtilisateur(idUtilisateur);

        then(utilisateurRepository).should().recupererUtilisateur(idUtilisateur);
        assertThat(resultat).isNotNull()
                .isEqualTo(utilisateur);
    }

    @Test
    public void sauvegarderUtilisateur() {
        Utilisateur utilisateur = creerUtilisateur();
        UtilisateurInput utilisateurInput = creerUtilisateurInput();
        Utilisateur reponse = new Utilisateur()
                .setId("1");
        given(utilisateurMapper.mapVersUtilisateur(utilisateurInput)).willReturn(utilisateur);
        given(utilisateurRepository.sauvegarderUtilisateur(utilisateur)).willReturn(reponse);

        Utilisateur resultat = utilisateurService.sauvegarderUtilisateur(utilisateurInput);

        then(utilisateurRepository).should().sauvegarderUtilisateur(utilisateur);
        assertThat(resultat).isNotNull()
                .isEqualTo(reponse);
    }

    @Test
    public void supprimerUtilisateur() {
        Utilisateur utilisateur = creerUtilisateur();
        UtilisateurInput utilisateurInput = creerUtilisateurInput();
        given(utilisateurMapper.mapVersUtilisateur(utilisateurInput)).willReturn(utilisateur);
        willDoNothing().given(utilisateurRepository).supprimerUtilisateur(utilisateur);

        utilisateurService.supprimerUtilisateur(utilisateurInput);

        then(utilisateurRepository).should().supprimerUtilisateur(utilisateur);
    }

    private Utilisateurs creerUtilisateurs() {
        Utilisateur utilisateur = creerUtilisateur();
        return new Utilisateurs()
                .setUtilisateursDtos(singletonList(utilisateur));
    }

    private Utilisateur creerUtilisateur() {

        return new Utilisateur()
                .setEmail("cyril.marchive@gmail.com")
                .setNom("Marchive")
                .setPrenom("Cyril");
    }

    private UtilisateurInput creerUtilisateurInput() {

        return new UtilisateurInput()
                .setEmail("cyril.marchive@gmail.com")
                .setNom("Marchive")
                .setPrenom("Cyril");
    }
}