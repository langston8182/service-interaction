package com.cmarchive.bank.serviceinteraction.mapper;

import com.cmarchive.bank.serviceinteraction.modele.Utilisateur;
import com.cmarchive.bank.serviceinteraction.modele.input.UtilisateurInput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UtilisateurMapperTest {

    @Autowired
    UtilisateurMapper utilisateurMapper;

    @Configuration
    @ComponentScan(basePackageClasses = UtilisateurMapper.class, useDefaultFilters = false, includeFilters = {
            @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = UtilisateurMapper.class) })
    public static class TestConfiguration {
        //
    }

    @Test
    public void mapVersUtilisateur() {
        UtilisateurInput utilisateurInput = new UtilisateurInput()
                .setEmail("cyril.marchive@gmail.com")
                .setNom("Marchive")
                .setPrenom("Cyril")
                .setMotDePasse("motDePasse");

        Utilisateur resultat = utilisateurMapper.mapVersUtilisateur(utilisateurInput);

        assertThat(resultat).isEqualToComparingFieldByField(utilisateurInput);
    }
}