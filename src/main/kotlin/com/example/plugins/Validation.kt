package com.example.plugins

import com.example.models.Customer
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.configureValidation() {
    install(RequestValidation) {
        validate<Customer> { customer ->
            if (customer.id <= 0)
                ValidationResult.Invalid("A customer ID should be greater than 0")
            else ValidationResult.Valid
        }
    }
}