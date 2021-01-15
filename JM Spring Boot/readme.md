включил roles.js
не работает:
- edit функция loadRolesFromUser не хочет подключаться в этом месте
- new из=за следующих ошибок:

JSON parse error: Cannot deserialize instance of `java.util.HashSet<ru.gigorv.web.models.Role>` out of VALUE_STRING token; nested exception is com.fasterxml.jackson.databind.exc.MismatchedInputException: Cannot deserialize instance of `java.util.HashSet<ru.gigorv.web.models.Role>` out of VALUE_STRING token

Resolved [org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Cannot deserialize instance of `java.util.HashSet<ru.gigorv.web.models.Role>` out of VALUE_STRING token; nested exception is com.fasterxml.jackson.databind.exc.MismatchedInputException: Cannot deserialize instance of `java.util.HashSet<ru.gigorv.web.models.Role>` out of VALUE_STRING token
 at [Source: (PushbackInputStream); line: 1, column: 98] (through reference chain: ru.gigorv.web.models.User["roles"])]

Resolved [org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Cannot construct instance of `ru.gigorv.web.models.Role` (although at least one Creator exists): no String-argument constructor/factory method to deserialize from String value ('ROLE_USER'); nested exception is com.fasterxml.jackson.databind.exc.MismatchedInputException: Cannot construct instance of `ru.gigorv.web.models.Role` (although at least one Creator exists): no String-argument constructor/factory method to deserialize from String value ('ROLE_USER')
 at [Source: (PushbackInputStream); line: 1, column: 97] (through reference chain: ru.gigorv.web.models.User["roles"]->java.util.HashSet[0])]


