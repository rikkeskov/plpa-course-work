import java.awt.Color

trait Colorable

trait Fillable

trait Action {
  def draw(context: DrawContext, objects: Array[(DrawObject[_], Int)]): Unit
}
trait Object {
  def draw(context: DrawContext): Unit
}

abstract class DrawObject[T]{
  var arguments: T
  var color: Color = new Color(0, 0, 0)
  var fill: Color = new Color(0, 0, 0, 0)

  val command: String = getClass.getName.tail.foldLeft(getClass.getName.head.toString) {
    case (acc, char) if char.isUpper => acc + "-" + char
    case (acc, char) => acc + char
  }.toUpperCase
};
