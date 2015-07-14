object Upper {
  // エントリポイント。
  // Upper は object なのでまさに Java の static main と同じ。
  def main(args: Array[String]) = {
    /*
    こういうのはできない:

    args.foreach(printf("%s ", _.toUpperCase()))
    ==> ((x$1) => x$1.toUpperCase())

    args.map(_).foreach(printf("%s ", _))
    ==> ((x$1) => args.map(x$1).foreach(((x$2) => printf("%s ", x$2))))

    args.foreach(printf("%s %s ", _, _))
    ==> ((x$1, x$2) => printf("%s %s ", x$1, x$2))

    ---

    逆にこういうのはいける:

    args.map(_ * 2)
    */
    args.map(_.toUpperCase()).foreach(printf("%s ", _))
    println("")
  }
}
