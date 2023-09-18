# Base 이미지 설정
FROM gradle:jdk17 AS build

# 애플리케이션을 빌드할 디렉토리 생성
WORKDIR /o2o

# 소스 코드 복사
COPY . .

# 애플리케이션 빌드
RUN gradle clean build -x Test --no-daemon

# 소스 코드 복사
CMD ["java", "-jar", "build/libs/*.jar"]