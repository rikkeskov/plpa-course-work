import java.awt.{Color, Graphics}


class BoundingBox(text: String) extends DrawObject[Array[Int]] {

  override var arguments: Array[Int] = Array()
  var error: String = ""

  text match {
    case s"($arg1 $arg2) ($arg3 $arg4)" => arguments = Array(arg1.toInt, arg2.toInt, arg3.toInt, arg4.toInt)
    case s => error = s"Cant match arguments to $command: $s"
  }

  override val color: Color = new Color(0, 0, 0)
  override val fill: Color = new Color(0, 0, 0,0)

  override def draw(context: DrawContext): Unit = {
    if(error.nonEmpty){
      context.addError(this, error)
    } else {
      context.graphics.drawRect(arguments(0), arguments(1), arguments(0) - arguments(2), arguments(1) - arguments(3))
    }
  }
}
