package edu.pitt.cs;

public class CatImpl implements Cat {

    private int id;
    private String name;
    private boolean rented;

    public CatImpl(int id, String name) {
        this.id = id;
        this.name = name;
        this.rented = false; // Initially, the cat is not rented
    }

    @Override
    public void rentCat() {
        if (!rented) {
            rented = true;
        }
    }

    @Override
    public void returnCat() {
        if (rented) {
            rented = false;
        }
    }

    @Override
    public void renameCat(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean getRented() {
        return rented;
    }

    @Override
    public String toString() {
        return "ID " + id + ". " + name;
    }
}
