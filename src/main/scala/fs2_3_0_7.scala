import fs2.Stream

class fs2_3_0_7 {
  Stream.emits("Hello".getBytes).through(fs2.text.utf8Decode)
}
