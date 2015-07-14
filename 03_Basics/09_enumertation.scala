/*  Enumeration
-----------------------------------------------*/
object Breed extends Enumeration {
  val Doberman = Value("Doberman Pinscher")
  val Yorkie   = Value("Torshire Terrier")
  val Scottie  = Value("Scottish Terrier")
  val Dane     = Value("Grate Dane")
  val Portie   = Value("Portuguese Water Dog")
}

// 列挙する
println("ID\tBreed")
for (breed <- Breed.values) printf("%d\t%s\n", breed.id, breed)

// テリアだけ
println("Terriers:")
Breed.values.filter(_.toString.endsWith("Terrier")).foreach(println)

// 型が .Value になってしまうので
val b: Breed.Value = Breed.Doberman


// 一応の対策
object Color extends Enumeration {
  // type を指定する
  type Color = Value

  val Red, Amber, Green = Value
}

import Color._      // こうすると
val c: Color = Red  // 型に `Color` を使える、名前空間を省略できる


/*  object を使う
-----------------------------------------------*/
/*
Enumeration は以下の理由で使いにくい。

- 値にメソッドを定義できない
- 個々の値は `.Value` 型として扱わならず不自然

Java の enum を使うのが簡便だが、
Scala のコードだけで同様の機能を実装するには、object を使うと良い。

> http://xerial.org/scala-cookbook/recipes/2012/06/29/enumeration/
*/

// object で定義する
object DNA {
  case object A extends DNA(0)
  case object C extends DNA(1)
  case object G extends DNA(2)
  case object T extends DNA(3)
  case object N extends DNA(4)

  // DNAの文字列をすべて並べる
  val values = Array(A, C, G, T, N)

  // 用途によって別の集合を定義することもできる
  val exceptN = Array(A, C, G, T)

  private val codeTable = Array(A, C, G, T, N, N, N, N)

  def complement(code: Int): DNA = codeTable((~code & 0x03) | (code & 0x04))
}

// sealed を付けると、DNA を拡張したクラスはこのファイル内でしか定義できない
// abstract を付けると、DNA を拡張したクラスは A, C, G, T, N 以外にないことを保証できるので
// match 文が exhaustive (すべてのケースを網羅)になる
sealed abstract class DNA(val code: Int) {
  // A, C, G, T, N を case object とすると、クラス名を表示する toString が実装される
  val name = toString

  // DNA クラスには自由にメソッドを定義できる
  def complement = DNA.complement(code)
}


// 型が `DNA` となる
val l: DNA = DNA.A

// パターンマッチが使える
l match {
  case DNA.A =>
    println("A <=> " + DNA.A.complement)  // これも動く
  case _ =>
    println("Other")
}
