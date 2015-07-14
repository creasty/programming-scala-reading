/*
関数型の構文と強い型付けの使用によって Scala では例外処理の必要性は少ない。
しかし、Java との連携ではまだ広く使われている。

Scala の例外処理はただのパターンマッチとして扱われるので、
例外が多数あるときに見栄えの良い選択を行うことができる。
*/

import java.util.Calendar

val now = Calendar.getInstance

try {
  now.compareTo(null)  // null との比較で例外を発生させる
} catch {
  case e: NullPointerException =>
    println("One was null!")
  case unkown: Throwable =>  // ワイルドカードは使えない
    println("Unknown error")
} finally {
  println("It all worked out")
}


// throw のやり方は Java と同じ
try {
  throw new RuntimeException("error!")
} catch {
  case e: RuntimeException =>
    println(e)
}
