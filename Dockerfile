FROM ubuntu:22.04

ENV DEBIAN_FRONTEND=noninteractive

# Install dependencies
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven \
    nodejs \
    npm \
    adb \
    curl \
    wget \
    unzip \
    git \
    && rm -rf /var/lib/apt/lists/*

# Install Appium
RUN npm install -g appium

# Verify installations
RUN java -version && mvn -version && appium -v

# Set JAVA_HOME
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH

# Working directory
WORKDIR /app

# Copy project
COPY . /app

# Expose Appium port
EXPOSE 4723

# Default command (can be overridden by Jenkins)
CMD ["bash"]
