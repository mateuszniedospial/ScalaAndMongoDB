import org.bson.types.ObjectId

/**
  * Created by Mateusz Niedośpiał on 31.08.2017.
  */
case class Human(_id: ObjectId, name: String, age: Int) {}

object Human {
  def apply(name: String, age: Int): Human = {
    Human(new ObjectId(), name, age)
  }
}