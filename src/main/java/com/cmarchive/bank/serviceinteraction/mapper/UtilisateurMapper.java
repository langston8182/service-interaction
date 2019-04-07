package com.cmarchive.bank.serviceinteraction.mapper;

import com.cmarchive.bank.serviceinteraction.modele.Utilisateur;
import com.cmarchive.bank.serviceinteraction.modele.input.UtilisateurInput;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {

    Utilisateur mapVersUtilisateur(UtilisateurInput utilisateurInput);

}
