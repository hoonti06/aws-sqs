#!/bin/bash

awslocal sqs create-queue --queue-name sample.fifo \
--attributes 'FifoQueue=true'

awslocal sqs create-queue --queue-name test-order-queue

echo "Initialized."