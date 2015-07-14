/*
ほとんどの言語では、参照するものがないときに null といったキーワードが使われるが、
null に対してメソッドを呼ぶことはできず混乱を招く設計になっている。

すべてのものをオブジェクトにするポリシーと関数型プログラミングの慣習に従うため、
Scala では値を参照しない可能性がある場合 Option 型を使うことが推奨される。

Option > (Some, None)
*/

val colors = Map(
  "Apple"  -> "Red",
  "Banana" -> "Yellow",
  "Peach"  -> "Pink"
)

println(colors.get("Apple"))  // Some
println(colors.get("Banana").get)  // `Option.get`: 実際の値を unwrap する

println(colors.get("Bacon"))
// println(colors.get("Bacon").get)  // None に対して `.get` すると例外が起きる
println(colors.get("Bacon").getOrElse("Unknown"))

println(colors.get("Peach").getOrElse("Unknown"))


/*
`scala.collection.immutable.HashMap` の `get` の実装 (v2.7):

def get(key: A): Option[B] = {
  if (contains(key))
    new Some(getValue(key))
  else
    Nonw
}
*/
