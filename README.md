## local에서 application 실행하는 방법

localstack 컨테이너 실행
```bash
$ docker-compose up -d
```

localstack 컨테이너 내부 접속
```bash
$ docker exec -it localstack bash
```

localstack 컨테이너 내부에서 SQS(FIFO) 생성
```bash
$ awslocal sqs create-queue --queue-name sample.fifo \
--attributes 'FifoQueue=true'
```

`sqs-consumer` application(port : 8081) , `sqs-producer` application(port : 8082) 각각 실행

`sqs-producer`의 **src.http.sqs.http**로 로컬 테스트 수행