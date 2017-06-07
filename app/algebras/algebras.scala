/**
 * https://github.com/AdrianRaFo
 */
package algebras

import freestyle._
import play.api.data._
import models.Models.Userdata

object algebras {
  @free trait Home {
    def index(userForm: Form[Userdata]): FS[Form[Userdata]]

    def getUser(userData: Userdata): FS[Userdata]
  }

}
