/*
Scala では「パラメタ化された型」を使い、Java では「ジェネリクス」を使う。
構文には <> ではなく [] を使う。

後の章で詳しく取り上げられるが、"parameterized types" は "generics" とは似ているが異なる。
*/

val languages: List[String] = List("en", "ja", "fr")


/*
Scala は、抽象型という型パラメタとは別の方の抽象化メカニズムをサポートしている。
Haskell などの関数型プログラミング言語でも多く使用されている。

抽象型は、型パラメタと同じ問題の多くに適用できるが、
各メカニズムは重複し冗長というわけではなく、問題に対し向き不向きがある。

- 型パラメタは、コレクションに使うのが最も適している。
- 抽象型は、「族」や、コレクション以外に使うシナリオに対して最も有用。
*/


/*  抽象型のアプローチ
-----------------------------------------------*/
import java.io._

abstract class BlukReader1 {
  type In  // 抽象型
  val source: In

  def read: String
}

class StringBulkReader1(val source: String) extends BlukReader1 {
  type In = String

  def read = source
}

class FileBulkReader1(val source: File) extends BlukReader1 {
  type In = File

  def read = {
    val in = new BufferedInputStream(new FileInputStream(source))
    val numBytes = in.available()
    val bytes = new Array[Byte](numBytes)
    in.read(bytes, 0, numBytes)
    new String(bytes)
  }
}


/*  型パラメタのアプローチ
-----------------------------------------------*/
abstract class BlukReader2[In] {
  val source: In

  def read: String
}

class StringBulkReader2(val source: String) extends BlukReader2[String] {
  def read = source
}

class FileBulkReader2(val source: File) extends BlukReader2[File] {
  def read = {
    val in = new BufferedInputStream(new FileInputStream(source))
    val numBytes = in.available()
    val bytes = new Array[Byte](numBytes)
    in.read(bytes, 0, numBytes)
    new String(bytes)
  }
}
