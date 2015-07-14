package shapes

import scala.actors._
import scala.actors.Actor._


object ShapeDrawingActor extends Actor {
  // `act` メソッドは抽象メソッドのため `override` キーワードは必要ない。
  def act() {
    loop { // 無限ループ
      /*
      receive メソッドの呼び出し。
      パーレンではなくブレースを使っていることに注意。
      ブレース内はメソッドに渡される1つの関数リテラルを構成する。
      */
      receive {
        case s: Shape => s.draw()
        case "exit"   => println("exiting..."); exit
        case x: Any   => println("Error: Unknown message! " + x) // 最上位のスーパクラスが Any
      }
    }
  }
}
