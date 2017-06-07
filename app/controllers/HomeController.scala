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

import models.Models._

import algebras.algebras._
import algebras.implicits._

class HomeController @Inject()(val controllerComponents: ControllerComponents)(implicit ec: ExecutionContext)
    extends BaseController
    with I18nSupport {

  val userForm = Form(
    mapping("email" -> email, "username" -> nonEmptyText, "age" -> optional(number))(Userdata.apply)(Userdata.unapply))
  /*def index = Action.async { _ =>
    for {
      msg <- Noop[Noop.Op].ok
    } yield Ok(msg)
  }*/

  def index = Action.async { implicit request =>
    Home[Home.Op].index(userForm) map (form ⇒ Ok(views.html.index(form)))
  }

  def user = Action { implicit request ⇒
    userForm.bindFromRequest.fold(
      formWithErrors => {
        // binding failure, you retrieve the form containing errors:
        BadRequest(
          views.html
            .index(formWithErrors, s"Validation error in fields: ${formWithErrors.errors.map(_.key).mkString(", ")}"))

      },
      userDataOk => {
        /* binding success, you get the actual value. */
        Redirect(routes.HomeController.getUser(userDataOk))
      }
    )
  }

  def getUser(userdata: Userdata) = Action.async { implicit request =>
    Home[Home.Op].getUser(userdata) map (user ⇒ Ok(views.html.user(user)))

  }
}
