FROM openjdk:11

WORKDIR /app

COPY . /app

RUN apt-get update && apt-get install -y maven

CMD ["mvn", "test"]
