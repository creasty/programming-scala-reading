/*
 「部分適用された関数」は、関数で定義された引数の一部がパラメタとして供給されなていない式です。
関数をアンダースコア `_` 付きで呼び出すと、メソッドが「関数値」に変わります。
*/

def add(a: Int, b: Int) = a + b

val fAdd = add _
println(fAdd(1, 2))

val addTwo = add(2, _: Int)
println(addTwo(3))

/*
1引数のメソッドは明示的にアンダースコアを書く必要がありません。
*/
List("for", "bar").foreach(println)


/*
すべての部分関数は `PartialFunction` 型です。
このトレイトは、`orElse` メソッドを定義しています。
`orElse` メソッドは、他の `PartialFunction` を引数に取ります。

case 文は、内部的には部分関数に展開されます。
展開された関数は抽象メソッド `isDefinedAt` を提供します。
`isDefinedAt` メソッドは、部分関数の定義域の境界の指定に使われます。
*/
val truthier: PartialFunction[Boolean, String] = { case true => "truthful" }
val fallback: PartialFunction[Boolean, String] = { case x => "sketchy" }
val tester = truthier orElse fallback

println(tester(true))
println(tester(false))

println(truthier.isDefinedAt(true))
