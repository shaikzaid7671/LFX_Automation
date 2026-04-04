FROM ubuntu:22.04

# Install dependencies
RUN apt-get update && apt-get install -y \
    curl wget unzip git openjdk-17-jdk maven adb \
    && rm -rf /var/lib/apt/lists/*

# Install Node.js
RUN curl -fsSL https://deb.nodesource.com/setup_18.x | bash - \
    && apt-get install -y nodejs

# Install Appium
RUN npm install -g appium

# Install Appium driver
RUN appium driver install uiautomator2@2.29.2

# Android SDK
ENV ANDROID_HOME=/opt/android-sdk
ENV ANDROID_SDK_ROOT=/opt/android-sdk

RUN mkdir -p $ANDROID_HOME/cmdline-tools
WORKDIR $ANDROID_HOME/cmdline-tools

RUN wget https://dl.google.com/android/repository/commandlinetools-linux-11076708_latest.zip -O tools.zip \
    && unzip tools.zip \
    && mv cmdline-tools latest \
    && rm tools.zip

ENV PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools

RUN yes | sdkmanager --licenses
RUN sdkmanager "platform-tools" "platforms;android-33" "build-tools;33.0.0"

# App setup
WORKDIR /app

# Cache optimization
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
COPY testng.xml .

CMD ["bash"]
