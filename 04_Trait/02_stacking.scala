/*
trait の Stacking (積み重ね) を利用すると、
コードの再利用性を高め、一度に2つ以上の trait をより簡単に使用することができます。
*/

/*  trait
-----------------------------------------------*/
import scala.language.reflectiveCalls

trait Subject {
  type Observer = { def receiveUpdate(subject: Any) }

  private var observers = List[Observer]()

  def addObserver(observer: Observer) = observers ::= observer
  def notifyObservers = observers foreach (_.receiveUpdate(this))
}

// これは、まさに Java の interface のよう
trait Clickable {
  // このメソッドは実装がない抽象メソッドだが、
  // trait には class のように abstruct キーワードがいらない
  def click()
}

// クリックを観察する trait
trait ObservableClicks extends Clickable with Subject {
  // `super.click()` を呼び出しているので、
  // `abstract` キーワードが必要。
  abstract override def click() {
    super.click()  // ← Clickable#click は抽象メソッド
    notifyObservers
  }
}

/*
抽象クラスを宣言する場合を除くと、
trait のメソッドに abstract キーワードを指定する必要があるのは、
メソッドが super を呼び出す実装を持つにもかかわらず、
親 trait の super メソッドが具象化された実装をもたない時だけ。
*/

// 2 回以上のクリックを無視する trait
trait VetoableClicks extends Clickable {
  val maxAllowed = 1
  private var count = 0

  abstract override def click() {
    if (count < maxAllowed) {
      count += 1
      super.click()
    }
  }
}


/*  実装
-----------------------------------------------*/
class Button(val label: String) extends Clickable {  // Clickable の「インタフェース」をもつ
  def click() {
    printf("Button [%s] clicked\n", label)
  }
}

class ButtonCountObserver {
  var count = 0
  def receiveUpdate(subject: Any) = count += 1
}


/*  テスト (1)
-----------------------------------------------*/
val button1 = new Button("Hi") with ObservableClicks
val buttonObserver1 = new ButtonCountObserver
button1.addObserver(buttonObserver1)

for (i <- 1 to 3) button1.click()

println("count: " + buttonObserver1.count)


/*  テスト (2)
-----------------------------------------------*/
val button2 = new Button("Hi") with ObservableClicks with VetoableClicks
val buttonObserver2 = new ButtonCountObserver
button2.addObserver(buttonObserver2)

for (i <- 1 to 3) button2.click()

println("count: " + buttonObserver2.count)  // 1

/*
with ObservableClicks with VetoableClicks

の順番で宣言されている。
この場合、`click` は以下の順番で呼び出される。(宣言の順番と逆)

1. VetoableClicks#click
2. ObservableClicks#click
3. Button#click
*/


/*  テスト (3)
-----------------------------------------------*/
// trait の順番を逆にしてみる
val button3 = new Button("Hi") with VetoableClicks with ObservableClicks
val buttonObserver3 = new ButtonCountObserver
button3.addObserver(buttonObserver3)

for (i <- 1 to 3) button3.click()

println("count: " + buttonObserver3.count)  // 3
