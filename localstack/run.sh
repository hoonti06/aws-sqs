#!/bin/bash

cd "$(dirname "$0")"

export SERVICES=sqs
export TMPDIR=/private$TMPDIR
export DEBUG=0
docker-compose up -d
