This project demonstrates a (possible) bug in ebean when having multiple many to many relations and using .fetch(...)
to prefetch all these relations. When .fetch(...) is not used, it works as expected.

The relations are as follows:

```
Level1 --- Level4
  |
  |
  |
Level2 --- Level3
```

To run the test just type
```
mvn test
```

When making changes I recommend to run
```
mvn clean test
```
