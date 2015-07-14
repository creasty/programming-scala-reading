/*
trait は補助コンストラクタをサポートしないので、
この特徴から拡張できるのは、引数なしの基本または補助コンストラクタを持つクラスだけです。

trait のコンストラクタに引数を渡すことはできませんが、
フィールドをデフォルト値で初期化することや、抽象フィールドにしておくことができる。

trait 内の具象フィールドが適切なデフォルト値を持たない場合、
値を初期化する**フェールセーフな方法は存在しません**。
そのため、フィールドは抽象のままにしておき、ユーザによるアドホックな手順が必要です。
*/

trait T1 {
  println(t1)
  val t1 = "T1"
  println(t1)
}

trait T2 {
  println(t2)
  val t2 = "T2"
  println(t2)
}

class Base {
  println(base)  // エラーになる？？
  val base = "Base"
  println(base)
}

class C extends Base with T1 with T2 {
  println(c)  // エラーになる？？
  val c = "C"
  println(c)
}


new C

/*
1. Base
2. T1
3. T2
4. C

の順番で生成される
*/
