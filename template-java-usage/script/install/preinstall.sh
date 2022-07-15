#!/bin/sh
echo $1 > instance.txt
DIR="$( cd "$( dirname "$0" )" && pwd )"
chmod -R 777 $DIR
exit 0