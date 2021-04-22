docker build -t photo-service .

docker stop photo-service
docker rm photo-service

docker run -d ^
    --name photo-service ^
    -e "LOG_LEVEL=TRACE" ^
    -p 8081:8080 photo-service