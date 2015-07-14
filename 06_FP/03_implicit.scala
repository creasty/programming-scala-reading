/*=== Pimp My Library パターン
==============================================================================================*/
/*
ある型のインスタンスを、それと異なるが似たような方が要求されている文脈で使わなければならいことがあります。
そのようなコードが何度も出現するのであれば、自動変換の仕組みを使うのが良いでしょう。

次の例で、`implicit` キーワードは、`capitalize` や `reverse` メソッドを持っている StringOps クラスが必要になった時に
`fancyString2StringOps` メソッドを使って `FancyString` から `StringOps` へ「暗黙的に」変換できることをコンパイラに指示しています。

コンパイラは、`capitalize` メソッドの呼び出しを検知し、`StringOps` にそのようなメソッドがあることを見つけ出します。
そして、現在のスコープ内で、`FancyString` を `StringOps` に変換する暗黙の型変換メソッドを探し、`fancyString2StringOps` を見つけます。

1. オブジェクトとメソッドの組み合わせが型チェックに成功した場合は、変換は行われない。
2. implicit キーワード付きのメソッドのみが検討される。
3. 現在のスコープ内と、対象の型のコンパニオンオブジェクトで定義された暗黙の型変換メソッドのみが検討される。
4. 暗黙の型変換メソッドは、利用可能な型から中間の型を経由して対象の型へ変換するための連鎖はしない。
   利用可能な型のインスタンス1つを引数として受け取り、対象の型のインスタンスを返すメソッドのみが検討される。
5. 複数の適応可能な変換メソッドがある場合は変換は試行されない。
   ただ1つのメソッドのみが適用可能でなければならない。
*/

import scala.language.implicitConversions
import scala.collection.immutable.StringOps

class FancyString(val str: String)

// 実際は名前は何でもよい
object FancyString2StringOps {
  implicit def fancyString2StringOps(fs: FancyString) = new StringOps(fs.str)
}

import FancyString2StringOps._

val fs = new FancyString("scala")
println(fs.capitalize.reverse)


/*=== 暗黙の関数パラメタ
==============================================================================================*/
/*
 v2.8 からは、引数のデフォルト値がサポートされましたが、`implicit` キーワードを使っても同じようなことができます。
*/
def multiplier(i: Int)(implicit factor: Int) = i * factor

implicit val factor = 5

println(multiplier(2))
println(multiplier(2)(3))


/*  型クラスとしての implicit
-----------------------------------------------*/
/*
また、こんな使い方も出来ます。

> Scala の implicit parameter は型クラスの一種とはどういうことなのか
> http://nekogata.hatenablog.com/entry/2014/06/30/062342
*/

trait Flipper[T] {
  def flip(x: T): T
}
implicit object IntFlipper extends Flipper[Int] {
  def flip(x: Int) = - x
}
implicit object StringFlipper extends Flipper[String] {
  def flip(x: String) = x.reverse
}

def flip[T](x: T)(implicit flipper: Flipper[T]) = flipper.flip(x)

println(flip(1))
println(flip("string"))
