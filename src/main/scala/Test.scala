import org.bson.types.ObjectId
import org.mongodb.scala.bson.collection.immutable.Document
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Projections._
import org.mongodb.scala.model.Sorts._
import org.mongodb.scala.model.Updates._
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.mongodb.scala.MongoCollection

import Helpers._

/**
  * Created by Mateusz Niedośpiał on 29.08.2017.
  *
  * The following code is a simple implementation
  * of operations which one could do using Scala MongoDB Driver.
  *
  * Examples cover:
  * + getting a connection (@see MongoConnection, Test)
  * + creating DB/Collections (@see MongoConnection, Test)
  * + inserting documents,
  * + updating documents,
  * + finding documents,
  * using sample Filters, Projections, Sorts, Updates.
  *
  * Additional functions for printing results are used.
  * (@see Helpers)
  *
  * The code is highly inspired/based on QuickTours from:
  * https://github.com/mongodb/mongo-scala-driver/blob/master/examples/src/test/scala/tour/
  *
  * Helpers object is imported from the tour package and is provided by authors of the driver:
  * https://github.com/mongodb/mongo-scala-driver/blob/master/examples/src/test/scala/tour/Helpers.scala
  *
  * Please note that for this code to work additional tools are needed:
  * + MongoDB
  * + MongoDB Scala Driver
  * + Mongo Plugin for IntelliJ Idea (not required)
  *
  * The MongoDB must be started.
  * Also make sure that correct port is passed during acquiring connection:
  * The connection is made inside MongoConnection object.
  * In this example default settings were used:
  * " mongodb://localhost:27017 ".
  */
object Test {
  def main(args: Array[String]): Unit = {

    //One entity to put into collection:
    val hobbit = Hobbit("Frodo", 18, Hobbit.stdEq, List("Sam", "Pippin", "Mary", "Gandalf", "Aragorn", "Legolas"))
    val doc: Document = Document(
      "_id" -> new ObjectId(),
      "name" -> hobbit.name,
      "age" -> hobbit.age,
      "friends" -> hobbit.friends,
      "weapons" -> Document(
        hobbit.weapons.map(w => w._1 -> w._2))
    )

    /**
      * Using helper method from tour package (mongodb scala driver master branch under examples directory:
      * https://github.com/mongodb/mongo-scala-driver/blob/master/examples/src/test/scala/tour/Helpers.scala
      */

    //Collection: 'hobbits'
    Hobbit.collection.insertOne(doc)

    //To print found entity:
    Hobbit.collection.find.first().printResults("Results: ")
    Hobbit.collection.find.first().printHeadResult("HeadResults: ")


    /**
      * Approach using subscribe method:
      */
    //    insertion.subscribe(new Observer[Completed] {
//      override def onNext(result: Completed): Unit = println("Inserted.")
//      override def onError(e: Throwable): Unit = println("Failed: " + e)
//      override def onComplete(): Unit = println("Completed.")
//    })


    /**
      * Approach using future and Await.result:
      */
    //    val f: Future[Completed] = insertion.toFuture()
//    Await.result(f, Duration.Inf)

    /**
      * Insert many:
      */
    val documents: List[Document] = DocumentBuilder.build()
    Race.collection.insertMany(documents).results()

    Race.collection.find().first().printHeadResult("Result: ")

    //Find all characters which have "Sam" as a friend:
    Race.collection.find(equal("friends", "Sam")).results()

    //Find all characters which have "Frodo" or "Sam" as a friends:
    Race.collection.find(or(equal("friends", "Frodo"), equal("friends", "Sam")))

    //Projections: exclude ID:
    Race.collection.find().projection(excludeId()).printHeadResult()

    //Projections: include (name, age, friends), exclude others by default:
    Race.collection.find().projection(include("name", "age", "friends")).printHeadResult()

    //Aggregations: skip/limit/sample
    Race.collection.find().limit(5).printResults()
    Race.collection.find().skip(5).printResults()
    //Race.collection.find(sample(5)).printHeadResult()
    Race.collection
      .find()
      .sort(orderBy(ascending("age"), descending("name")))
      .limit(10)
      .printResults()


    //Updates:

    Race.collection
      .updateOne(equal("name", "Frodo"), set("age", 40))
      .printResults()

    Race.collection
      .updateOne(equal("name", "Frodo"), push("race", "Hobbit"))
      .printResults()

    Race.collection
      .updateMany(gt("age", 20), push("pushed:", "true"))
      .printResults()

    Race.collection
      .deleteMany(lt("age", 18))
      .printResults()


    //Drop:

    MongoConnection
      .connection
      .getDatabase("lotr")
      .getCollection("characters")
      .drop()
      .printResults()

    //Resource close:

    MongoConnection.connection.close()

    /**
      * One can also use case classes to
      * create entities on collection:
      * i.e Human
      */

    val humans: List[Human] =
      List(
        Human("Aragorn", 56),
        Human("Boromir", 33),
        Human("Eowena", 22)
      )

    //Using codes registry to specify Human:
    val codecRegistryForHumans = fromRegistries(fromProviders(classOf[Human]), DEFAULT_CODEC_REGISTRY)
    val dbHumans = MongoConnection.connection.getDatabase("humans").withCodecRegistry(codecRegistryForHumans)
    val collection: MongoCollection[Human] = dbHumans.getCollection("entities")

    collection.insertMany(humans).printResults()
    collection.insertOne(Human("Hannah Barbara", 55)).printResults()

    collection.find().printResults()

    collection.deleteOne(equal("name", "Hannah Barbara")).printResults()

    collection.find().printResults()

    MongoConnection
      .connection
      .getDatabase("humans")
      .getCollection("entities")
      .drop()
      .printResults()

    MongoConnection
      .connection
      .getDatabase("humans")
      .drop()
      .printResults()

    MongoConnection.connection.close()
  }
}
