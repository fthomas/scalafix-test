import cats.effect.IO
import org.http4s.HttpRoutes
import org.http4s.dsl.io._

object Http4sService {
  def service: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case _ @ GET -> Root => Ok().map(_.withEntity("hello"))
  }
}
