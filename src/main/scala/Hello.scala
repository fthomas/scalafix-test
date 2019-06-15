import cats.effect.IO
import fs2.StreamApp.ExitCode
import cats.effect.{ ExitCode, IOApp }
import cats.syntax.functor._

object Hello extends IOApp {
  override def run(args: List[String] ): IO[ExitCode] =
    fs2.Stream(ExitCode.Success).covary[IO].compile.drain.as(ExitCode.Success)
}
