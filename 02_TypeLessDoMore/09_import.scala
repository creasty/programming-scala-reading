/*  基本
-----------------------------------------------*/
// ワイルドカードは Java では `*` だが、Scala では `_` を使う。
// `*` は関数名として有効な文字だから。
import java.awt._


// 指定
// Scala の object なら、フィールドとメソッドをインポートする
import java.io.File


// 複数指定
import java.util.{Map, HashMap}


/*  高度な使い方
-----------------------------------------------*/
def writeAboutBigInteger {
  // どこでも import できる
  import java.math.BigInteger.{
    ZERO => MYZERO,  // エイリアス
    ONE => _,        // 隠す。例外を作るときに便利
    TEN
  }

  // println(ONE)   // 未定義
  // println(ZERO)  // 未定義
  println(MYZERO)
  println(TEN)
}

writeAboutBigInteger

// `writeAboutBigInteger` のスコープに束縛されているので、
// メソッドの外からは参照できない
//
// println(MYZERO)
// println(TEN)


/*  import は相対的
-----------------------------------------------*/
import scala.collection.mutable._
import collection.immutable._  // `scala` はすでに import されており省略可
import _root_.scala.collection.immutable._  // `_root_` が実際の「ルート」からの import

package scala.actors {  // ここでスコープができる
  import remote._       // `scala.actors.remote._` となる
}
