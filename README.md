# Lift-Log-Backend
Backend for logging of weight lifting exercises.

## Launch application
./mvnw spring-boot:run

## Run tests
SPRING_PROFILES_ACTIVE=test ./mvnw test

# TODO
## Ensure all controller methods return proper http status codes
- This should be done by wrapping all return values in ResponseEntity's

## Only objects returned should be either org.springframework.hateoas.Resources or org.springframework.hateoas.Resource and with proper hateoas links
- Ensure this by writing a reflection based test?

# Notes
## Android api auth
Send token as password towards basic auth. Use custom filter to validate against google... ?
* https://stackoverflow.com/questions/22361365/spring-security-custom-token-filter
* https://stackoverflow.com/questions/21994348/spring-security-token-based-api-auth-user-password-authentication
* http://shout.setfive.com/2015/11/02/spring-boot-authentication-with-custom-http-header/

## Retrofit basic auth
* https://futurestud.io/tutorials/android-basic-authentication-with-retrofit
