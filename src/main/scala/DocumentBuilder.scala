import org.bson.types.ObjectId
import org.mongodb.scala.Document

/**
  * Created by Mateusz Niedośpiał on 31.08.2017.
  */
object DocumentBuilder {
  def build(): List[Document] = {
    val hobbit = Hobbit("Frodo", 18, Hobbit.stdEq, List("Sam", "Mary", "Pippin", "Gandalf", "Aragorn", "Legolas", "Gimli"))
    val hobbit2 = Hobbit("Sam", 19, Hobbit.stdEq, List("Frodo", "Mary", "Pippin", "Gandalf", "Aragorn", "Legolas", "Gimli"))
    val hobbit3 = Hobbit("Pippin", 18, Hobbit.stdEq, List("Frodo", "Sam", "Mary", "Gandalf", "Gimli", "Aragorn"))
    val hobbit4 = Hobbit("Mary", 19, Hobbit.stdEq, List("Frodo", "Sam", "Pippin", "Gandalf", "Gimli", "Aragorn"))
    val hobbit5 = Hobbit("Bilbo", 84, Hobbit.stdEq, List("Frodo", "Sam", "Pippin", "Gandalf"))
    val hobbit6 = Hobbit("Maril", 23, Hobbit.stdEq, List("Frodo", "Sam", "Pippin", "Gandalf", "Gimli", "Aragorn"))
    val hobbit7 = Hobbit("Drondo", 25, Hobbit.stdEq, List("Frodo", "Sam", "Pippin", "Gandalf", "Gimli", "Aragorn"))
    val hobbit8 = Hobbit("Gorge", 27, Hobbit.stdEq, List("Frodo", "Sam", "Pippin", "Gandalf", "Gimli", "Aragorn"))
    val hobbit9 = Hobbit("Fanfil", 30, Hobbit.stdEq, List("Frodo", "Sam", "Pippin", "Gandalf", "Gimli", "Aragorn"))
    val hobbit10 = Hobbit("Raral", 15, Hobbit.stdEq, List("Frodo", "Sam", "Pippin", "Gandalf", "Gimli", "Aragorn"))

    val elf = Elf("Legolas", 63, Elf.stdEq, List("Frodo", "Mary", "Pippin", "Gandalf", "Aragorn", "Gimli"))
    val elf2 = Elf("Arkadius", 50, Elf.stdEq, List("Frodo", "Mary", "Pippin", "Gandalf", "Aragorn", "Legolas", "Gimli"))
    val elf3 = Elf("Manily", 40, Elf.stdEq, List("Frodo", "Mary", "Pippin", "Gandalf", "Aragorn", "Legolas", "Gimli"))
    val elf4 = Elf("Fuffs", 22, Elf.stdEq, List("Frodo", "Mary", "Pippin", "Gandalf", "Aragorn", "Legolas", "Gimli"))
    val elf5 = Elf("Elundir", 70, Elf.stdEq, List("Frodo", "Mary", "Pippin", "Gandalf", "Aragorn", "Legolas", "Gimli"))
    val elf6 = Elf("Baralon", 89, Elf.stdEq, List("Frodo", "Mary", "Pippin", "Gandalf", "Aragorn", "Legolas", "Gimli"))
    val elf7 = Elf("Kukulus", 99, Elf.stdEq, List("Frodo", "Mary", "Pippin", "Gandalf", "Aragorn", "Legolas", "Gimli"))
    val elf8 = Elf("Milindir", 53, Elf.stdEq, List("Frodo", "Mary", "Pippin", "Gandalf", "Aragorn", "Legolas", "Gimli"))
    val elf9 = Elf("Funfun", 77, Elf.stdEq, List("Frodo", "Mary", "Pippin", "Gandalf", "Aragorn", "Legolas", "Gimli"))
    val elf10 = Elf("Barass", 88, Elf.stdEq, List("Frodo", "Mary", "Pippin", "Gandalf", "Aragorn", "Legolas", "Gimli"))

    val dwarf = Dwarf("Gimmly", 44, Dwarf.stdEq, List("Frodo", "Mary", "Pippin", "Gandalf", "Aragorn", "Legolas"))
    val dwarf2 = Dwarf("Monil", 55, Dwarf.stdEq, List("Frodo", "Mary", "Pippin", "Gandalf", "Aragorn", "Legolas", "Gimli"))
    val dwarf3 = Dwarf("Fifunl", 64, Dwarf.stdEq, List("Frodo", "Mary", "Pippin", "Gandalf", "Aragorn", "Legolas", "Gimli"))
    val dwarf4 = Dwarf("Boln", 70, Dwarf.stdEq, List("Frodo", "Mary", "Pippin", "Gandalf", "Aragorn", "Legolas", "Gimli"))
    val dwarf5 = Dwarf("Gilu", 46, Dwarf.stdEq, List("Frodo", "Mary", "Pippin", "Gandalf", "Aragorn", "Legolas", "Gimli"))
    val dwarf6 = Dwarf("Heler", 45, Dwarf.stdEq, List("Frodo", "Mary", "Pippin", "Gandalf", "Aragorn", "Legolas", "Gimli"))

    val characters = List[Race](
      hobbit,
      hobbit2,
      hobbit3,
      hobbit4,
      hobbit5,
      hobbit6,
      hobbit7,
      hobbit8,
      hobbit9,
      hobbit10,

      elf,
      elf2,
      elf3,
      elf4,
      elf5,
      elf6,
      elf7,
      elf8,
      elf9,
      elf10,

      dwarf,
      dwarf2,
      dwarf3,
      dwarf4,
      dwarf5,
      dwarf6
    )

    documents(characters)
  }

  private def documents(list: List[Race]): List[Document] = {
    list.map(e =>
      Document(
        "_id" -> new ObjectId(),
        "name" -> e.name,
        "age" -> e.age,
        "weapons" -> Document(e.weapons.map(w => w._1 -> w._2)),
        "friends" -> e.friends
      )
    )
  }
}
