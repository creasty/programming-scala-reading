import java.util.Map
import java.util.HashMap

val intToString1: Map[Int, String] = new HashMap
val intToString2 = new HashMap[Int, String]

/*
実用的には、次の状況で明示的な type annotation が必要になる。

1. 変数宣言。
   ただし、初期化している場合は例外。
   `val name = "John"`
2. メソッド引数。
   `def foo(x: Int)`
3. 次の場合の返り値
  a. 明示的に `return` を呼ぶとき
  b. 再帰するとき
  c. 多重定義されたメソッドの1つが別の多重定義されたメソッドを呼ぶとき。
     (呼び出し側のメソッドに返り値の type annotation が必要)
  d. 推論で Upcast されるとき
*/


/*  3.a の例
-----------------------------------------------*/
def usingReturn(s: String): String = { // 返り値の type annotation がないとコンパイルできない
  if (s.length == 0)
    return s  // (3.a)
  else
    s.toUpperCase()
}

/*  3.b の例
-----------------------------------------------*/
def factorial(i: Int) = {  // ここの annotation は省略可
  def fact(i: Int, accumulator: Int = 1): Int = {  // ここは必要
    if (i <= 1)
      accumulator
    else
      fact(i - 1, i * accumulator)  // (3.b)
  }

  fact(i)
}

/*  3.c の例
-----------------------------------------------*/
object StringUtil {
  // 返り値型は省略可
  def joiner(strings: List[String], separator: String) = strings.mkString(separator)

  // こっちには必要
  def joiner(strings: List[String]): String = joiner(strings, " ")  // (3.c)
}

/*  3.d の例
-----------------------------------------------*/
// コンパイル時にはエラーにならない
def makeList(strings: String*) = {
  if (strings.length == 0)
    // わざと `List[Int]` にしておく。
    //
    // List()
    // List("")
    //
    // なら問題ない
    List(0)
  else
    strings.toList  // List[String]
}

// `makeList()` は `List[Int]` と `List[String]` の最も近いスーパクラスの `List[Any]` に推論される。
// `List[Any]` に対し `List[String]` を要求しているので実行時にエラーとなる。
// val list: List[String] = makeList()
