package com.francis.inventmgmt.repository;

import com.francis.inventmgmt.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Long> {
}
