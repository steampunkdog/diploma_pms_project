package mykyta.anyshchenko.diploma.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Role extends DynamicEnum<Role> {
    public static final Role ANONYMIZED = new Role("anonymized");
    public static final Role CLIENT = new Role("client");
    public static final Role WORKER = new Role("worker");
    public static final Role MANAGER = new Role("manager");
    public static final Role ADMINISTRATOR = new Role("administrator");

    private Role(String value) {
        super(value);
    }

    @JsonCreator
    public static Role create(String role) {
        return new Role(role);
    }
}

