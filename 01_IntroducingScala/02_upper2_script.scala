/*
- object だと singleton になる。
  Java のように static はない。static フィールドは実際のインスタンスには関連付けられていない。
  Scala では「すべてがオブジェクト」であるというポリシーの一貫性を保っている。
- スレッドの安全性に問題を引き起こす変数等がないのでスレッドセーフ。
*/
object Upper {
  /*
  - `_` はプレースホルダ指示子。
    名前付きパラメタの代わりにレシーバになる。
    コンパイルしたらマクロっぽい感じだった。
    ((x$1) => x$1.toUpperCase())
  - `Console` は自動的に読み込まれているので省略可
  */
  def upper(strings: String*): Seq[String] = strings.map(_.toUpperCase())
}

// object として宣言されたものは、ランタイムに常に1つしか存在できない。
// Upper.new とは出来ない。
println(Upper.upper("A", "First", "Scala", "Program"))
