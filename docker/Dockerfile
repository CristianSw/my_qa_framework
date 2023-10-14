FROM jenkins/jenkins:lts-jdk17

USER root

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get install -y chromium && \
    apt-get install -y chromium-driver && \
    rm -rf /var/lib/apt/lists/*

USER jenkins