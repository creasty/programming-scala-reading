/*  while / do-while
-----------------------------------------------*/
var count = 0

while (count < 10) {
  println(count)
  count += 1
}


do {
  println(count)
  count += 1
} while (count < 10)


/*  ジェネレータ式
-----------------------------------------------*/
for (i <- 1 to 10) println(i)

// 暗黙の型変換により、`1` は `RichInt` に変換される。
// `RichInt.to` は `Range.Inclusive` のインスタンスを返す
println(1 to 10)

// until もある
println(1 until 10)


/*
Scala には、break や continue 相当の命令文を持っていません。
条件式あるいは再帰を使用します。
できれば、フィルタを用いて前もって取り除いてください。

v2.8 からは、組み込みキーワードではなく、ライブラリとして break をサポートするようになりました。
*/
