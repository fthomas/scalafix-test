
import fs2.textclass fs2_3_0_7 {
  fs2.Stream.emits("Hello".getBytes).through(text.utf8.decode)
}
