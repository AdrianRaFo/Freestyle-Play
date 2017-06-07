/**
 * https://github.com/AdrianRaFo
 */
package algebras.handlers
import freestyle._
import freestyle.implicits._
import cats._
import cats.implicits._

import algebras.algebras._

import play.api.data._
import models.Models._

trait HomeHandler {
  implicit def homeHandler[M[_]](implicit MM: Monad[M]): Home.Handler[M] = new Home.Handler[M] {

    def index(userForm: Form[Userdata]): M[Form[Userdata]] =
      MM.pure(userForm)

    def getUser(userData: Userdata): M[Userdata] = MM.pure(userData)

  }
}
