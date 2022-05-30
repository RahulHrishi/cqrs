# kafka-publisher
Apache Kafka Publisher and cqrs using SpringBoot
version kafka -kafka_2.12-3.1.0

# start zookeeper.start bat file like below
zookeeper-server-start.bat D:\{your loc}\kafka_2.12-3.1.0\config\zookeeper.properties

# start kafka server
kafka-server-start.bat D:\{your loc}\kafka_2.12-3.1.0\config\server.properties

# Create Topic:
kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 -topic java

# Produce a message 
kafka-console-producer.bat --broker-list localhost:9092 --topic java

# Consume a message
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic java


#mongo db 

mongodb://localhost:27017

#swagger
http://localhost:9091/swagger-ui.html
