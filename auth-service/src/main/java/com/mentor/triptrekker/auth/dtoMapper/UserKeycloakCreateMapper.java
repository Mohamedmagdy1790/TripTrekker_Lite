package com.mentor.triptrekker.auth.dtoMapper;

import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserKeycloakCreateMapper {

    UserRepresentation userKeycloakCreateToUserRep (UserKeycloakCreateDTO userKeycloakCreateDTO);
}
