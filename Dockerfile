# Base 이미지 설정
FROM gradle:jdk17 AS builder
WORKDIR /o2o

# 그래들 파일이 변경되었을 때만 새롭게 의존패키지 다운로드 받게함.
COPY build.gradle settings.gradle /o2o/
RUN gradle build -x test --parallel --continue > /dev/null 2>&1 || true

# 빌더 이미지에서 애플리케이션 빌드
COPY . /o2o
RUN gradle clean build -x test --no-daemon

# 소스 코드 복사
CMD ["java", "-jar", "build/libs/*.jar"]