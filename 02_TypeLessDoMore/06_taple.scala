def tupleator(a: Any, b: Any, c: Any) = (a, b, c)

/*
`(,)` は `scala.Tuple{N}` のマクロ。
N は要素数を表し、1 <= N <= 22。
したがって 23 個以上の値はグループ化できない。

scala> (1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22)
res9: (Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int) = (1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22)

scala> (1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23)
<console>:8: error: object <none> is not a member of package scala
              (1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23)

---

タプルのインスタンスは immutable で、ファーストクラスの値なので、
タプルに変数を代入したり、メソッドから返したりできる。
*/

val t = tupleator("I", "am", 22)
println(t)  // `Tuple3.toString` を呼ぶ
println(t._1)
println(t._2)
println(t._3)

val (t1, t2, t3) = t
println(t1)
println(t2)
println(t3)


/*  他の作り方
-----------------------------------------------*/
// arrow 演算子
1 -> 2

// TupleN から直接インスタンス化
Tuple2(1, 2)

// Pair
// Pair(1, 2)  // Deplicated
