FROM public.ecr.aws/f2h9h8g7/openjdk
ADD target/Authorization-Microservice-0.0.1-SNAPSHOT.jar auth.jar
ENTRYPOINT ["java", "-jar", "/auth.jar"]