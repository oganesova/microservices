service that receives JSON messages from Kafka, 
write it to the database, I approached from the point of view of microservices architecture.
I realized a little event driven
+ CQRS, where upon receipt of a letter, a service sends a letter through a broker (Apache Kafka)
to another service and accordingly stores it in the database.
