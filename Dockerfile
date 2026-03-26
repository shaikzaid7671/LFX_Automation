FROM ubuntu:22.04

ENV DEBIAN_FRONTEND=noninteractive

# Install basic tools
RUN apt-get update && apt-get install -y \
    curl \
    wget \
    unzip \
    git \
    openjdk-17-jdk \
    maven \
    adb \
    && rm -rf /var/lib/apt/lists/*

# 🔥 Install Node.js 18 (IMPORTANT FIX)
RUN curl -fsSL https://deb.nodesource.com/setup_18.x | bash - \
    && apt-get install -y nodejs

# Verify node version
RUN node -v && npm -v

# Install Appium
RUN npm install -g appium

# Install compatible driver
RUN appium driver install uiautomator2@2.29.2

# Set JAVA_HOME
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH

# Working directory
WORKDIR /app

# Copy project
COPY . /app

# Expose Appium port
EXPOSE 4723

CMD ["bash"]
