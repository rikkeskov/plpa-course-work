import java.awt.{BasicStroke, Rectangle, Shape}
import java.awt.BasicStroke.{CAP_ROUND, JOIN_ROUND}


class BoundingBox(text: String) extends DrawObject[Array[Int]] with Object with Colorable with Fillable {

  override var arguments: Array[Int] = text match {
    case s"($arg1 $arg2) ($arg3 $arg4)" => Array(arg1.toInt, arg2.toInt, arg3.toInt, arg4.toInt)
    case s => throw DrawException(s"Cant match arguments to $command: $s", this)
  }

  override def draw(context: DrawContext): Unit = {
    val g = context.graphics
    val shape: Shape = new Rectangle(arguments(0) * 10, arguments(1) * 10, (arguments(2) - arguments(0)) * 10, (arguments(3) - arguments(1)) * 10)

    g.setColor(fill)
    g.fill(shape)
    g.setColor(color)
    g.setStroke(new BasicStroke(2f, CAP_ROUND, JOIN_ROUND, 1.0f, Array(3.1f, 5.1f), 0.5f))
    g.draw(shape)
    g.setClip(shape)
    context.latestBoundingBox = Some(shape)

    // Reset stroke for next drawing
    g.setStroke(new BasicStroke(1.5f))
  }
}
