class Upper {
  /*
  - 可変引数は型側に `*` を付ける。
    内部的には Array[String] になる。
  - Seq[T] はコレクション。
    「パラメタ化された型」には <> ではなく [] を使う。
  - {} すら省略されることがあるので、ambiguous にならないように `=` がある。
    等号 → 関数すら値というコンセプト
  */
  def upper(strings: String*): Seq[String] = {
    // 関数リテラル(無名関数)には `=>` fat allow が使われる
    strings.map((s: String) => s.toUpperCase()) // 暗黙的な return
  }
}

val up = new Upper
Console.println(up.upper("A", "First", "Scala", "Program"))
