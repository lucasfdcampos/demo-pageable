package br.com.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customer")
public class Customer {

    private static final long serialVersionUID = 1L;

    public Customer() {
    }

    public Customer(Long id, @NotNull @Size(max = 60) String name, @NotNull @Size(max = 60) String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Id
    @Column(nullable = false, name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 60)
    @Column(nullable = false, name = "name")
    private String name;

    @NotNull
    @Size(max = 60)
    @Column(nullable = false, name = "email")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", email=" + email + "]";
    }
}
