// 名前空間は、Java のようにヘッダに宣言する方法が一般的だが、C# ような全体をブロックでくくる構文も使える。
package shapes


/*
- パーレンがコンストラクタのパラメタになる。
- val だと immutable (読み取り専用) になる。
  mutable なパラメタおよび、可変引数の場合は var を使う。
*/
class Point(val x: Double, val y: Double) {
  // concrete メソッドをオーバライドするときは `override` キーワードが必要
  override def toString() = "Point(" + x + ", " + y + ")"
}

abstract class Shape {
  /*
  - メソッドには `abstract` キーワードはつかない。
  - `Unit` は Java / C でいう `void` にほぼ相当する。
  */
  def draw(): Unit
}

class Circle(val center: Point, val radius: Double) extends Shape {
  // this は直前の文字列との結合演算 `+` によって暗黙的に `this.tostring()` が呼ばれる
  def draw() = println("Circle.draw: " + this)
  override def toString() = "Circle(" + center + ", " + radius + ")"
}

class Rectangle(val lowerLeft: Point, val height: Double, val width: Double) extends Shape {
  def draw() = println("Rectangle.draw: " + this)
  override def toString() = "Rectangle(" + lowerLeft + ", " + height + ", " + width + ")"
}

class Triangle(val point1: Point, val point2: Point, val point3: Point) extends Shape {
  def draw() = println("Triangle.draw: " + this)
  override def toString() = "Triangle(" + point1 + ", " + point2 + ", " + point3 + ")"
}
