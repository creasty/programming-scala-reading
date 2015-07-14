import shapes._


// デフォルトでこのアクターは独自のスレッドで実行される
ShapeDrawingActor.start()

// actor ! message 構文
ShapeDrawingActor ! new Circle(new Point(0.0, 0.0), 1.0)
ShapeDrawingActor ! new Rectangle(new Point(0.0, 0.0), 2, 5)
ShapeDrawingActor ! new Triangle(
  new Point(0.0, 0.0),
  new Point(1.0, 0.0),
  new Point(0.0, 1.0)
)

ShapeDrawingActor ! 3.14

ShapeDrawingActor ! "exit"
