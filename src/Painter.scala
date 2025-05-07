import java.awt.Graphics2D

class Painter {
  private val context: DrawContext = new DrawContext() // Initialize with null graphics

  private def parse(text: String): Array[(DrawObject[_], Int)] = {
    var objects: Array[(DrawObject[_], Int)] = Array()

    for ((s, lineNumber) <- text.split("\n").zipWithIndex) {
      // Commands in forms:
      // # Comment
      // Base cmd -> "(CMD args)"
      // Boxes -> "(CMD (coord1 coord2) (coord3 coord4) )"
      // Text/Circle -> "(CMD (coord1 coord2) val)"
      if(!s.startsWith("#")){
        // In case of command which adds a new object, add given drawing to the objects array
        // then in draw command use the arguments to find the given object from the array and assign it a color
        try {
          objects = objects :+ (s.trim match {
            case s"(BOUNDING-BOX $args)" => (new BoundingBox(args), lineNumber);
            case s"(RECTANGLE $args)" => (new Rectangle(args), lineNumber);
            case s"(LINE $args)" => (new Line(args), lineNumber);
            case s"(CIRCLE $args)" => (new Circle(args), lineNumber);
            case s"(TEXT-AT $args)" => (new TextAt(args), lineNumber);
            case s"(COLOR $args)" => (new ColorAction(args), lineNumber);
            case s"(FILL $args)" => (new FillAction(args), lineNumber);
            case s"($command $args)" => throw DrawException(s"Unknown command on Line $lineNumber: \"$command\" with args \"$args\"");
            case s"($command)" => throw DrawException(s"Unknown command on Line $lineNumber: \"($command)\"");
            case s"$text" => throw DrawException(s"Missing command structure \"( )\" at $lineNumber: \"$text\"");
          })
        } catch {
          case DrawException(error, drawObject) => context.addError(error, lineNumber, drawObject);
          case e: Throwable => System.out.println("Unknown parsing error at line: " + (lineNumber + 1) + "\n" + s"\"$s\"" + e.getCause + "\n" + e.toString);
        }
      }
    }

    return objects
  }

  def paint(text: String, g: Graphics2D): Unit = {
    context.graphics = g // Update the graphics object

    context.resetContext()
    context.objects = this.parse(text)

    val mappedObjects = context.objects.groupBy {
      case (_: DrawObject[_] with Action, _:Int) => "action"
      case (_: DrawObject[_] with Object, _:Int) => "object"
    }
    val objects = mappedObjects.getOrElse("object", Array.empty[(DrawObject[_], Int)])
    val actions = mappedObjects.getOrElse("action", Array.empty[(DrawObject[_], Int)])
    actions.foreach{case (a: DrawObject[_] with Action, lineNumber) => try {
      a.draw(context, objects)
    } catch {
      case DrawException(error, drawObject) => context.addError(error, lineNumber, drawObject);
      case e: Throwable => System.out.println("Unknown action error at line: " + (lineNumber + 1) + "\n" + e.getCause + "\n" + e.toString);
    }}

    objects.foreach{case (obj: DrawObject[_] with Object, lineNumber) => try {
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
      case DrawException(error, drawObject) => context.addError(error, lineNumber, drawObject);
      case e: Throwable => System.out.println("Unknown object error at line: " + (lineNumber + 1) + "\n" + e.getCause + "\n" + e.toString);
    }}
  }

  def getContext: DrawContext = context // Add a getter for the context
}
