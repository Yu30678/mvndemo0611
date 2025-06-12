# 第一階段：用來編譯 Java 專案
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

# 複製 pom.xml 並下載 dependencies（避免每次 build 都重新下載）
COPY pom.xml .
RUN mvn dependency:go-offline

# 複製所有程式碼並打包
COPY src ./src
RUN mvn clean package -DskipTests

# 第二階段：只留下 JAR 和 JRE
FROM eclipse-temurin:21-jre

WORKDIR /app

# 複製 jar
COPY --from=builder /app/target/*.jar app.jar

# Railway 預設會注入 PORT 環境變數
ENV PORT=8080
EXPOSE 8080

# 執行
ENTRYPOINT ["java", "-jar", "app.jar"]