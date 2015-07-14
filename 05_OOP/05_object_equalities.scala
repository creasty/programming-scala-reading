val v1 = 123
val v2 = 123

val l1 = List(1, 2)
val l2 = List(1, 2)
val nullList: List[Int] = null


/*=== 値の等価性: equals メソッド
==============================================================================================*/
/*
equals は、Java の equals や Ruby の eql? と同じで、値の等価性を検査します。
`==` メソッドは final 宣言されていますが、内部で、equals に委譲されています。

  o == arg0

は `Any.==` では、

  o.equals(arg0)

また `AnyRef.==` では、

  if o eq null
    arg0 eq null
  else
    o.equals(arg0)

と等価です。
*/

println(v1 equals v2)  // true
println(v1 == v2)      // true

println(nullList == null)  // true


/*=== 参照の等価性: eq / ne メソッド
==============================================================================================*/
/*
メソッド eq は参照の等価性を検査します。
つまり、メモリ上の同じ位置を指している時に心となります。

これらのメソッドは `AnyRef` のためだけに定義されています。

したがって、`eq` は Java, C++, C# の `==` 演算子のように振る舞います。
*/

println(l1 == l2)  // true
println(l1 eq l2)  // false


/*=== Array の等価性: sameElements メソッド
==============================================================================================*/
val a1 = Array(1, 2)
val a2 = Array(1, 2)

println(a1 == a2)  // false
println(a1 eq a2)  // false
println(a1 sameElements a2)  // true
