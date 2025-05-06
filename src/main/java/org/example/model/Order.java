package org.example.model;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private String id;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> list = new ArrayList<>();

    private String status;

    public Order() {
        this.id = UUID.randomUUID().toString();
        this.status = "PENDING";
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public List<Product> getList(){
        return this.list;
    }

    public void addProduct(Product product){
        this.list.add(product);
    }

    public void removeProduct(Product product){
        this.list.remove(product);
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String parseProductsList() {
        return this.list.toString();
    }
}
