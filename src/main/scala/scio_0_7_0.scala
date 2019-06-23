import com.spotify.scio.testing.{AvroIO, JobTest, PipelineSpec, TextIO}
import com.spotify.scio.values.SCollection
import org.apache.avro.generic.GenericRecord
import scala.reflect.ClassTag

// https://github.com/spotify/scio/blob/master/site/src/paradox/migrations/v0.7.0-Migration-Guide.md#automated-migration
object scio_0_7_0 {

  object MyScioJob {}

  val inputAUri = ""
  val inputBUri = ""
  val output = "output"

  object Schemas {
    def record1: GenericRecord = ???

    def record2: GenericRecord = ???
  }

  implicit def bo: com.spotify.scio.testing.JobTest.BeamOptions =
    ??? // XXX: why do I need this ?

  def doSomething[T: ClassTag](coll: SCollection[T]): SCollection[T] =
    coll.map { t =>
      // do something that returns a T
      val result: T = ???
      result
    }

  class MyScioJobTest extends PipelineSpec {

    "MyScioJob" should "work" in {
      JobTest[MyScioJob.type]
        .args(s"--inputAUri=${inputAUri}")
        .args(s"--inputBUri=${inputBUri}")
        .input(AvroIO[GenericRecord](inputAUri), Seq(Schemas.record1))
        .input(AvroIO[GenericRecord](inputBUri), Seq(Schemas.record2))
        .output(TextIO(output)) { coll =>
          coll should haveSize(1)
          ()
        }
        .run()
    }

    // more tests
  }

}
