FROM public.ecr.aws/f2h9h8g7/openjdk
ADD target/processPensionMicroservice-0.0.1-SNAPSHOT.jar process.jar
ENTRYPOINT ["java", "-jar", "/process.jar"]