# BUILD
FROM maven:3.5.2-ibmjava-9-alpine as builder
WORKDIR /build
ADD . /build
RUN mvn install -B

# RUN
FROM openjdk:9-jre
COPY --from=builder /build/dist/target/windpath-dist-1.0.0-SNAPSHOT-bin /opt
WORKDIR /opt
EXPOSE 9000
VOLUME /opt

CMD ./bin/run-windpath.sh
