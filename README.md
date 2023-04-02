## local에서 application 실행하는 방법

localstack 컨테이너 실행
```bash
$ ./localstack/run.sh
```

`sqs-consumer` application(port : 8081) , `sqs-producer` application(port : 8082) 각각 실행

`sqs-producer`의 **src.http.sqs.http**로 로컬 테스트 수행