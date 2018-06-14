package de.diedavids.cuba.ceuw.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum Salutation implements EnumClass<String> {

    MR("MR"),
    MRS("MRS");

    private String id;

    Salutation(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static Salutation fromId(String id) {
        for (Salutation at : Salutation.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}