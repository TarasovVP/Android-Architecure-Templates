FROM jenkins/jenkins:lts

USER root

RUN apt-get update && apt-get install -y \
    openjdk-21-jdk \
    unzip \
    wget \
    curl \
    ruby \
    ruby-dev \
    build-essential \
    libffi-dev \
    libssl-dev \
    gnupg2 \
    zlib1g-dev \
    liblzma-dev \
    && apt-get clean

ENV ANDROID_SDK_ROOT=/opt/android-sdk
ENV PATH="${PATH}:${ANDROID_SDK_ROOT}/cmdline-tools/latest/bin:${ANDROID_SDK_ROOT}/platform-tools:${ANDROID_SDK_ROOT}/emulator"

RUN mkdir -p ${ANDROID_SDK_ROOT}/cmdline-tools && \
    cd ${ANDROID_SDK_ROOT}/cmdline-tools && \
    wget https://dl.google.com/android/repository/commandlinetools-linux-9477386_latest.zip -O tools.zip && \
    unzip tools.zip && \
    rm tools.zip && \
    mv cmdline-tools latest

RUN yes | ${ANDROID_SDK_ROOT}/cmdline-tools/latest/bin/sdkmanager --licenses

RUN ${ANDROID_SDK_ROOT}/cmdline-tools/latest/bin/sdkmanager \
    "platform-tools" \
    "platforms;android-33" \
    "build-tools;33.0.2" \
    "platforms;android-34" \
    "build-tools;35.0.0"

RUN gem install bundler fastlane -NV

USER jenkins


