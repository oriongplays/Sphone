package com.dev.sphone.mod.common.phone;

import java.util.Objects;

public class Contact {

    int id;
    String name;
    String lastname;
    String numero;
    String notes;
    String photo;

    public Contact() {
    }

    public Contact(int id, String name, String lastname, String numero, String notes, String photo) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.numero = numero;
        this.notes = notes;
        this.photo = photo;
    }

    public Contact(int id, String name, String lastname, String numero, String notes) {
        this(id, name, lastname, numero, notes, "empty");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNumero() {
        return numero;
    }

    public String getNotes() {
        return notes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean equals(Contact obj) {
        return this.name.equals(obj.name) && Objects.equals(this.numero, obj.numero);
    }
}
