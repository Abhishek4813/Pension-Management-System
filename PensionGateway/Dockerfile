FROM public.ecr.aws/f2h9h8g7/openjdk
ADD target/PensionGateway-0.0.1-SNAPSHOT.jar gateway.jar
ENTRYPOINT ["java", "-jar", "/gateway.jar"]