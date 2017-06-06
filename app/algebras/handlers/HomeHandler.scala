/**
 * https://github.com/AdrianRaFo
 */
package algebras.handlers
import freestyle._
import freestyle.implicits._
import cats._
import cats.implicits._

import algebras.algebras._

import play.api._
import play.api.mvc._
import play.api.http._
import play.api.data._
import play.api.data.Forms._
import models.Models.Userdata

trait HomeHandler {
  implicit def homeHandler[M[_]](implicit MM: Monad[M]): Home.Handler[M] = new Home.Handler[M] {

    var userData: Userdata = Userdata("def@g.com", "def", None)

    val userForm = Form(
      mapping("email" -> email, "username" -> nonEmptyText, "age" -> optional(number))(Userdata.apply)(
        Userdata.unapply))

    def index: M[Form[Userdata]] =
      MM.pure(userForm)

    def user(implicit request: Request[_]): M[Option[Form[Userdata]]] =
      userForm.bindFromRequest.fold(
        formWithErrors => {
          // binding failure, you retrieve the form containing errors:
          MM.pure(Some(formWithErrors))
        },
        userDataOk => {
          /* binding success, you get the actual value. */
          userData = userDataOk
          MM.pure(None)
        }
      )

    def getUser: M[Userdata] = MM.pure(userData)

  }
}
