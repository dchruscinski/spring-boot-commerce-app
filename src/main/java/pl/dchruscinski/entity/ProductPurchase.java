package pl.dchruscinski.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.Objects;

@Entity
public class ProductPurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Product product;

    @NotNull(message = "You need to specify amount of purchased product.")
    private Integer amount;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "You must set date for purchase.")
    private Date purchaseDate;

    public ProductPurchase() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPurchase that = (ProductPurchase) o;
        return Objects.equals(id, that.id) && Objects.equals(customer, that.customer) && Objects.equals(product, that.product) && Objects.equals(amount, that.amount) && Objects.equals(purchaseDate, that.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, product, amount, purchaseDate);
    }

    @Override
    public String toString() {
        return "ProductPurchase{" +
                "id=" + id +
                ", customer=" + customer +
                ", product=" + product +
                ", amount=" + amount +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}