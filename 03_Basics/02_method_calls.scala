/*   パーレンのないメソッド
-----------------------------------------------*/
/*
Scala コミュニティの慣習では、
「副作用のない」メソッドの呼び出しにはパーレンを省略します。
*/
val list = List(1, 2, 3)
println(list.size)
// println(list.size())  // エラー

val str = "Hello"
println(str.length)
println(str.length())  // これは大丈夫


/*  ドットの省略
-----------------------------------------------*/
// 引数なしのメソッドや引数が1つのメソッド呼び出しでは、ドットも省略できる。

// postfix operator size should be enabled
// by making the implicit value scala.language.postfixOps visible.
import scala.language.postfixOps

println(list size)


def isEven(n: Int) = (n % 2) == 0
List(1, 2, 3, 4) filter isEven foreach println
