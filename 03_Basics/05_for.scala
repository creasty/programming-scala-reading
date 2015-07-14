val colors = List(
  "Red",
  "Orange",
  "Yellow",
  "Green",
  "Blue",
  "Indigo",
  "Violet"
)

// `<-` 左向きのアロー演算子は「ジェネレータ」と呼ばれる
for (color <- colors)
  println(color)

println("")


/*  フィルタリング
-----------------------------------------------*/
for (
  color <- colors
  if color.contains("e")  // 条件式の周りにパーレンはいらない
) println(color)

println("")

for (
  color <- colors
  if color.contains("e");  // 条件が複数ある場合はセミコロンで区切る
  if color.length <= 4
) println(color)

println("")


/*  yield
-----------------------------------------------*/
val filteredColors = for {  // パーレンじゃなくてブレース
  color <- colors
  if color.contains("e")  // セミコロンは必要ない
  if color.length <= 4
} yield color  // yield キーワードを使うと新しいコレクションを生成できる

// `filteredColors` は `colors` の**部分リスト**であるから、`List[String]` になる。
println(filteredColors)

/*
パーレンとブレースどちらを使って for 式を書いてもいいが、
ブレースの場合セミコロンでフィルタを区切る必要がなくなる。
フィルタや値の代入が複数ある場合はブレースのほうがいい。
*/


/*  スコープの拡大
-----------------------------------------------*/
for {
  color <- colors
  upcasedColor = color.toUpperCase()  // val で宣言しなくても、for の本体で再利用できる
} println(upcasedColor)

// println(upcasedColor)  // スコープ外
