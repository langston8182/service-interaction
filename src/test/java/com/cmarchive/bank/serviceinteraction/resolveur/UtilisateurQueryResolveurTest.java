package com.cmarchive.bank.serviceinteraction.resolveur;

import com.cmarchive.bank.serviceinteraction.modele.Utilisateur;
import com.cmarchive.bank.serviceinteraction.service.UtilisateurService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UtilisateurQueryResolveur.class)
@AutoConfigureMockMvc(secure=false)
public class UtilisateurQueryResolveurTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UtilisateurService utilisateurService;

    @MockBean
    private ResourceServerProperties resourceServerProperties;

    @MockBean
    private AuthenticationConfiguration authenticationConfiguration;

    @MockBean
    private ObjectPostProcessor objectPostProcessor;

    @Test
    public void listerUtilisateurs() throws Exception {
        Utilisateur cyril = new Utilisateur()
                .setEmail("cyril.marchive@gmail.com")
                .setNom("Marchive")
                .setPrenom("Cyril");
        Utilisateur melanie = new Utilisateur()
                .setEmail("melanie.boussat@gmail.com")
                .setNom("Boussat")
                .setPrenom("Melanie");
        List<Utilisateur> utilisateurs = Stream.of(cyril, melanie).collect(Collectors.toList());
        given(utilisateurService.listerUtilisateurs()).willReturn(utilisateurs);

        mockMvc.perform(get("/utilisateurs")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void recupererUtilisateur() throws Exception {
        Utilisateur utilisateur = creerUtilisateur();
        given(utilisateurService.recupererUtilisateur("1")).willReturn(utilisateur);

        mockMvc.perform(get("/utilisateurs/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", equalTo("Marchive")))
                .andExpect(jsonPath("$.prenom", equalTo("Cyril")))
                .andExpect(jsonPath("$.email", equalTo("cyril.marchive@gmail.com")));
    }

    private Utilisateur creerUtilisateur() {
        return new Utilisateur()
                .setEmail("cyril.marchive@gmail.com")
                .setNom("Marchive")
                .setPrenom("Cyril");
    }
}