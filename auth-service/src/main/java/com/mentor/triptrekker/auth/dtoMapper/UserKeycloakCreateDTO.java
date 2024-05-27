package com.mentor.triptrekker.auth.dtoMapper;

import org.springframework.stereotype.Component;

public record UserKeycloakCreateDTO(String firstName,
                                    String lastName,
                                    String email,
                                    String userName,
                                    String password) {
}