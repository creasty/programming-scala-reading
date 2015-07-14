/*  整数リテラル
-----------------------------------------------*/
val i1 = 1
val i2 = 0xff
// val i3 = 0755  // Deplicated

val l: Long  = 100L // 64 bit (signed)
val i: Int   = 100  // 32 bit (signed)
val s: Short = 1    // 16 bit (signed)
val c: Char  = 'A'  // 16 bit (unsigned)
val b: Byte  = 'A'  //  8 bit (signed)


/*  浮動小数点リテラル
-----------------------------------------------*/
val f: Float = 0.1f

val d1: Double = 0.1
val d2: Double = 0.1d  // 任意で d を suffix できる


/*  ブールリテラル
-----------------------------------------------*/
val b1: Boolean = true
val b2: Boolean = false


/*  文字リテラル
-----------------------------------------------*/
val c1 = '\n'
val c2 = '\u0041'
// val c3 = '\012'  // Deplicated


/*  文字列リテラル
-----------------------------------------------*/
val s1: String = "This is a string"

val s2 = """He exclaimed, "Scala is great!""""  // 4つ double quote が続くが期待通り動く

val s3 = """
  This
  is
  great
"""


/*  シンボルリテラル
-----------------------------------------------*/
// Ruby, Smalltalk, Lisp のように頻繁には使わない
// 正規表現は /'[a-zA-Z_][a-zA-Z0-9_]+/

val sym = 'id  // `scala.Symbol("id")` のマクロになっている


/*  バッククオートリテラル
-----------------------------------------------*/
// 識別子に、予約後や識別子に使用できない文字を使用したいときに `` でくくる
val `this is valid ident` = 123
println(`this is valid ident`)

// パッケージやメソッド名が予約後であるときに役立つ
import scala.`package`.Throwable
