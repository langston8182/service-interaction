package com.cmarchive.bank.serviceinteraction.repository;

import com.cmarchive.bank.serviceinteraction.modele.Utilisateur;
import com.cmarchive.bank.serviceinteraction.modele.Utilisateurs;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;

@RunWith(SpringJUnit4ClassRunner.class)
public class UtilisateurRepositoryImplTest {

    private final static String serviceUtilisateurUrl = "http://localhost:8100/utilisateurs";

    @InjectMocks
    private UtilisateurRepositoryImpl utilisateurRepository;

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(utilisateurRepository, "serviceUtilisateurUrl", serviceUtilisateurUrl);
    }

    @Test
    public void listerUtilisateurs() {
        Utilisateurs utilisateurs = creerUtilisateurs();
        given(restTemplate.getForObject(eq(serviceUtilisateurUrl), eq(Utilisateurs.class))).willReturn(utilisateurs);

        Utilisateurs resultat = utilisateurRepository.listerUtilisateurs();

        assertThat(resultat).isNotNull();
        assertThat(resultat.getUtilisateurs()).isNotEmpty()
                .isEqualTo(utilisateurs.getUtilisateurs());
    }

    @Test
    public void recupererUtilisateur() {
        Utilisateur utilisateur = creerUtilisateur();
        given(restTemplate.getForObject(eq(serviceUtilisateurUrl + "/1"), eq(Utilisateur.class))).willReturn(utilisateur);

        Utilisateur resultat = utilisateurRepository.recupererUtilisateur("1");

        assertThat(resultat).isNotNull();
        assertThat(resultat.getNom()).isEqualTo("Marchive");
        assertThat(resultat.getPrenom()).isEqualTo("Cyril");
        assertThat(resultat.getEmail()).isEqualTo("cyril.marchive@gmail.com");
    }

    @Test
    public void sauvegarderUtilisateur() {
        Utilisateur utilisateur = creerUtilisateur();
        HttpEntity<Utilisateur> requete = new HttpEntity<>(utilisateur);
        given(restTemplate.postForObject(eq(serviceUtilisateurUrl), eq(requete), eq(Utilisateur.class))).willReturn(utilisateur);

        Utilisateur resultat = utilisateurRepository.sauvegarderUtilisateur(utilisateur);

        assertThat(resultat).isNotNull();
        assertThat(resultat.getNom()).isEqualTo("Marchive");
        assertThat(resultat.getPrenom()).isEqualTo("Cyril");
        assertThat(resultat.getEmail()).isEqualTo("cyril.marchive@gmail.com");
    }

    @Test
    public void supprimerUtilisateur() {
        Utilisateur utilisateur = creerUtilisateur();
        HttpEntity<Utilisateur> requete = new HttpEntity<>(utilisateur);
        willDoNothing().given(restTemplate).delete(serviceUtilisateurUrl, requete);

        utilisateurRepository.supprimerUtilisateur(utilisateur);

        then(restTemplate).should().delete(serviceUtilisateurUrl, requete);
    }

    private Utilisateurs creerUtilisateurs() {
        Utilisateur utilisateur = creerUtilisateur();
        return new Utilisateurs()
                .setUtilisateurs(singletonList(utilisateur));
    }

    private Utilisateur creerUtilisateur() {

        return new Utilisateur()
                .setEmail("cyril.marchive@gmail.com")
                .setNom("Marchive")
                .setPrenom("Cyril");
    }
}