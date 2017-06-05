package controllers

import javax.inject._
import freestyle._
import freestyle.implicits._
import cats._
import cats.implicits._
import freestyle.http.play.implicits._

import play.api.mvc._
import play.api.http._

import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, Materializer}

import scala.concurrent.ExecutionContext

class HomeController @Inject()(val controllerComponents: ControllerComponents)(implicit ec: ExecutionContext)
    extends BaseController {

  import algebras.algebras._
  import algebras.implicits._

  def program[F[_]: Noop]: FreeS[F, Result] =
    for {
      msg <- Noop[F].ok
    } yield Results.Ok(msg)

  def index = Action.async { _ =>
    program[Noop.Op]
  }

}
