package example.controllers

import sttp.tapir._
import sttp.tapir.json.circe._
import sttp.tapir.server.finatra.FinatraServerInterpreter
import com.twitter.util.Future
import io.circe.generic.auto._
import sttp.tapir.generic.auto._
import sttp.tapir.swagger.bundle.SwaggerInterpreter

object MyRoute {

  // endpoint descriptions
  case class Author(name: String)
  case class Book(title: String, year: Int, author: Author)

  val booksListing: PublicEndpoint[Unit, Unit, Vector[Book], Any] = endpoint.get
    .in("books")
    .in("list" / "all")
    .out(jsonBody[Vector[Book]])

  val finatraServerInterpreter = FinatraServerInterpreter()

  val swaggerUIRoutes = SwaggerInterpreter()
    .fromEndpoints[Future](List(booksListing), "demo", "1.0")
    .map(finatraServerInterpreter.toRoute)

}
