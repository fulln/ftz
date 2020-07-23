FROM java:8
MAINTAINER : fulln <i@fulln.me>
COPY target/ftz.jar  /opt/ftz/ftz.jar
ENV spring_profiles_active=pro
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/ftz/ftz.jar"]
