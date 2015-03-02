# sample-groovy-mongodb-rest
Simple example of todoApp using [Spark] (http://sparkjava.com), [MongoDB] (http://www.mongodb.org), [Groovy] (http://groovy-lang.org), [Jongo] (http://jongo.org) and [Java8] (http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html)

I started to work with Mongo for the first time with nodejs, it was a great experience, but I am not a nodejs programmer (I really like javascript... Really!!!) I`m better when programming in groovy so I decided to join the best of two worlds using NoSQL (Not-Only SQL) with my favorite programming language

## This project needs to:
- Be easy to change
- Be fast
- Be readable
- Be scalable
- Have DAOs really reusable, not like SQL (`String select = "SELECT * FROM TABLE WHERE X = ?"`)

## Running with Vagrant
Within your terminal/prompt/powershell run the following commands (Ctrl + C) (Ctrl + V)
```
vagrant up
mvn compile exec:java
```

## Running on Cloud 9
Within your terminal run the following commands (Ctrl + C) (Ctrl + V)
```
wget -qO- https://gist.github.com/MartinsThiago/a7e55bbffb7391713388/raw/jdk8_precise.sh | sh
wget -qO- https://gist.github.com/MartinsThiago/7d1f3274838103327646/raw/install-maven-3.2.5.sh | sh
wget -qO- https://gist.github.com/MartinsThiago/486d297e89fbf89b0906/raw/c9_mavenrunner.sh | sh
mongod --smallfiles &
mvn compile exec:java
```
The command above installs jdk 8, maven 3.2.5, maven runner and starts mongodb
