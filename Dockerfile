#образ взятый за основу
FROM openjdk:17
#Записываем в переменную путь до варника (необязательно)
ARG jarFile=target/SafeSummit-0.0.1-SNAPSHOT.jar
#Куда переместить варник внутри контейнера
WORKDIR /opt/app
#копируем наш джарник в новый внутри контейнера
COPY ${jarFile} SafeSummit.jar
#Открываем порт
EXPOSE 7070
#Команда для запуска
ENTRYPOINT ["java", "-jar", "SafeSummit.jar"]
