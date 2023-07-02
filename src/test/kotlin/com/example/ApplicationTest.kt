package com.example

import com.example.models.Customer
import com.example.models.customerStorage
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals
import io.ktor.http.*
import com.example.plugins.*
import io.ktor.client.call.*
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation

class ApplicationTest {

    @Test
    fun testRoot() = testApplication {
        val customer = Customer(1, "aaa", "bbb", "eee")
        customerStorage.add(customer)
        client.get("/customer/1").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals(
                """{"id":1,"firstName":"aaa","lastName":"bbb","email":"eee"}""",
                bodyAsText()
            )
        }
    }
    @Test
    fun testPost() = testApplication {
        val response = client.post("/customer") {
            contentType(ContentType.Application.Json)
            setBody("""{"id":1,"firstName":"aaa","lastName":"bbb","email":"eee"}""")
        }
        assertEquals("""{"id":1,"firstName":"aaa","lastName":"bbb","email":"eee"}""", response.bodyAsText())
        assertEquals(HttpStatusCode.Created, response.status)
    }
    @Test
    fun handlingRequestValidationException() = testApplication {
        val response = client.post("/customer") {
            contentType(ContentType.Application.Json)
            setBody("""{"id":-1,"firstName":"aaa","lastName":"bbb","email":"eee"}""")
        }
        assertEquals("A customer ID should be greater than 0", response.bodyAsText())
        assertEquals(HttpStatusCode.BadRequest, response.status)
    }
}
