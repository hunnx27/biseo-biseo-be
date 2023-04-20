FROM amazoncorretto:11
# create & set working directory
RUN mkdir -p /usr/src
WORKDIR /usr/src

# set volume for mount
VOLUME ["/usr/src"]

ARG JAR_FILE_PATH=target/*.jar
COPY ${JAR_FILE_PATH} app.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Duser.timezone=${TZ}", "-Dfile.encoding=UTF-8" ,"-jar","app.jar"]
