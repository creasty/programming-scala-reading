/*
object と class が同じファイルの同じパッケージ内に、同じ名前で両方とも定義されている場合、
それぞれを「コンパニオンオブジェクト」と「コンパニオンクラス(型)」と言います。
*/

/*=== apply / unapply
==============================================================================================*/
type Pair[+A, +B] = Tuple2[A, B]

object Pair {
  // object の場合、`apply` は新たなインスタンスを返すファクトリメソッドとして使われるのが慣習
  def apply[A, B](x: B, y: B) = Tuple2(x, y)

  // コンパニオン型のインスタンスからフィールド値を抽出するために使われる。
  // 抽出子 (extractor) とも呼ばれる。
  // パターンマッチではこの特徴が大々的に使われている。
  def unapply[A, B](x: Tuple2[A, B]): Option[Tuple2[A, B]] = Some(x)
}

// `new` なしで `Pair` のインスタンスを生成するように、
// 糖衣構文によって `apply` が呼ばれている。
val p = Pair(1, "one") // <=> Pair.apply(1, "one")


/*  ファクトリ
-----------------------------------------------*/
/*
クラスに複数のコンストラクタがあり、コンパニオンオブジェクトもあるなら、コンストラクタの定義を減らし、
バリエーションはコンパニオンオブジェクトに複数の `apply` メソッドを多重定義することで対応することを検討してください。
*/


/*  パターンマッチ
-----------------------------------------------*/
class Checkbox(val label: String, val checked: Boolean)

object Checkbox {
  def unapply(checkbox: Checkbox) = Some((checkbox.label, checkbox.checked))  // 多値はタプルを Some でラップして返す
}

new Checkbox("label", true) match {
  case Checkbox(label, checked) => println(s"Checkbox(label = $label, checked = $checked)")
}


/*  コレクション
-----------------------------------------------*/
class MyList[T](val values: List[T])

object MyList {
  // apply が可変引数を取り、
  // `List[T]` から `MyList[T]` を生成する
  def apply[T](xs: T*): MyList[T] = new MyList(xs.toList)

  // `MyList[T]` から `List[T]` を抽出する
  def unapplySeq[T](x: MyList[T]): Option[List[T]] = Some(x.values)
}

MyList(1, 2, 3) match {
  case MyList(head, rest @ _*) => println(s"Matched with head = $head, rest = $rest")
}


/*=== Java の静的メソッド
==============================================================================================*/
/*
コンパニオンオブジェクトに定義されたメソッドは Java から static なメソッドとして見えるべきですが、
残念ながら現バージョンの Scala ではそうなっていません。

したがって、Java から static メソッドとして、アクセスする必要があるのなら、
コンパニオンオブジェクトに定義せずに、シングルトンオブジェクトの中に定義する必要があります。
*/

class Person(val name: String)

object Person {
  // この中に定義したメソッドは Java から呼べない

  def main() {
    // ...
  }

  def apply(name: String) = new Person(name)
}

// 以下のようにシングルトンオブジェクトに定義する
object PersonTest {
  def main() {
    // ...
  }
}

object PersonFactory {
  def make(name: String) = new Person(name)
}
