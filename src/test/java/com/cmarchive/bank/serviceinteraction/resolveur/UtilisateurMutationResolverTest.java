package com.cmarchive.bank.serviceinteraction.resolveur;

import com.cmarchive.bank.serviceinteraction.modele.Utilisateur;
import com.cmarchive.bank.serviceinteraction.modele.input.UtilisateurInput;
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

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UtilisateurMutationResolver.class)
@AutoConfigureMockMvc(secure=false)
public class UtilisateurMutationResolverTest {

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
    public void supprimerUtilisateur() throws Exception {
        UtilisateurInput cyril = creerUtilisateurInput();
        willDoNothing().given(utilisateurService).supprimerUtilisateur(cyril);

        mockMvc.perform(delete("/utilisateurs")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cyril)))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void sauvegarderUtilisateur() throws Exception {
        Utilisateur cyril = creerUtilisateur();
        Utilisateur reponse = new Utilisateur()
                .setId("1");
        given(utilisateurService.sauvegarderUtilisateur(any(UtilisateurInput.class))).willReturn(reponse);

        mockMvc.perform(post("/utilisateurs")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cyril)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo("1")));
    }

    @Test
    public void modifierUtilisateur() throws Exception {
        UtilisateurInput cyril = creerUtilisateurInput();
        Utilisateur reponse = creerUtilisateur()
                .setNom("Boussat");
        given(utilisateurService.modifierUtilisateur(any(UtilisateurInput.class))).willReturn(reponse);

        mockMvc.perform(put("/utilisateurs")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cyril)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", equalTo("Boussat")));
    }

    private UtilisateurInput creerUtilisateurInput() {
        return new UtilisateurInput()
                .setEmail("cyril.marchive@gmail.com")
                .setNom("Marchive")
                .setPrenom("Cyril");
    }

    private Utilisateur creerUtilisateur() {
        return new Utilisateur()
                .setEmail("cyril.marchive@gmail.com")
                .setNom("Marchive")
                .setPrenom("Cyril");
    }
}