val num = 1

if (num == 0) {
  println("Nothing")
} else if (num < 4) {
  println("A few")
} else {
  println(num)
}


// 単文の時はブレースを省略可
if (num == 0)
  println("Nothing")
else if (num < 4)
  println("A few")
else
  println(num)


// if も式である
val result = if (num == 0) {
  "Nothing"
} else if (num < 4) {
  "A few"
} else {
  num.toString
}

println(result)


/*
なお、if が式であり値を持つので、Scala には三項演算子が存在しない。
*/

val q1 = if (false) "aaa"
val q2 = if (false) "aaa" else if (false) "bbb"
println(q1)  // `()` ということは `Unit`
println(q2)

/*
型推論は if 式のすべての結果で動作する型を選択する。
値を持たない結果がありうる場合、`Unit` になる。
*/
