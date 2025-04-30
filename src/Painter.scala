import java.awt.Graphics2D
import javax.swing.JComponent

class Painter(canvas: JComponent) {
  private var context: DrawContext = new DrawContext() // Initialize with null graphics

  private def parse(text: String): Array[DrawObject[_]] = {
    var objects: Array[DrawObject[_]] = Array()

    for ((s, lineNumber) <- text.split("\n").zipWithIndex) {
      // Commands in forms:
      // Base cmd -> "(CMD args)"
      // Boxes -> "(CMD (coord1 coord2) (coord3 coord4) )"
      // Text/Circle -> "(CMD (coord1 coord2) val)"

      // In case of command which adds a new object, add given drawing to the objects array
      // then in draw command use the arguments to find the given object from the array and assign it a color
      try {
        objects = objects :+ (s.trim match {
          case s"(BOUNDING-BOX $args)" => new BoundingBox(args);
          case s"(RECTANGLE $args)" => new Rectangle(args);
          case s"(LINE $args)" => new Line(args);
          case s"(CIRCLE $args)" => new Circle(args);
          case s"(TEXT-AT $args)" => new TextAt(args);
          case s"(COLOR $args)" => new ColorAction(args);
          case s"(FILL $args)" => new Fill(args);
          case s"($command $args)" => throw DrawException(s"Unknown command on Line $lineNumber: \"$command\" with args \"$args\"");
          case s"($command)" => throw DrawException(s"Unknown command on Line $lineNumber: \"($command)\"");
          case s"$text" => throw DrawException(s"Missing command structure \"( )\" at $lineNumber: \"$text\"");
        })
      } catch {
        case DrawException(error, drawObject) => context.addError(error, lineNumber, drawObject);
        case e: Throwable => System.out.println("Unknown error at line: " + (lineNumber + 1) + "\n" + s"\"$s\"" + e.getCause);
      }
    }

    return objects
  }

  def paint(text: String, g: Graphics2D): Unit = {
    context.graphics = g // Update the graphics object
    context.resetContext()
    context.objects = this.parse(text)
    println(context.objects.mkString("Array(", ", ", ")"))

    for (obj <- context.objects) {
      try {
        if ( obj.isInstanceOf[BoundingBox] ) {
          context.graphics.setClip(null)
        }
        else {
          context.latestBoundingBox match {
            case Some(box) =>
              context.graphics.setClip(box);
            case None =>  throw DrawException("Missing Bounding-box", null)
          }
        }
        obj.draw(context)
      } catch {
        case DrawException(error, drawObject) => context.addError(error, 0, drawObject); // todo add line context to objects
        case e: Throwable => System.out.println("Unknown error at line: " + (0 + 1) + "\n" + e.getCause);
      }
    }
  }

  def getContext: DrawContext = context // Add a getter for the context
}
