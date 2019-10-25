# Playground with @JsonProperty, @JsonAlias and @JsonCreator with Kotlin
 
```kotlin
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
``` 
 
## @JsonProperty

```bash
zoal@zoltans-macbook-pro:~|⇒  echo '{"value2":"TEST"}'  | http post :8080/json-property
HTTP/1.1 200
Content-Length: 4
Content-Type: application/json;charset=UTF-8
Date: Fri, 25 Oct 2019 09:34:48 GMT

TEST
```

## @JsonAlias

```bash
zoal@zoltans-macbook-pro:~|⇒  echo '{"value2":"TEST"}'  | http post :8080/json-alias
HTTP/1.1 400
Connection: close
Content-Type: application/json
Date: Fri, 25 Oct 2019 09:34:59 GMT
Transfer-Encoding: chunked

{
    "error": "Bad Request",
    "message": "JSON parse error: Cannot construct instance of `com.example.kotlinjacksondemo.WithJsonAlias` (although at least one Creator exists): cannot deserialize from Object value (no delegate- or property-based Creator); nested exception is com.fasterxml.jackson.databind.exc.MismatchedInputException: Cannot construct instance of `com.example.kotlinjacksondemo.WithJsonAlias` (although at least one Creator exists): cannot deserialize from Object value (no delegate- or property-based Creator)\n at [Source: (PushbackInputStream); line: 1, column: 2]",
    "path": "/json-alias",
    "status": 400,
    "timestamp": "2019-10-25T09:34:59.765+0000"
}
```

## @JsonCreator

```bash
zoal@zoltans-macbook-pro:~|⇒  echo '{"value2":"TEST"}'  | http post :8080/json-creator
HTTP/1.1 200
Content-Length: 4
Content-Type: application/json;charset=UTF-8
Date: Fri, 25 Oct 2019 09:35:18 GMT

test
```