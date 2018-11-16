#FROM tomcat:8.0.20-jre8
#LABEL maintainer="j.benge@techgapsolutions.com"
#COPY target/DroolsAverage-0.0.1-SNAPSHOT.jar /usr/local/tomcat8/webapps/DroolsAverage.jar

FROM java:8
VOLUME /tmp
ADD target/DroolsAverage-0.0.1-SNAPSHOT.jar DroolsAverage.jar
ADD src/main/webapp/WEB-INF/ src/main/webapp/WEB-INF/
RUN bash -c 'touch /DroolsAverage.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/DroolsAverage.jar"]
