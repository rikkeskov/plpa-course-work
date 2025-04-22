import java.awt.{Color, Graphics, Shape, Rectangle}


class BoundingBox(text: String) extends DrawObject[Array[Int]] {

  override var arguments: Array[Int] = text match {
    case s"($arg1 $arg2) ($arg3 $arg4)" => Array(arg1.toInt, arg2.toInt, arg3.toInt, arg4.toInt)
    case s => throw DrawException(s"Cant match arguments to $command: $s", this)
  }
  override val shape: Shape = new Rectangle(arguments(0), arguments(1), arguments(2) - arguments(0), arguments(3) - arguments(1))

  override val color: Color = new Color(0, 0, 0)
  override val fill: Color = new Color(0, 0, 0,0)

  override def draw(context: DrawContext): Unit = {
      context.graphics.draw(shape)
      context.graphics.setClip(shape)
      context.latestBoundingBox = Some(shape)

  }
}
