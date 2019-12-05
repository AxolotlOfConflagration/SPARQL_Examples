package example

import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.vocabulary.VCARD

object Examples {
  def main(args: Array[String]): Unit = {
    val personURI = "http://somewhere/JohnSmith"
    val fullName = "John Smith"

    val model = ModelFactory.createDefaultModel()
    val johnSmith = model
      .createResource(personURI)
      .addProperty(VCARD.FN, fullName)


    println(johnSmith.toString)
  }
}
