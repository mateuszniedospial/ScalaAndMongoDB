import org.mongodb.scala.{Document, MongoCollection}

/**
  * Created by Mateusz Niedośpiał on 31.08.2017.
  */
case class Dwarf(_name: String, _age: Int, _weapons: Map[String, Int], _friends: List[String]) extends Race{
  override def name: String = _name
  override def age: Int = _age
  override def race: String = "Dwarf"
  override def weapons: Map[String, Int] = _weapons
  override def friends: List[String] = _friends
}

object Dwarf {
  def collection: MongoCollection[Document] = MongoConnection.database.getCollection("dwarves")
  def stdEq = Map("axe" -> 18, "dagger" -> 7, "spear" -> 10)
}
