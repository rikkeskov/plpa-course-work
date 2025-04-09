import java.awt.{Color, Graphics}


class BoundingBox(text: String) extends DrawObject[Array[Int]] {

  override val arguments: Array[Int] = text match {
    case s"($arg1 $arg2) ($arg3 $arg4)" => Array(arg1.toInt, arg2.toInt, arg3.toInt, arg4.toInt)
    case _ => throw new MatchException(s"Cant match arguments to $command", null)
  }
  override val color: Color = new Color(0, 0, 0)
  override val fill: Color = new Color(0, 0, 0,0)

  override def draw(context: DrawContext): Unit = {
    context.graphics.drawRect(arguments(0), arguments(1), arguments(0) - arguments(2), arguments(1) - arguments(3))
  }
}
