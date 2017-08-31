# Scala And MongoDB 

This repository contains a simple implementation
of operations which one could do using Scala MongoDB Driver.

Examples cover:
+ getting a connection (@see MongoConnection, Test)
+ creating DB/Collections (@see MongoConnection, Test)
+ inserting documents,
+ updating documents,
+ finding documents,
using sample Filters, Projections, Sorts, Updates.

Additional functions for printing results are used.
(@see Helpers)

The code is highly inspired/based on QuickTours from:
https://github.com/mongodb/mongo-scala-driver/blob/master/examples/src/test/scala/tour/ [Aug 2017]

Helpers object is imported from the tour package and is provided by authors of the driver:
https://github.com/mongodb/mongo-scala-driver/blob/master/examples/src/test/scala/tour/Helpers.scala [Aug 2017]

Please note that for this code to work additional tools are needed:
+ MongoDB
+ MongoDB Scala Driver
+ Mongo Plugin for IntelliJ Idea (not required)

The MongoDB must be started.
Also make sure that correct port is passed during acquiring connection:
The connection is made inside MongoConnection object.
In this example default settings were used:
" mongodb://localhost:27017 ".

The readme is a copy of comment written above implementation of Test object which contains main method.
