import org.mongodb.scala.{MongoClient, MongoDatabase}

/**
  * Created by Mateusz Niedośpiał on 29.08.2017.
  */
object MongoConnection {
  def connection: MongoClient = MongoClient("mongodb://localhost:27017")
  def database: MongoDatabase = connection.getDatabase("lotr")
}
