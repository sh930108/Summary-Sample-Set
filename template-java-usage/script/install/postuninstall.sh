#!/bin/sh
DIR="$( cd "$( dirname "$0" )" && pwd )"
COMDIR=$DIR/../..
echo $COMDIR
rm -rf /usr/lib/systemd/system/$1.service
systemctl daemon-reload
exit 0
