#!/bin/sh

set -e
cmd=$@

while !(nc -z mongo 27017) ; do
    echo "Waiting for mongo to initialize..."
    sleep 2
done

exec ${cmd}
