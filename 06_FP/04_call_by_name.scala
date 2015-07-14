/*
普通、関数のパラメタは「値渡し」(関数に渡される前に評価される) です。

Scala では、関数内で参照されるまでは評価したくない式を関数に渡すことが出来き、「名前渡し」と言います。
名前渡しパラメタは、関数パラメタに普通伴うパーレンを省略することで指定します。
*/

def myWhile(conditional: => Boolean)(f: => Unit) {  // `x: () => T` の形になっていないことに注目
  if (conditional) {
    f
    myWhile(conditional)(f)
  }
}

var count = 0

myWhile(count < 5) {
  println(count)
  count += 1
}
