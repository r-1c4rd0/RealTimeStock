# Dockerfile
FROM openjdk:17-jdk-slim AS build

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo gradle e o wrapper para o diretório de trabalho
COPY gradle gradle
COPY gradlew .
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY src src

# Execute o Gradle para compilar o projeto
RUN ./gradlew build -x test

# Etapa final da imagem
FROM openjdk:17-jdk-slim

# Copie o artefato construído da etapa anterior
COPY --from=build /app/build/libs/*.jar app.jar

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Exponha a porta que sua aplicação vai rodar
EXPOSE 8080
