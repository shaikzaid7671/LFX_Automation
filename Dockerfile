WORKDIR /app

# Copy only pom.xml first (for caching dependencies)
COPY pom.xml .

RUN mvn dependency:go-offline

# Then copy source code
COPY src ./src
