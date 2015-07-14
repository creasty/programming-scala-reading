/*=== 具現クラス
==============================================================================================*/
class ConcreteClass {
  val concreteValField = 1
  var concreteVarField = 1

  def method = "this is a method"
}

class SubConcreteClass extends ConcreteClass {
  override val concreteValField = -1  // `override` キーワードは必要

  // override var concreteVarField = -1  // `var` は override 出来ない
  concreteVarField = -1  // でも「再設定」できる

  override def method = "overrided!"  // `override` キーワードは必須
}


/*=== 抽象クラス
==============================================================================================*/
abstract class AbstractClass {
  val abstractValField: Int
  var abstractVarField: Int

  val concreteValField = 1
  var concreteVarField = 1

  def abstractMethod: String
  def concreteMethod: String = "this is a concrete method"
}

class SubAbstractClass extends AbstractClass {
  override val abstractValField = -1  // `override` キーワードは必要
  var abstractVarField = -1  // オーバライドできる！(キーワードは任意)

  override val concreteValField = -1  // `override` キーワードは必要
  // override var concreteVarField = -1  // `var` は override 出来ない

  def abstractMethod = "overrided!"           // 抽象メソッドなので `override` キーワードは任意
  override def concreteMethod = "overrided!"  // 具現メソッドなので `override` キーワードは必要
}


/*=== トレイト
==============================================================================================*/
trait RegualrTrait {
  val abstractValField: Int
  var abstractVarField: Int

  val concreteValField = 1
  var concreteVarField = 1

  def abstractMethod: String
  def concreteMethod: String = "this is a concrete method"
}

class ClassWithRegualrTrait extends RegualrTrait {
  override val abstractValField = -1  // `override` キーワードは必要
  var abstractVarField = -1  // オーバライドできる！(キーワードは任意)

  override val concreteValField = -1  // `override` キーワードは必要
  // override var concreteVarField = -1  // `var` は override 出来ない

  def abstractMethod = "overrided!"           // 抽象メソッドなので `override` キーワードは任意
  override def concreteMethod = "overrided!"  // 具現メソッドなので `override` キーワードは必要
}


/*=== 名前空間の統合 (統一形式アクセスの原則)
==============================================================================================*/
abstract class AbstractParent {
  def abstractField: String             // パーレンなしの抽象メソッド
  def concreteField: String = "parent"  // パーレンなしの具現メソッド
}

class ConcreteChild extends AbstractParent {
  val abstractField = "child"           // `val` でオーバライドできる
  override val concreteField = "child"  // `override` キーワードは必要
}


/*=== `final` 宣言
==============================================================================================*/
/*  メソッド
-----------------------------------------------*/
class RegularClass {
  final def finalMethod = "this is a final method"
}

class SubRegularClass extends RegularClass {
  // override def finalMethod = "can't be overrided"  // 親で `final` されているものは override できない
}


/*  クラス
-----------------------------------------------*/
final class FinalClass

// class SubFinalClass extends FinalClass  // `final` な class は継承できない
