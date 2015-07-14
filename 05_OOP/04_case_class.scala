case class Point(x: Double, y: Double) {  // `val` キーワードはコンパイラが自動挿入するので、任意になる
  // コンストラクタ引数に指定されたフィールドを使用して、
  // `equals`, `hashCode`, `toString` メソッドが自動で実装される
  // (case 式と一緒に使わないような単純な構造化クラスとしても便利)
}

// さらに、コンストラクタと同じ引数を取る `apply` と
// `unapply` メソッドを持ったコンパニオンオブジェクトを自動生成してくれる。
// object Point { def apply( ....

/*
注: ケースクラスに補助コンストラクタを作ることもできるが、対応する `apply` は多重定義されない。
*/


/*=== 二項演算子用の糖衣構文
==============================================================================================*/
List(1, 2, 3) match {
  // case ::(head, tail) と等価
  case head :: tail => ;

  case _ => ;
}

/*
実はここに `::` という名前のケースクラスのコンパニオンオブジェクトが使われています。
case 式で使われた場合には、コンパイラは `unapply` を呼び出す特別な中置演算子記法として扱うのです。
*/

new Point(1.0, 2.0) match {
  // case Point(x, y) と等価
  case x Point y => ;
}


/*  2引数以上の場合
-----------------------------------------------*/
case class Point3D(x: Double, y: Double, z: Double)

new Point3D(1.0, 2.0, 3.0) match {
  case x Point3D (y, z) => ;
}


/*=== copy メソッド (v2.8)
==============================================================================================*/
/*
あるインスタンスと幾つかフィールドの値が異なる新しいインスタンスを作るときに便利です。
*/

case class Circle(center: Point, radius: Double)

val c1 = Circle(Point(0.0, 0.0), 2.0)
val c2 = c1 copy (radius = 4.0)


/*=== 継承
==============================================================================================*/
/*
 `==` メソッドが反射律を満たせないことから、v2.8 から抽象ケースクラスの継承が非推奨となりました。
したがって、以下のようには出来ません。

  abstract case class AbstractCaseClass(field: Int)

  case class ConcreteCaseClass(override val field: Int, foo: Int) extends AbstractCaseClass(field)


以下記事を参照

> Scala case class inheritance
> http://stackoverflow.com/questions/12705309/scala-case-class-inheritance
*/

sealed trait Shape {
 val id: Int
}

case class Square(id: Int, width: Int, height: Int) extends Shape
