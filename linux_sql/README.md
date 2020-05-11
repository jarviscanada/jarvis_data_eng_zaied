# Linux Cluster Monitoring Agent

## Introduction
The Jarvis Linux Cluster Administration (LCA) team manages a Linux cluster of 10 nodes/servers which are running CentOS 7. These servers are internally connected through a switch and able to communicate through internal IPv4 addresses.The LCA team needs to record the hardware specifications of each node and monitor node resource usages (e.g. CPU/Memory) in realtime (see appendix A). The collected data should be stored in an RDBMS database. LCA team will use the data to generate some reports for future resource planning purposes (e.g. add/remove servers). This project implement an MVP solution to help the LCA tem to meet their business needs. Being a MVP solution, this implementation invloves single linux machine instead of cluster.

## Overview
This solution is a Cluster monitoring agent. It will monitor the hardware specification and hardware usage in each of the node machines and store in the instance. Server will be able to query and extract important usage information. Being an MVP Solution, in this case, both server and node is the same machine. PostgreSQL is used as the database to store hardware usage and specifications. This solution will provide the necessary scripts to create, start and stop a Postgresql instance in a docker container. It will also provide necessary scripts to extract and store hardware specifications and usage data in the PostgreSQL instance. Futhermore, it will also provide sql files to create and populate database table and perform some sample queries.

![cluster image](./assets/cluster_image.png)  
## Architecture
* A PostgreSQL instance is used to store all data
* ./scripts directory has all the neccessary scripts about hardware
	* host_info.sh: script to extract necessary hardware specifications and insert into the host_info table instance
	* host_usage.sh: script to extract real-time hardware usage info and insert into the host_usage table in the instance
	* psql_docker.sh: sctipt to create, start, stop the instance in a docker container
* ./sql directory has all the neccessary script to create database table and perform query
	* ddl.sql: creates both host_info and host_usage table in the database
	* queries.sql: consists some sample queries to perform on the stored data
## Usage
* Provisioning PostgreSQL instance
Create and start the docker container which will run a PostgreSQL instance
```
./linux_sql/psql_docker.sh create db_username db_password
./linux_sql/psql_docker.sh start
```
* Create `host_agent` database  using psql CLI 
```
psql -h localhost -U postgres -W
postgres=# CREATE DATABASE host_agent;
``` 
* How to start the PostgreSQL instance?\
--> In command-line write:  bash scripts/psql_docker start
* How to create tables?\
--> In command-line write:  psql -h localhost -U postgres -W -d host_agent -f sql/ddl.sql
* How to insert hardware specifications into corresponding table?\
--> In command-line write:  bash scripts/host_info.sh psql_host psql_port db_name psql_user psql_password (Sample Usage: bash scripts/host_info.sh localhost 5432 host_agent postgres password
* How to insert hardware usages into corresponding table?\
--> In command-line write:  bash scripts/host_usage.sh psql_host psql_port db_name psql_user psql_password (Sample Usage: bash scripts/host_usage.sh localhost 5432 host_agent postgres password
The hardware usage data needs to be extracted continuously. To achieve that crontab needs to be set up.
* How to setup crontab?\
--> In command-line write: crontab -e\
--> then add this to the opened file: * * * * * bash /home/centos/dev/jrvs/bootcamp/linux_sql/host_agent/scripts/host_usage.sh localhost 5432 host_agent postgres password > /tmp/host_usage.log

## Improvements
* Handle Hardware Update
* Filter out unnecessary Hardware Usage data
* Make the queries more performance efficient  

