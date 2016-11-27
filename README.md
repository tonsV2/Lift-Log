# Lift-Log-Backend
Backend for logging of weight lifting exercises.

## Launch application
./mvnw spring-boot:run

## TODO
# Ensure all controller methods return proper http status codes
This should be done by wrapping all return values in ResponseEntity's

# Only objects returned should be either org.springframework.hateoas.Resources or org.springframework.hateoas.Resource and with proper hateoas links
