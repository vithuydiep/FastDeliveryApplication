package com.example.TVK.Model;

public class Feedback {
    private int id;
    private int idOrder;
    private float rating;
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Feedback(int id, int idOrder, float rating, String note) {
        this.id = id;
        this.idOrder = idOrder;
        this.rating = rating;
        this.note = note;
    }
}
