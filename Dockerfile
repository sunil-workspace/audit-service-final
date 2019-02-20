FROM openjdk:8
ADD target/audit-0.0.1-SNAPSHOT.jar audit-0.0.1-SNAPSHOT.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "audit-0.0.1-SNAPSHOT.jar"]