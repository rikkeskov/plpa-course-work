import java.awt.Shape

class Rectangle(text: String) extends DrawObject[Array[Int]] with Object with Colorable with Fillable {

  override var arguments: Array[Int] = text match {
    case s"($arg1 $arg2) ($arg3 $arg4)" => Array(arg1.toInt, arg2.toInt, arg3.toInt, arg4.toInt)
    case _ => throw DrawException(s"Cant match arguments to $command", this)
  }

  override def draw(context: DrawContext): Unit = {
    val g = context.graphics
    val shape: Shape = new java.awt.Rectangle(arguments(0) * 10, arguments(1) * 10, (arguments(2) - arguments(0)) * 10, (arguments(3) - arguments(1)) * 10)

    g.setColor(fill)
    g.fill(shape)
    g.setColor(color)
    g.draw(shape)
  }
}