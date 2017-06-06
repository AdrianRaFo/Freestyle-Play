/**
 * https://github.com/AdrianRaFo
 */
package algebras

import freestyle._
import play.api._
import play.api.mvc._
import play.api.data._
import models.Models.Userdata

object algebras {
  @free trait Home {
    def index: FS[Form[Userdata]]

    def user: FS[Option[Form[Userdata]]]

    def getUser: FS[Userdata]
  }

}
