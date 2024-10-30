# Use a imagem base do OpenJDK
FROM openjdk:17-jdk-slim AS build

# Defina o diretório de trabalho
WORKDIR /app

# Copie o wrapper do Gradle e arquivos de configuração
COPY gradlew .
COPY gradlew.bat .
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY gradle gradle

# Copie o código-fonte
COPY src ./src

# Execute o Gradle para construir o projeto
RUN chmod +x gradlew && ./gradlew build -x test

# Use uma nova imagem base para o ambiente de produção
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho
WORKDIR /app

# Copie o jar construído da fase de build
COPY --from=build /app/build/libs/*.jar app.jar

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
