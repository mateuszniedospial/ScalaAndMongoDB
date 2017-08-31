import org.mongodb.scala.{MongoCollection}
import org.mongodb.scala.bson.collection.immutable.Document

/**
  * Created by Mateusz Niedośpiał on 29.08.2017.
  */
case class Hobbit(_name: String, _age: Int, _weapons: Map[String, Int], _friends: List[String]) extends Race{
  override def name: String = _name
  override def age: Int = _age
  override def race: String = "Hobbit"
  override def weapons: Map[String, Int] = _weapons
  override def friends: List[String] = _friends
}

object Hobbit {
  def collection: MongoCollection[Document] = MongoConnection.database.getCollection("hobbits")
  def stdEq = Map("hSword" -> 10, "dagger" -> 8, "bomb" -> 10)
}