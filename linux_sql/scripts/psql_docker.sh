#!/bin/bash

#arg variables
command=$1
username=$2
password=$3

if [[ ! ( $"(systemctl status docker)" || $"(systemctl start docker)") ]]; then
    echo "Daemon is not running"
    exit 1
fi
if [[ $command = "create" ]]; then
    if [[ $(docker container ls -a -f name=jrvs-psql | wc -l) -ge 2 ]]; then 
        echo "jrvis-psql container is already created"
        exit 1
    fi
    if [[ ! $username ]] || [[ ! $password ]]; then 
        echo "Not enough argument. Usage: ./scripts/psql_docker.sh create db_username db_password"
        exit 1
    fi
    docker volume create pgdata
    docker run --name jrvs-psql -e POSTGRES_PASSWORD=$password -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 $username
    exit 0
fi
if [[ ! $(docker ps -a | grep "jrvs-psql") ]]; then 
   echo "jrvis-psql container is not created yet. Usage: ./scripts/psql_docker.sh create db_username db_password"
   exit 1
fi
if [[ $command = "start" ]]; then
   docker container start jrvs-psql
   #echo "Successfully started"
   exit 0
fi
if [[ $command = "stop" ]]; then
   docker container start jrvs-psql
   docker container stop jrvs-psql
   #echo "Successfully stopped"
   exit 0
fi
echo "Invalid command. Usage: ./scripts/psql_docker.sh create db_username db_password or start or stop"
exit 1
