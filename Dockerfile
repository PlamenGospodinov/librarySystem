FROM eclipse-temurin:17-jre
COPY target/librarysystem*.jar librarysystem.jar
ENTRYPOINT exec java $JAVA_OPTS -jar /librarysystem.jar $ARGS