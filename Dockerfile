# Base 이미지 설정
FROM gradle:jdk17 AS build

# 애플리케이션을 빌드할 디렉토리 생성
WORKDIR /o2o

# 애플리케이션 빌드
RUN gradle clean build --no-daemon --exclude-task test

# 소스 코드 복사
COPY build/libs/*.jar app.jar

CMD ["java", "-jar", "app.jar"]