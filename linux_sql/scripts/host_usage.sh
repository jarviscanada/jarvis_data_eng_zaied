#!/bin/bash

##argument variables
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

##usage data
lscpu_out=`lscpu`
hostname=$(hostname -f)
host_id=$(echo "$(psql -h localhost -U postgres -d host_agent -c "select id from host_info where hostname='$hostname'" | head -3 | tail -1 | xargs)")
timestamp=$(echo "$(vmstat -t)" | awk '{ print $18,$19 }' | tail -1 | xargs)
memory_free=$(echo "$(vmstat -t)" |  awk '{print $4}' | tail -1 | xargs)
cpu_idle=$(echo "$(vmstat -t)" | awk '{ print $15 }' | tail -1 | xargs)
cpu_kernel=$(echo "$(vmstat -t)" | awk '{print $14}' | tail -1 | xargs)
disk_io=$(echo "$(vmstat -d)" |  awk '{print $10}' | tail -1 | xargs)
disk_available=$(echo "$(df -BM)" | awk '{print $4}' | head -6 | tail -1 | egrep -o '[0-9]+' | xargs)

##executing insert command
psql -h $psql_host -U $psql_user -d $db_name -c "INSERT INTO host_usage (timestamp, host_id, memory_free, cpu_idle, cpu_kernel,disk_io, disk_available) VALUES ('$timestamp', '$host_id', '$memory_free', '$cpu_idle', '$cpu_kernel','$disk_io', '$disk_available')";
