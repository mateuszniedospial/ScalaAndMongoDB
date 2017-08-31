import org.mongodb.scala.{Document, MongoCollection}

/**
  * Created by Mateusz Niedośpiał on 31.08.2017.
  */
case class Elf(_name: String, _age: Int, _weapons: Map[String, Int], _friends: List[String]) extends Race{
  override def name: String = _name
  override def age: Int = _age
  override def race: String = "Elf"
  override def weapons: Map[String, Int] = _weapons
  override def friends: List[String] = _friends
}

object Elf {
  def collection: MongoCollection[Document] = MongoConnection.database.getCollection("elves")
  def stdEq = Map("elvenBow" -> 16, "knife" -> 5, "sword" -> 11)
}
