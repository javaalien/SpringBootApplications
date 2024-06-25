#1: Build the Image

docker build -t docker-springboot-app .

#2: Run the Docker Container

docker run -p 8080:8080 docker-springboot-app
