/**
 * https://github.com/AdrianRaFo
 */
package models
import play.api.mvc._
object Models {
  case class Userdata(email: String, username: String, age: Option[Int])

  implicit def queryStringBindable(implicit stringBinder: QueryStringBindable[String]) =
    new QueryStringBindable[Userdata] {
      override def bind(key: String, params: Map[String, Seq[String]]): Option[Either[String, Userdata]] = {
        for {
          email    <- stringBinder.bind("email", params)
          username <- stringBinder.bind("username", params)
          age      <- stringBinder.bind("age", params)
        } yield {
          (email, username, age) match {
            case (Right(email: String), Right(username: String), Right(age)) =>
              Right(Userdata(email, username, Some(age.toInt)))
            case _ => Left("Unable to bind an AgeRange")
          }
        }
      }
      override def unbind(key: String, userdata: Userdata): String =
        stringBinder.unbind("email", userdata.email) + "&" + stringBinder.unbind("username", userdata.username) + "&" + stringBinder
          .unbind("age", userdata.age.get.toString)
    }
}
