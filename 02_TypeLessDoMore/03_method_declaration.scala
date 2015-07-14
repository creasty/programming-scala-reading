/*=== 等号の有無
==============================================================================================*/
/*
メッソドを宣言するときに、`def foo =` のように `=` を付けないと、
コンパイル時に自動的に `:Unit` が挿入される。
したがって返り値が `Unit` 以外の時は、`=` を使わなければならない。

関数型プログラミングにおいて関数は常に値を返す。
そのため、Scala で等号の無いメソッドは「手続き」のために宣言されたとみなされる。
*/

// 誤:
def square1(x: Int) { x * x }  // シグニチャは (Int)Unit
println(square1(4))  // 出力される `()` は Unit のシングルトンインスタンスの名前 (関数型プログラミングの慣習に由来)

// 正:
def square2(x: Int) = x * x  // シグニチャは (Int)Int
def square3(x: Int) = println(x)
def square4(x: Int) { println(x) }


// また、等号とメソッドの本体を省略すると暗黙的に「抽象」として宣言される。
abstract class Bar { def bar(x: Int) }


/*=== パーレンの省略
==============================================================================================*/
// メソッド定義時のパーレンは引数を取らないときには省略できるが、コール時の挙動が変わる。

def f1(): Int = 1
println(f1)
println(f1())

def f2: Int = 2
// println(f2())  // 何故かできない
println(f2)


/*=== 可変長引数
==============================================================================================*/
// 可変引数は型側に `*` を付ける。内部的には Array[String] になる
def test(strings: String*) = println(strings)
test("hello", "world")


/*=== デフォルト引数と名前付き引数
==============================================================================================*/
object StringUtil {
  // ポリモーフィックに宣言
  def joiner(strings: List[String], separator: String): String =
    strings.mkString(separator)
  def joiner(strings: List[String]): String = joiner(strings, " ")

  // v2.8 からの方法
  def joiner2(strings: List[String], separator: String = " "): String =
    strings.mkString(separator)
}

import StringUtil._

println(joiner(List("Hello", "world")))
println(joiner(List("Hello", "world"), ", "))

println(joiner2(List("Hello", "world")))
println(joiner2(List("Hello", "world"), ", "))

/*  名前付きで呼び出す
-----------------------------------------------*/
println(joiner2(strings = List("Hello", "world")))
println(joiner2(List("Hello", "world"), separator = ", "))
println(joiner2(strings = List("Hello", "world"), separator = ", "))
println(joiner2(strings = List("Hello", "world"), ", "))
println(joiner2(separator = ", ", strings = List("Hello", "world")))

// 名前付きパラメータが1つでもあると、
// その後に現れるパラメタも名前付きでなければならない
// println(joiner2(separator = ", ", List("Hello", "world")))

/*  コンストラクタでのデフォルト引数
-----------------------------------------------*/
object User {
  val UnknownName = "Anonymous"
  val UnknownAge  = -1
}
class User(
  name: String = User.UnknownName,
  age: Int     = User.UnknownAge
) {
  override def toString() = s"User(name = ${this.name}, age = ${this.age})"
}

println(new User)
println(new User(age = 22))

/*  デフォルト引数に式を使う
-----------------------------------------------*/
def defaultMe(str: String = "str" + "ing", num: Int = 1 + 1) = println(str, num)
defaultMe()


/*=== メソッドのネスト
==============================================================================================*/
def factorial(i: Int): Int = {
  def fact(i: Int, accumulator: Int = 1): Int = {  // `fact` ローカルの i
    if (i <= 1)
      accumulator
    else
      fact(i - 1, i * accumulator)
  }

  fact(i)
}

println(factorial(10))
// println(fact(10))  // 不可視

def countTo(n: Int) {
  def count(i: Int) {
    if (i <= n) {  // n は可視
      println(i)
      count(i + 1)
    }
  }

  count(1)
}

countTo(5)
