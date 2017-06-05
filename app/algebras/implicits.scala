/**
 * https://github.com/AdrianRaFo
 */
package algebras

import freestyle._
import freestyle.implicits._
import cats._
import cats.implicits._
import algebras._

object implicits {
  implicit def noopHandler[M[_]](
      implicit MM: Monad[M]
  ): Noop.Handler[M] = new Noop.Handler[M] {
    def ok: M[String] = MM.pure("success!")
  }
}
