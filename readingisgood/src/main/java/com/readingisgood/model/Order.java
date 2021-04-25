package com.readingisgood.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.readingisgood.enums.OrderStatus;
import com.readingisgood.enums.Status;
import com.readingisgood.util.Constants;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Audited
@Entity
@Table(name = "order", schema = Constants.DB_SCHEMA)
public class Order implements Serializable {

    @Id
    @GeneratedValue(generator = Constants.ORDER_GENERATOR, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = Constants.ORDER_GENERATOR,
            sequenceName = Constants.ORDER_SEQUENCE,
            schema = Constants.DB_SCHEMA,
            allocationSize = 1
    )
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Book book;

    @JsonIgnore
    @Column(name = "book_id")
    private Long bookId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Customer customer;

    @JsonIgnore
    @Column(name = "customer_id")
    private Long customerId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private Status status;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "created_user")
    private String createdUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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
