package com.readingisgood.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;
import com.readingisgood.enums.Status;
import com.readingisgood.util.Constants;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Audited
@Entity
@Table(name = "customer", schema = Constants.DB_SCHEMA)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer implements Serializable {

    @Id
    @GeneratedValue(generator = Constants.CUSTOMER_GENERATOR, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = Constants.CUSTOMER_GENERATOR,
            sequenceName = Constants.CUSTOMER_SEQUENCE,
            schema = Constants.DB_SCHEMA,
            allocationSize = 1
    )
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "address", nullable = false)
    private String address;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private Status status;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "created_user")
    private String createdUser;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orderList = Lists.newArrayList();

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }
}
