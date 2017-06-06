package controllers

import javax.inject._
import cats._
import cats.implicits._
import freestyle.http.play.implicits._

import play.api._
import play.api.mvc._
import play.api.http._
import play.api.data._
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.libs.json._

import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, Materializer}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext
import scala.util.{Success}

import models.Models.Userdata

import algebras.algebras._
import algebras.implicits._

class HomeController @Inject()(val controllerComponents: ControllerComponents)(implicit ec: ExecutionContext)
    extends BaseController
    with I18nSupport {

  /*def index = Action.async { _ =>
    for {
      msg <- Noop[Noop.Op].ok
    } yield Ok(msg)
  }*/

  def index = Action.async { implicit request =>
    for {
      userForm ← Home[Home.Op].index
    } yield Ok(views.html.index(userForm))
  }

  def user = Action.async { implicit request ⇒
    for {
      formResult ← Home[Home.Op].user

    } yield
      if (formResult.isEmpty)
        Redirect(routes.HomeController.getUser)
      else
        BadRequest(
          views.html
            .index(formResult.get, s"Validation error in fields: ${formResult.get.errors.map(_.key).mkString(", ")}"))
  }

  def getUser = Action.async { implicit request =>
    for {
      user ← Home[Home.Op].getUser
    } yield Ok(views.html.user(user)) //Ok(Json.toJson(user))

  }
}
