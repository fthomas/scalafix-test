/*
import cats.effect.IO
import fs2.StreamApp
import fs2.StreamApp.ExitCode

object Hello extends StreamApp[IO] {
  override def stream(args: List[String], requestShutdown: IO[Unit]): fs2.Stream[IO, ExitCode] =
    fs2.Stream(ExitCode.Success).covary[IO]
}
 */
