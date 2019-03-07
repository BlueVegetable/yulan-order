package com.yulan.pojo;

import java.util.Objects;

public class Unit {

    private String note;
    private String unit;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unit)) return false;
        Unit unit1 = (Unit) o;
        return Objects.equals(getNote(), unit1.getNote()) &&
                Objects.equals(getUnit(), unit1.getUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNote(), getUnit());
    }

    @Override
    public String toString() {
        return "Unit{" +
                "note='" + note + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}