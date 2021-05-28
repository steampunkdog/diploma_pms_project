package mykyta.anyshchenko.diploma.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Gender extends DynamicEnum<Gender>{
    public static final Gender FEMALE = new Gender("female");
    public static final Gender MALE = new Gender("male");
    public static final Gender OTHER = new Gender("other");

    private Gender(String value) {
        super(value);
    }

    @JsonCreator
    public static Gender create(String gender) {
        return new Gender(gender);
    }
}
