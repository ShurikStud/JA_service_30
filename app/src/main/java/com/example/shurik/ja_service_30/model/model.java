package com.example.shurik.ja_service_30.model;

/**
 * Created by shurik on 05.02.2018.
 */

public class model {

    private int id;
    private String name;
    private String description;

    @Override
    public String toString() {
        return "model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        model model = (model) o;

        return id == model.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public model(int id, String name, String description) {

        this.id = id;
        this.name = name;
        this.description = description;
    }
}
