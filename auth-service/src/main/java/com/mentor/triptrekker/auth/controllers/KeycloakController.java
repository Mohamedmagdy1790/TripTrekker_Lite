package com.mentor.triptrekker.auth.controllers;

import com.mentor.triptrekker.auth.config.KeycloakConfig;
import com.mentor.triptrekker.auth.dtoMapper.UserKeycloakCreateMapper;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/create-realm")
public class KeycloakController {

    @Autowired
    private Keycloak keycloak;
    @Autowired
    private KeycloakConfig keycloakConfig;
    @Autowired
    private UserKeycloakCreateMapper userKeycloakCreateMapper;

    @PostMapping()
    public ResponseEntity<String> createRealm() {
        RealmRepresentation realm = new RealmRepresentation();
        realm.setRealm("triptrikker");
        keycloak.realms().create(realm);
        return ResponseEntity.ok("Realm created successfully");
    }

    @PostMapping("/user")
    public ResponseEntity<String> createUser() {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername("username");
        userRepresentation.setLastName ("hamada");
        userRepresentation.setEmail ("hamada@gmail.com");
        // Set other user attributes as needed

        keycloak.realm("triptrikker").users().create(userRepresentation);

        return ResponseEntity.ok("User created successfully");
    }


    public List<String> getAllRoles(){
        ClientRepresentation clientRep = keycloak
                .realm("triptrikker")
                .clients()
                .findByClientId("admin-cli")
                .get(0);
        List<String> availableRoles = keycloak
                .realm("triptrikker")
                .clients()
                .get(clientRep.getId())
                .roles()
                .list()
                .stream()
                .map(role -> role.getName())
                .collect(Collectors.toList());
        return availableRoles;
    }

    @GetMapping("/add-role/{roleName}")
    public void addRealmRole(@PathVariable(name = "roleName") String new_role_name){
        if(!getAllRoles().contains(new_role_name)){   RoleRepresentation roleRep = new  RoleRepresentation();
            roleRep.setName(new_role_name);
            roleRep.setDescription("role_" + new_role_name);ClientRepresentation clientRep = keycloak
                    .realm("triptrikker")
                    .clients()
                    .findByClientId("admin-cli")
                    .get(0);
            keycloak.realm("triptrikker")
                    .clients()
                    .get(clientRep.getId())
                    .roles()
                    .create(roleRep);
        }
        addRealmRoleToUser ("username" ,new_role_name);
    }




    public void addRealmRoleToUser(String userName, String role_name){ String client_id = keycloak
            .realm("triptrikker")
            .clients()
            .findByClientId("admin-cli")
            .get(0)
            .getId();  String userId = keycloak
            .realm("triptrikker")
            .users()
            .search(userName)
            .get(0)
            .getId();


             UserResource user = keycloak
            .realm("triptrikker")
            .users()
            .get(userId);  List<RoleRepresentation> roleToAdd = new LinkedList<> ();

             roleToAdd.add(keycloak
            .realm("triptrikker")
            .clients()
            .get(client_id)
            .roles()
            .get(role_name)
            .toRepresentation()
    );

            user.roles().clientLevel(client_id).add(roleToAdd);
    }

}

