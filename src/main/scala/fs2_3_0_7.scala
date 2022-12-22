import fs2.Stream
import fs2.text

class fs2_3_0_7 {
  Stream.emits("Hello".getBytes).through(text.utf8.decode)
}
