package example

import java.io.ByteArrayOutputStream

import org.apache.jena.query.{Query, QueryExecution, ResultSet, ResultSetFormatter}

import scala.util.Try

case class QueryResult(executor: QueryExecution, query: Query) {
  def print(): Unit = {
    Try(executor.execSelect()).foreach(ResultSetFormatter.out(System.out, _, query))
  }

  def asJson(): Option[String] = {
    Try(executor.execSelect()).toOption.map { result =>
      val outStream = new ByteArrayOutputStream()
      ResultSetFormatter.outputAsJSON(outStream, result)
      outStream.toString
    }
  }

  def asXML(): Option[String] = {
    Try(executor.execSelect()).toOption.map(ResultSetFormatter.asXMLString)
  }

  def asCSV(): Option[String] = {
    Try(executor.execSelect()).toOption.map { result =>
      val outStream = new ByteArrayOutputStream()
      ResultSetFormatter.outputAsCSV(outStream, result)
      outStream.toString
    }
  }
}
