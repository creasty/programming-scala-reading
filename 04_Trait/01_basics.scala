/*
Trait は実装を持つことのできる interface (Java)、あるいは module (Ruby) などの mixin。

mixin としての trait は、付加的な振る舞いに最も適している。
「is a」ではなく「behaves as」。
*/

/*  trait
-----------------------------------------------*/
import scala.language.reflectiveCalls  // 29 行目の (_.receiveUpdate(this)) で warning が出る

trait Subject {
  type Observer = { def receiveUpdate(subject: Any) }

  private var observers = List[Observer]()

  // `::` は右側に束縛される
  // observers = observer :: observers
  // observers = observers.::(observer)
  def addObserver(observer: Observer) = observers ::= observer

  // observers foreach _.receiveUpdate(this)
  // のようにパーレンがないと、
  // ((x$1) => observers.foreach(x$1.receiveUpdate(this)))
  // になる
  def notifyObservers = observers foreach (_.receiveUpdate(this))
}


/*  実装
-----------------------------------------------*/
// 1つ以上の trait を使うクラスを宣言し、そのクラスが他のクラスを**拡張しない**のであれば、
// 最初の trait に extends キーワードを使わなければならない
class Button(val label: String) {
  def click() {
    printf("Button [%s] clicked\n", label)
  }
}

class ObservableButton(name: String) extends Button(name) with Subject {
  override def click() {
    super.click()
    notifyObservers
  }
}

class ButtonCountObserver {
  var count = 0
  def receiveUpdate(subject: Any) = count += 1
}


/*  テスト
-----------------------------------------------*/
val button = new ObservableButton("Hi")
val buttonObserver = new ButtonCountObserver
button.addObserver(buttonObserver)

for (i <- 1 to 3) button.click()

println("count: " + buttonObserver.count)
