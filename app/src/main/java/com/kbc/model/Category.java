package com.kbc.model;

/**
 * Category object
 */
public class Category {
    private String name;
    private int id;
    private String characterEncoding;

    public String getName(String business) {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharacterEncoding() {
        return characterEncoding;
    }

    public void setCharacterEncoding(String characterEncoding) {
        this.characterEncoding = characterEncoding;
    }


}
