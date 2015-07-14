// オペレータが行末にあるから続行があるとみなされる
val reallySuperLongValueNameThatGoesOnForeverSoYouNeedANewLine =
  "wow that was " +
  "a long value name"

println(reallySuperLongValueNameThatGoesOnForeverSoYouNeedANewLine)

// パーレンがあればいける
val str = ("wow that was "
  + "a long value name")

println(str)

println(
  "aaa"
  + "bbb"
)
println(
  1
  + 2
)

/*
これはエラー

val str = "wow that was "
  + "a long value name"

`unary_+` と解釈されるっぽい

---

バックスラッシュでの継続もないらしい

val num = 1 \
  + 2
*/

// こういうのもいける
def foo(
  s1: String,
  s2: String  // trailing comma は受理されない
) = {
  s1 + s2
}

// 複文を一行書くには `;` を使う
println("Hey"); println("Hey")
