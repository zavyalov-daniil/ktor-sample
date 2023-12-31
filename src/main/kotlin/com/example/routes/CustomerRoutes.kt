package com.example.routes

import com.example.models.Customer
import com.example.models.customerStorage
import com.example.repository.CustomerRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.customerRouting() {
    route("/customer") {
        get {
            if(customerStorage.isEmpty()) {
                call.respondText("No customer found", status = HttpStatusCode.OK)
            } else {
                call.respond(customerStorage)
            }
        }
        get("{id?}") {
            val id: Int = call.parameters["id"]?.toIntOrNull() ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val customer = customerStorage.find { it.id == id } ?: return@get call.respondText(
                "No customer with id",
                status = HttpStatusCode.NotFound
            )
            call.respond(customer)
        }
        post {
            val customer = call.receive<Customer>()
            customerStorage.add(customer)
            val rep = CustomerRepository();
            rep.createCustomer(customer)
            call.response.status(HttpStatusCode.Created)
            call.respond(customer)
        }
        delete("{id?}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@delete call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            customerStorage.removeIf{ it.id == id }
            call.respondText("Customer deleted successfully", status = HttpStatusCode.OK)
        }
    }
}