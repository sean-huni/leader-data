FROM openjdk:10-jre-slim
RUN mkdir -p /app/leader-data-service/
COPY build/libs/leader-data.jar /app/leader-data-service/leader-data.jar
RUN apt-get update -y
RUN apt-get install -y vim
RUN apt-get install curl -y
RUN apt-get install -y procps
RUN apt-get install htop -y
RUN apt-get install apt-utils -y
RUN apt-get install iputils-ping -y
RUN apt-get install curl -y
RUN apt-get install net-tools -y
ENV TZ=Africa/Johannesburg
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN ln -fs /usr/share/zoneinfo/$TZ /etc/localtime && \
     dpkg-reconfigure -f noninteractive tzdata
RUN apt-get update -y
VOLUME /tmp
RUN bash -c 'touch /app/leader-data-service/leader-data.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/leader-data-service/leader-data.jar"]
EXPOSE 8003
