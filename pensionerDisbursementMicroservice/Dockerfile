FROM public.ecr.aws/f2h9h8g7/openjdk
ADD target/pensionerDisbursementMicroservice-0.0.1-SNAPSHOT.jar disburse.jar
ENTRYPOINT ["java", "-jar", "/disburse.jar"]