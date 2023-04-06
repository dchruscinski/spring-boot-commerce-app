package pl.dchruscinski.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Product must have name.")
    private String name;

    @NotNull(message = "Product price must be set.")
    private Integer price;

    private String color;

    @OneToMany(mappedBy = "product")
    private Set<ProductOwnership> productAmount;

    public Product() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Set<ProductOwnership> getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Set<ProductOwnership> productAmount) {
        this.productAmount = productAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(color, product.color) && Objects.equals(productAmount, product.productAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, color, productAmount);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", productAmount=" + productAmount +
                '}';
    }
}