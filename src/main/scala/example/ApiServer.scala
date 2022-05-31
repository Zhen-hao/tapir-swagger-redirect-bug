package example

import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter
import example.controllers.MyController

object ApiServerMain extends ApiServer

class ApiServer extends HttpServer {

  override def configureHttp(router: HttpRouter): Unit =
    router
      .add[MyController]
}
