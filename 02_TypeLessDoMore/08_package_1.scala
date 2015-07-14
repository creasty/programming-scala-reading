/*
Scala は Java が名前空間としてつかている package の概念を採用しているが、
ファイル名が型名とと一致している必要も、パッケージ構造がディレクトリ構造に一致している必要もない。
*/


// Java の構文 (ヘッダ宣言型)
package com.example


// C# の構文 (入れ子型)
package pkg1 {
  class MyClass1 { }
}

package pkg2 {
  class MyClass2 { }

  package pkg21 {
    class MyClass21 { }
  }

  // 1つの節で複数のパッケージを連結できる
  package pkg22.pkg221.pkg2211 {
    class MyClass2211 { }
  }
}
