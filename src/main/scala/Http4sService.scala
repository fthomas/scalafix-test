import cats.effect.IO
import org.http4s.HttpService
import org.http4s.dsl.io._

object Http4sService {
  def service: HttpService[IO] = HttpService[IO] {
    case _ @ GET -> Root => Ok().withBody("hello")
  }
}
