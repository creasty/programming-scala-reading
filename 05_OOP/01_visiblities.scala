/*
Scala では可視性のスコープは、5つあります。

| 名前                                    | キーワード       | 説明                                                   |
| --------------------------------------- | ---------------- | ------------------------------------------------------ |
| 公開 (public)                           | なし             | どこからでも参照可能                                   |
| 限定公開 (protected)                    | protected        | 定義している型、派生型、入れ子にしている型から参照可能 |
| スコープ指定限定公開 (scoped protected) | protected[scope] | 参照できる範囲が scope に限定される                    |
| 非公開 (private)                        | private          | 定義している型、入れ子にしている型から参照可能         |
| スコープ指定非公開                      | private[scope]   | 継承した場合を除いては protected[scope] と同じ         |

- private な型は同一パッケージ内からのみ使用可能
- [scope] の scope は、パッケージ名、型、this のいずれか

Java などの言語で言う、protected, private は、Scala では、protected[this] と private[this] に等しくなります。
殆どの場合、this スコープ指定されたものを使うことになるでしょう。
*/

/*=== 定義
==============================================================================================*/
package basePkg {
  class PublicClass {
    /*  フィールド
    -----------------------------------------------*/
    val publicField = 1

    protected              val protectedField      = 1
    protected[basePkg]     val pkgProtectedField   = 1
    protected[PublicClass] val typeProtectedField  = 1
    protected[this]        val thisProtectedField  = 1

    private              val privateField      = 1
    private[basePkg]     val pkgPrivateField   = 1
    private[PublicClass] val typePrivateField  = 1
    private[this]        val thisPrivateField  = 1


    /*  ネストしたクラス
    -----------------------------------------------*/
    class PublicNestedClass {
      def method(c: PublicClass) {
        println(c.publicField)

        println(c.protectedField)
        println(c.pkgProtectedField)
        println(c.typeProtectedField)
        // println(c.thisProtectedField)  // Error

        println(c.privateField)
        println(c.pkgPrivateField)
        println(c.typePrivateField)
        // println(c.thisPrivateField)  // Error
      }
    }

    protected              class ProtectedNestedClass
    protected[basePkg]     class PkgProtectedNestedClass
    protected[PublicClass] class TypeProtectedNestedClass
    protected[this]        class ThisProtectedNestedClass

    private              class PrivateNestedClass
    private[basePkg]     class PkgPrivateNestedClass
    private[PublicClass] class TypePrivateNestedClass
    private[this]        class ThisPrivateNestedClass

    val publicNested = new PublicNestedClass

    val protectedNested      = new ProtectedNestedClass
    val pkgProtectedNested   = new PkgProtectedNestedClass
    val typeProtectedNested  = new TypeProtectedNestedClass
    val thisProtectedNested  = new ThisProtectedNestedClass

    // val privateNested      = new PrivateNestedClass  // Error
    val pkgPrivateNested   = new PkgPrivateNestedClass
    val typePrivateNested  = new TypePrivateNestedClass
    // val thisPrivateNested  = new ThisPrivateNestedClass  // Error
  }

  protected class ProtectedClass
  private class PrivateClass
}


/*=== 同じ名前空間で使う場合
==============================================================================================*/
package basePkg {
  class UsingPublicClass(val c: PublicClass) {
    def method() {
      println(c.publicField)

      // println(c.protectedField)  // Error
      println(c.pkgProtectedField)
      // println(c.typeProtectedField)  // Error
      // println(c.thisProtectedField)  // Error

      // println(c.privateField)  // Error
      println(c.pkgPrivateField)
      // println(c.typePrivateField)  // Error
      // println(c.thisPrivateField)  // Error
    }
  }

  class UsingProtectedClass(val c: ProtectedClass)
  // class UsingPrivateClass(val c: PrivateClass)  // Error
}


/*=== 同じ名前空間で継承して使う場合
==============================================================================================*/
package basePkg {
  class SubPublicClass extends PublicClass {
    /*  フィールド
    -----------------------------------------------*/
    val _publicField = publicField

    val _protectedField      = protectedField
    val _pkgProtectedField   = pkgProtectedField
    val _typeProtectedField  = typeProtectedField
    val _thisProtectedField  = thisProtectedField

    // val _privateField      = privateField  // Error
    val _pkgPrivateField   = pkgPrivateField
    // val _typePrivateField  = typePrivateField  // Error
    // val _thisPrivateField  = thisPrivateField  // Error


    /*  ネストしたクラス
    -----------------------------------------------*/
    val _publicNested = new PublicNestedClass

    val _protectedNested      = new ProtectedNestedClass
    val _pkgProtectedNested   = new PkgProtectedNestedClass
    val _typeProtectedNested  = new TypeProtectedNestedClass
    val _thisProtectedNested  = new ThisProtectedNestedClass

    // val _privateNested      = new PrivateNestedClass  // Error
    val _pkgPrivateNested   = new PkgPrivateNestedClass
    // val _typePrivateNested  = new TypePrivateNestedClass  // Error
    // val _thisPrivateNested  = new ThisPrivateNestedClass  // Error
  }

  // class SubProtectedClass extends ProtectedClass  // Error
  // class SubPrivateClass extends PrivateClass  // Error
}


/*=== 別の名前空間から使う場合
==============================================================================================*/
package differentPkg {
  class UsingPublicClass(val c: basePkg.PublicClass) {
    def method() {
      println(c.publicField)

      // println(c.protectedField)  // Error
      // println(c.pkgProtectedField)  // Error
      // println(c.typeProtectedField)  // Error
      // println(c.thisProtectedField)  // Error

      // println(c.privateField)  // Error
      // println(c.pkgPrivateField)  // Error
      // println(c.typePrivateField)  // Error
      // println(c.thisPrivateField)  // Error
    }
  }

  // class UsingProtectedClass(val c: basePkg.ProtectedClass)  // Error
  // class UsingPrivateClass(val c: basePkg.PrivateClass)  // Error
}


/*=== 別の名前空間から継承して使う場合
==============================================================================================*/
package differentPkg {
  class SubPublicClass extends basePkg.PublicClass {
    /*  フィールド
    -----------------------------------------------*/
    val _publicField = publicField

    val _protectedField      = protectedField
    val _pkgProtectedField   = pkgProtectedField
    val _typeProtectedField  = typeProtectedField
    val _thisProtectedField  = thisProtectedField

    // val _privateField      = privateField  // Error
    // val _pkgPrivateField = pkgPrivateField  // Error
    // val _typePrivateField  = typePrivateField  // Error
    // val _thisPrivateField  = thisPrivateField  // Error


    /*  ネストしたクラス
    -----------------------------------------------*/
    val _publicNested = new PublicNestedClass

    val _protectedNested      = new ProtectedNestedClass
    val _pkgProtectedNested   = new PkgProtectedNestedClass
    val _typeProtectedNested  = new TypeProtectedNestedClass
    val _thisProtectedNested  = new ThisProtectedNestedClass

    // val _privateNested      = new PrivateNestedClass  // Error
    // val _pkgPrivateNested   = new PkgPrivateNestedClass  // Error
    // val _typePrivateNested  = new TypePrivateNestedClass  // Error
    // val _thisPrivateNested  = new ThisPrivateNestedClass  // Error
  }
}
