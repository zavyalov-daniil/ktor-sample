package com.example

import com.example.models.Customer
import com.example.models.customerStorage
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.testing.*
import kotlin.test.*
import io.ktor.http.*
import com.example.plugins.*
import io.ktor.client.call.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        val customer = Customer("First", "aaa", "bbb", "eee")
        customerStorage.add(customer)
        client.get("/customer/First").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals(
                """{"id":"First","firstName":"aaa","lastName":"bbb","email":"eee"}""",
                bodyAsText()
            )
        }
    }
}
