package ru.otus.spring.davlks.security.enums;

import ru.otus.spring.davlks.security.entity.Role;

import java.util.List;

public enum RoleNames {

    LIBRARIAN(1, List.of(new Role(1,"ADMIN"))),

    CUSTOMER(2, List.of(new Role(2, "USER"))),

    MANAGER(3, List.of(new Role(1, "ADMIN"), new Role( 2,"USER")));

    private final List<Role> authorities;

    private final long id;

    RoleNames(long id, List<Role> authorities) {
        this.id = id;
        this.authorities = authorities;
    }

    public static List<Role> getAuthoritiesById(long id) {
        for (RoleNames e: values()) {
            if (e.id == id) {
                return e.authorities;
            }
        }
        return List.of();
    }

    public long getId() {
        return id;
    }
}
