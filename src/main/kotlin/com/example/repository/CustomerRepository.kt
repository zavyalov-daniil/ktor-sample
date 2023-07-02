package com.example.repository

import com.example.models.Customer
import com.example.models.CustomerEntity
import com.example.models.Customers
import org.ktorm.database.Database
import org.ktorm.entity.add
import org.ktorm.entity.sequenceOf

class CustomerRepository {

    private val database = Database.connect(
        url = "jdbc:postgresql://localhost:5439/postgres",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "postgres"
    )

    fun createCustomer(customer: Customer): Boolean {
        val newCustomer = CustomerEntity {
            firstName = customer.firstName
            lastName = customer.lastName
            email = customer.email
        }
        val affectedRecordsNumber =
            database.sequenceOf(Customers)
                .add(newCustomer)
        return affectedRecordsNumber == 1;
    }
}