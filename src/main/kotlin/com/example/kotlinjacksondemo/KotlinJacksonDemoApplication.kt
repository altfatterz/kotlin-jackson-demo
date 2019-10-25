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

    @PostMapping("/json-property")
    fun jsonProperty(@RequestBody greeting: WithJsonProperty) = greeting.value

    @PostMapping("/json-alias")
    fun jsonAlias(@RequestBody greeting: WithJsonAlias) = greeting.value

    @PostMapping("/json-creator")
    fun jsonAlias(@RequestBody greeting: WithJsonCreator) = greeting.value

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