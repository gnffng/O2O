# Base 이미지 설정
FROM gradle:jdk17 AS builder
WORKDIR /build

# 그래들 파일이 변경되었을 때만 새롭게 의존패키지 다운로드 받게함.
COPY build.gradle settings.gradle /build/
RUN gradle clean build -x Test --no-daemon --parallel --continue > /dev/null 2>&1 || true

# 빌더 이미지에서 애플리케이션 빌드
COPY . /build
RUN gradle build -x test --parallel

# 애플리케이션을 빌드할 디렉토리 생성
FROM gradle:jdk17 AS build
WORKDIR /o2o
# 빌더 이미지에서 jar 파일만 복사
# COPY --from=builder /build/build/libs/*-SNAPSHOT.jar ./app.jar
COPY --from=builder /build/build/libs/*-SNAPSHOT.jar app.jar

# 소스 코드 복사
CMD ["java", "-jar", "app.jar"]