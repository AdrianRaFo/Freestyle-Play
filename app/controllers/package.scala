/**
 * https://github.com/AdrianRaFo
 */
import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._
import models.Models.Userdata

package object controllers {

  implicit def conv[T](a: T): Option[T] = Some(a)
  implicit val userdataFormat: Format[Userdata] = (
    (JsPath \ "email").format[String](email) and
      (JsPath \ "username").format[String] and
      (JsPath \ "age").formatNullable[Int]
  )(Userdata.apply _, unlift(Userdata.unapply _))

}
