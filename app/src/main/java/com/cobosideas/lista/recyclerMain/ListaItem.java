package com.cobosideas.lista.recyclerMain;

public class ListaItem {
    private long id;
    private String name;
    private String description;
    private int photoId;

    public ListaItem(long id, String name, String description, int photoId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.photoId = photoId;
    }
}
