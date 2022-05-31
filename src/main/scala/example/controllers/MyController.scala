package example.controllers

import com.twitter.finatra.http.Controller
import sttp.tapir.server.finatra._

class MyController extends Controller with TapirController {

  MyRoute.swaggerUIRoutes.foreach(addTapirRoute)

}
