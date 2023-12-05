FROM amazoncorretto:17.0.8

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /backend

# Копируем JAR файл приложения внутрь контейнера
COPY target/hw_spring_store-0.0.1-SNAPSHOT.jar /backend/app.jar

# Установка переменных среды (можно изменить на свои параметры)
#ENV POSTGRES_URL=jdbc:postgresql://postgres:5432/secret1
#ENV POSTGRES_USER='postgres'
#ENV POSTGRES_PASSWORD='1234'

# Указываем порт, который будет использоваться приложением
EXPOSE 8090

# Команда для запуска приложения
#CMD ["java", "-jar", "hw_spring_store-0.0.1-SNAPSHOT.jar"]
CMD sleep 3 && java -jar app.jar