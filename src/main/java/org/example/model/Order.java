package org.example.model;

import java.util.LinkedList;
import java.util.UUID;

public class Order {
    private String id;
    private LinkedList<Product> list;

    public Order() {
        this.id = UUID.randomUUID().toString();
        this.list = new LinkedList<Product>();
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public LinkedList<Product> getList(){
        return this.list;
    }

    public void addProduct(Product product){
        this.list.add(product);
    }

    public void removeProduct(Product product){
        this.list.remove(product);
    }

    public String parseProductsList() {
        return this.list.toString();
    }
}
