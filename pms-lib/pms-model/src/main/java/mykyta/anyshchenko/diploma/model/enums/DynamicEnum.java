package mykyta.anyshchenko.diploma.model.enums;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import mykyta.anyshchenko.diploma.model.serialization.DynamicEnumSerializer;

import java.util.Objects;

@JsonSerialize(using = DynamicEnumSerializer.class)
public abstract class DynamicEnum<T extends DynamicEnum<?>> {
    private final String value;

    protected DynamicEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(this.value, ((DynamicEnum) o).value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
