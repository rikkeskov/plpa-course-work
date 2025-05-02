import java.awt.{Color, Shape}

trait Colorable

trait Fillable

trait Action

abstract class DrawObject[T]{
  var arguments: T
  var color: Color = new Color(0, 0, 0)
  var fill: Color = new Color(0, 0, 0, 0)

  val command: String = getClass.getName.tail.foldLeft(getClass.getName.head.toString) {
    case (acc, char) if char.isUpper => acc + "-" + char
    case (acc, char) => acc + char
  }.toUpperCase

  def draw(context: DrawContext): Unit
};
