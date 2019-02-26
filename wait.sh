#!/bin/sh
# wait.sh

set -e
HOST_NAME=${1}
host=${HOST_NAME%:*}
port=${HOST_NAME#*:}
shift

cmd="$@"

while !(nc -z ${host} ${port}) ; do
    echo "Waiting ${host}:${port} to initialize..."
    sleep 2
done

>&2 echo "Executing command: ${cmd}"
exec ${cmd}
