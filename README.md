# Email sender microservice

## Введение
Проект реализует отправку email сообщений, принимаемых из топика "email_message" Kafka.
## Настройки проекта
1. Настройка сервера отправки

   ```
    host: ${MAIL_HOST}
    port: ${MAIL_PORT}
    username: ${MAIL_USER}
    password: ${MAIL_PASSWORD}
   ```
2. Конфигурация подключения к Kafka

   ```
    group-id: ${KAFKA_CONSUMER_GROUP_ID}
    bootstrap-servers: ${$KAFKA_SERVER}
   ```

Упрвлением зависимостей и сборки занимается gradle. Подробнее в файле [build.gradle.kts](https://github.com/Merantory/email-sender/blob/main/build.gradle.kts)
