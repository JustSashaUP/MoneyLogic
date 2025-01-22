package com.moneylogic.finance.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private TransactionType transactionType;

    protected Category() {}

    public static Category createCategory(User user, String name, TransactionType transactionType) {
        Category category = new Category();
        category.user = user;
        category.name = name;
        category.transactionType = transactionType;
        return category;
    }

    private static Category createEmptyCategory() {
        Category category = new Category();
        category.user = null;
        category.name = null;
        category.transactionType = null;
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id && Objects.equals(user, category.user) && Objects.equals(name, category.name) && transactionType == category.transactionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, name, transactionType);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", transactionType=" + transactionType +
                '}';
    }
}
