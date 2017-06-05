/**
 * https://github.com/AdrianRaFo
 */
package algebras

import freestyle._

object algebras {
  @free trait Noop {
    def ok: FS[String]
  }

}
