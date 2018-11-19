FROM java:8
MAINTAINER (justin benge) <j.benge@techgapsolutions.com>
VOLUME /tmp
ADD target/DroolsAverage-0.0.1-SNAPSHOT.jar DroolsAverage.jar
ADD src/main/webapp/WEB-INF/ src/main/webapp/WEB-INF/
RUN bash -c 'touch /DroolsAverage.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/DroolsAverage.jar"]
