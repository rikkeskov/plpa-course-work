import javax.swing.JComponent

class Painter(canvas: JComponent) {
  private var context: DrawContext = new DrawContext(canvas.getGraphics)

  private def parse(text: String): Array[DrawObject[_]] = {
    var objs: Array[Either[DrawObject[_], IllegalArgumentException]] = Array()
    for ((s, linenumber) <- text.split("\n").zipWithIndex) {

      objs :+ (s.trim match {
        case s"(BOUNDING-BOX $args)" => new BoundingBox(args);
        case s"(RECTANGLE $args)" => new Rectangle(args);
        case s"(LINE $args)" => new Line(args);
        case s"(CIRCLE $args)" => new Circle(args);
        case s"(TEXT-AT $args)" => new TextAt(args);
        case s"(DRAW $args)" => new Draw(args);
        case s"(FILL $args)" => new Fill(args);
        case s"($command $args)" => new IllegalArgumentException(s"Unknown command on Line $linenumber: \"$command\" with args \"$args\"")
      })
      // Commands in forms:
      // Base cmd -> "(CMD args)"
      // Boxes -> "(CMD (coord1 coord2) (coord3 coord4) )"
      // Text/Circle -> "(CMD (coord1 coord2) val)"

      // In case of command which adds a new object, add given drawing to the objects array
      // then in draw command use the arguments to find the given object from the array and assign it a color
    }

    val errors = objs.filter(s => s.isRight)

    return objs.collect {
      case Left(drawObject) => drawObject
    }
  }

  def paint(text: String): Unit = {
    context.objects = this.parse(text)

    context.objects :+ new BoundingBox("(0 0) (30 30)")

    for (obj <- context.objects)

      obj.draw(context)

  }
}
