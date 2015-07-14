/*=== val と var
==============================================================================================*/
// `val` は immutable な宣言
// val array: Array[String] = new Array(5)
val array = new Array[String](5)

// 配列の内容自体は変更可
array(0) = "zero"
array.update(1, "one")
println(array(0), array.apply(1))

// 別の Array を代入することはできない
// array = new Array(5)


// `var` は mutable な宣言
var stockPrice: Double = 100.0
stockPrice = 10.0
println(stockPrice)


/*=== 初期化
==============================================================================================*/
// `val` も `var` も宣言時に初期化する必要がある
// var num: Int

// ただし、コンストラクタパラメタや
class Foo(var x: Int, val a: Int)

// abstract な変数の宣言時は例外
abstract class Bar {
  val a = 1
}
class ConcreteBar extends Bar {
  override val a = 10 // `val` でも override できる
}

println((new ConcreteBar).a)
