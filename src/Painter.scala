import java.awt.Graphics2D
import javax.swing.JComponent

class Painter(canvas: JComponent) {
  private val context: DrawContext = new DrawContext(canvas.getGraphics.asInstanceOf[Graphics2D])

  private def parse(text: String): Array[DrawObject[_]] = {
    val objects: Array[DrawObject[_]] = Array()

    for ((s, lineNumber) <- text.split("\n").zipWithIndex) {
      // Commands in forms:
      // Base cmd -> "(CMD args)"
      // Boxes -> "(CMD (coord1 coord2) (coord3 coord4) )"
      // Text/Circle -> "(CMD (coord1 coord2) val)"

      // In case of command which adds a new object, add given drawing to the objects array
      // then in draw command use the arguments to find the given object from the array and assign it a color
      try{
        objects :+ (s.trim match {
          case s"(BOUNDING-BOX $args)" => new BoundingBox(args);
          case s"(RECTANGLE $args)" => new Rectangle(args);
          case s"(LINE $args)" => new Line(args);
          case s"(CIRCLE $args)" => new Circle(args);
          case s"(TEXT-AT $args)" => new TextAt(args);
          case s"(DRAW $args)" => new Draw(args);
          case s"(FILL $args)" => new Fill(args);
          case s"($command $args)" => DrawException(s"Unknown command on Line $lineNumber: \"$command\" with args \"$args\"", null)
        })
      } catch{
        case DrawException(error, drawObject) => context.addError(drawObject, error);
        case _ => System.out.println("Unknown error at line: " + (lineNumber + 1) + "\n" + s"\"$s\"");
      }
    }

    return objects
  }

  def paint(text: String): Unit = {
    context.objects = this.parse(text)

    context.objects :+ new BoundingBox("(0 0) (30 30)")


    for (obj <- context.objects)

      obj.draw(context)

  }
}
