package com.francis.inventmgmt.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="customers" )
@Data
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String address;


}