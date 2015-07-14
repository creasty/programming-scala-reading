// ちなみに、トレイトなのは、以下の `value` の値が初期化されていなので、
// 抽象である必要があり、`new` するためには `abstract class` ではダメだから。
trait Test {
  println("Test")

  val value: Int

  // `inverse` が実際に使用されるまで遅延され、必要になった最初の時に1回だけ評価される
  lazy val inverse = { println("inverse"); 1.0 / value }
}

val t = new Test {
  println("t")
  val value = 10
}

println("---")
t.inverse
t.inverse
