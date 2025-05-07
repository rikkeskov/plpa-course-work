import java.awt.Color

class FillAction(args: String) extends DrawObject[Array[Any]] with Action {

  override var arguments: Array[Any] = args match {
    case s"$arg1 $remaining" => Array(arg1, remaining)
    case _ => throw DrawException(s"Cant match arguments to $command", this)
  }

  private val idx: Array[Int] = arguments(1).asInstanceOf[String].trim.split(" ").map(_.toInt)

  private def parseColor(colorStr: String): Color = {
    colorStr.trim.toLowerCase match {
      case "red" => Color.RED
      case "green" => Color.GREEN
      case "blue" => Color.BLUE
      case "black" => Color.BLACK
      case "white" => Color.WHITE
      case "yellow" => Color.YELLOW
      case "cyan" => Color.CYAN
      case "magenta" => Color.MAGENTA
      case _ => throw DrawException(s"Unknown color: $colorStr", null)
    }
  }

  override def draw(context: DrawContext, objects: Array[(DrawObject[_], Int)]): Unit = {
    idx.foreach(element => {
      if (objects.length < element - 1) {
        throw DrawException(s"Object $element does not exist, maximum object value is ${objects.length}")
      } else {
        objects.apply(element-1)._1.fill = parseColor(arguments(0).asInstanceOf[String])
      }
    })
  }
}

