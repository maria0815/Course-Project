docker rm registry.heroku.com/cryptic-reaches-71111/web

docker tag photo-service registry.heroku.com/cryptic-reaches-71111/web
docker push registry.heroku.com/cryptic-reaches-71111/web

heroku container:release web -a cryptic-reaches-71111
