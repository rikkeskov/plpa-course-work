class Circle(text: String) extends DrawObject[Array[Int]] {

  override var arguments: Array[Int] = text match {
    case s"($arg1 $arg2) $arg3" => Array(arg1.toInt, arg2.toInt, arg3.toInt)
    case _ => throw DrawException(s"Cant match arguments to $command", this)
  }

  private val Location = (arguments(0), arguments(1))
  private val Radius = arguments(2)

  override def draw(context: DrawContext): Unit = {
    context.graphics.drawOval(Location._1, Location._2, Radius, Radius)
  }
}
