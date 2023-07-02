package com.example.models

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.long
import org.ktorm.schema.varchar

interface CustomerEntity: Entity<CustomerEntity> {
    companion object : Entity.Factory<CustomerEntity>()

    val id: Long?
    var firstName: String;
    var lastName: String;
    var email: String
}

object Customers : Table<CustomerEntity>("customer") {
    val id = long("id").primaryKey().bindTo(CustomerEntity::id)
    val first_name = varchar("first_name").bindTo(CustomerEntity::firstName)
    val last_name = varchar("last_name").bindTo(CustomerEntity::lastName)
    val email = varchar("email").bindTo(CustomerEntity::email)
}
