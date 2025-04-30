import java.awt.Color

class ColorAction(args: String) extends DrawObject[Array[Any]] {

  override var arguments: Array[Any] = args match {
    case s"$arg1 $remaining" => Array(arg1, remaining)
    case _ => throw DrawException(s"Cant match arguments to $command", this)
  }

  private val lineNumbers: Array[Int] = arguments(1).asInstanceOf[String].split(" ").map(_.toInt)

  private def parseColor(colorStr: String): Color = {
    colorStr.toLowerCase match {
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

  override def draw(context: DrawContext): Unit = {
    lineNumbers.foreach(element => {
      val obj = context.objects.apply(element-1)
      obj.color = parseColor(arguments(0).asInstanceOf[String])
      obj.draw(context)
    })
  }
}

