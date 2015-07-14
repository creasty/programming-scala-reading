/*  シンプルな例
-----------------------------------------------*/
val things: List[Any] = List(1, 2, "hello", 3.14)

for (thing <- things) {
  thing match {
    case int: Int => println(s"Say, $int")  // 型に対するマッチ
    case "hello"  => println("Hello World") // 値に対する
    case _        => println("Oops")        // その他はワイルドカード
  }
}


/*  マッチ式の変数
-----------------------------------------------*/
val rand = new scala.util.Random().nextInt(10)

rand match {
  case 7   => println("Lucky seven!")
  case num => println(s"Boring $num")
}


/*  シーケンスに対するマッチ
-----------------------------------------------*/
val willWork    = List(1, 3, 23, 90)
val willNotWork = List(4, 18, 52)
val empty       = List()


for (l <- List(willWork, willNotWork, empty)) {
  l match {
    case List(_, 3, _, _) => println("Four elements with the 3 at the secound")
    case List(_*)         => println("Other")
  }
}

for (l <- List(willWork, willNotWork, empty)) {
  l match {
    // List.cons
    case head :: tail => printf("Head: %s, Tail: %s\n", head, tail)

    case Nil          => println("None")
  }
}


/*  タプルに対するマッチ
-----------------------------------------------*/
val tup1 = ("Good", "Morning!")
val tup2 = ("Guten", "Tag!")

for (tup <- List(tup1, tup2)) {
  tup match {
    case (first, second) if first == "Good" =>
      println("English")
    case (first, second) =>
      println("Other")
  }
}


/*  case class に対するマッチ
-----------------------------------------------*/
case class Person(name: String, age: Int)

val alice   = new Person("Alice", 25)
val bob     = new Person("Bob", 32)
val charlie = new Person("Charlie", 19)

for (person <- List(alice, bob, charlie)) {
  person match {
    case Person("Alice", 25) =>
      println("Hey Alice!")
    case Person(name, age) if age > 30 =>
      println("Hi " + name)
    case Person(name, _) =>
      println(s"Who is $name?")
  }
}


/*  入れ子の変数の束縛
-----------------------------------------------*/
class Role
case object Manager extends Role
case object Developer extends Role

case class Employee(name: String, role: Role)

val employees = Map(
  1 -> new Employee("Alice", Developer),
  2 -> new Employee("Bob", Manager),
  3 -> new Employee("Charlie", Developer)
)

for (employee <- employees) {
  employee match {
    case (id, e @ Employee(_, Manager)) =>  // `@` を使うとマッチを平坦化できる
      printf("#%d %s is a manager\n", id, e.name)
    case (id, e @ Employee(_, _)) =>
      println("Other " + e)
  }
}

// `@` 構文を使わないとこうなる
for (employee <- employees) {
  employee match {
    case (id, e: Employee) => e.role match {  // Employee 自体に対するマッチ条件を使わない
      case Manager =>
        printf("#%d %s is a manager\n", id, e.name)
      case _ =>
        println("Other " + e)
    }
  }
}


/*  正規表現に対するマッチ
-----------------------------------------------*/
val BookExtractorRE = "Book: id=(\\d+) author=(.+)".r
val MagazineExtractorRE = "Magazine: issue=(\\d+)".r

val entries = List(
  "Book: id=123 author=David",
  "Magazine: issue=678",
  "Book: id=456 author=John",
  "CD: title=Hello"
)

for (entry <- entries) {
  entry match {
    case BookExtractorRE(id, author) =>  // キャプチャグループを抽出子に変換
      printf("Book #%s by %s\n", id, author)
    case MagazineExtractorRE(issue) =>
      println("Magazine issue no. " + issue)
    case entry =>
      println("Unknown: " + entry)
  }
}
