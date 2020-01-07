// https://github.com/spotify/scio/blob/master/site/src/paradox/migrations/v0.7.0-Migration-Guide.md#automated-migration

import com.google.protobuf.Message
import com.spotify.scio._
import com.spotify.scio.testing.PipelineSpec
import org.apache.avro.generic.GenericRecord
import scala.reflect.ClassTag
import com.spotify.scio.bigquery.BigQueryClient
import com.spotify.scio.avro._


object scio_0_7_0 {

  case class InputClass(s: String, i: Int) extends GenericRecord {
    def getSchema(): org.apache.avro.Schema = ???
    def get(x$1: String): Object = ???
    def put(x$1: String, x$2: Any): Unit = ???
    def get(x$1: Int): Object = ???
    def put(x$1: Int, x$2: Any): Unit = ???
  }

  case class OutputClass(result: String) extends GenericRecord {
    def getSchema(): org.apache.avro.Schema = ???
    def get(x$1: String): Object = ???
    def put(x$1: String, x$2: Any): Unit = ???
    def get(x$1: Int): Object = ???
    def put(x$1: Int, x$2: Any): Unit = ???
  }

  object TestJob

  class ValidationJobTest extends PipelineSpec {
    val inputs: List[InputClass] = (1 to 10).toList.map { i =>
      InputClass(s"s$i", i)
    }
    val inputs2 = (1 to 10).zip(inputs).toMap
    val inputs3 = inputs2.values
    val expected = List(OutputClass("result"))

    "TestJob" should "run" in {
      JobTest[TestJob.type]
        .input(AvroIO[InputClass]("current"), inputs)
        .input(AvroIO[GenericRecord]("reference"), inputs2.values)
        .input(AvroIO[InputClass]("reference2"), inputs3)
        .input(AvroIO[InputClass]("donttouch"), inputs)
        .output[OutputClass](AvroIO("foo")) { coll =>
          coll should containInAnyOrder(expected)
          ()
        }
        .run()
    }
  }

  object AddMissingImports {

    def computeAndSaveDay[M <: Message: ClassTag](sc: ScioContext): Unit = {
      sc.protobufFile[M]("input")
        .saveAsProtobufFile("output")

      sc.close()
      ()
    }
  }

  object RewriteSysProp {
    sys.props(BigQueryClient.PROJECT_KEY) = "project-key"
    sys.props(BigQueryClient.CACHE_ENABLED_KEY) = false.toString
    sys.props(BigQueryClient.PRIORITY_KEY) = "INTERACTIVE"

    val tmp = sys.props("java.io.tmpdir")
    val username = sys.props("user.name")
  }
}
