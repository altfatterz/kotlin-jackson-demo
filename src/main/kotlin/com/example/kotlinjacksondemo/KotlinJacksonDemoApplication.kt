package com.example.kotlinjacksondemo

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class KotlinJacksonDemoApplication

fun main(args: Array<String>) {
    runApplication<KotlinJacksonDemoApplication>(*args)
}

@RestController
class DemoController {

    @PostMapping("/post-json-property")
    fun postJsonProperty(@RequestBody greeting: WithJsonProperty) = greeting.value

    @PostMapping("/post-json-alias")
    fun postJsonAlias(@RequestBody greeting: WithJsonAlias) = greeting.value

    @PostMapping("/post-json-creator")
    fun postJsonAlias(@RequestBody greeting: WithJsonCreator) = greeting.value

    @GetMapping("/get-json-property")
    fun getJsonProperty() = WithJsonProperty("hello")

    @GetMapping("/get-json-alias")
    fun getJsonAlias() = WithJsonAlias("hello")

    @GetMapping("/get-json-creator")
    fun getJsonCreator() = WithJsonCreator("hello")

}

data class WithJsonProperty(@JsonProperty("value2") val value: String);

data class WithJsonAlias(@JsonAlias("value2") val value: String);

data class WithJsonCreator(val value: String) {
    companion object {
        @JvmStatic
        @JsonCreator
        fun create(@JsonProperty("value2") value2: String) = WithJsonCreator(value2.toLowerCase())
    }
}