import org.mongodb.scala.{Document, MongoCollection}

/**
  * Created by Mateusz Niedośpiał on 31.08.2017.
  */
trait Race {
  def name: String
  def age: Int
  def race: String
  def weapons: Map[String, Int]
  def friends: List[String]
}

object Race {
  def collection: MongoCollection[Document] = MongoConnection.database.getCollection("characters")
}
