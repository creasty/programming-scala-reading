/*
Scala において、カリー化された関数は複数のパラメタリストを使って表現されます。
*/

def add1(a: Int)(b: Int) = a + b

val add1Two = add1(2)(_)  // `(_)` が必要
println(add1Two(3))

/*
また以下の様な構文を使うと、
カリー化された関数を部分適用された関数として扱う場合に、語尾のアンダースコアの必要がなくなります。
*/

def add2(a: Int) = (b: Int) => a + b

val add2Two = add2(2)
println(add2Two(3))

/*
また、`curried` メソッドを使ってカリー化された形式に変換することも出来ます。
*/

def add3(a: Int, b: Int) = a + b

val curriedAdd3 = (add3 _).curried
println(curriedAdd3(2)(3))
