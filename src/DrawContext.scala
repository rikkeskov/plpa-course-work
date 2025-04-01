import java.awt.{Graphics, Shape}

class DrawContext(val graphics: Graphics) {
  var objects: Array[DrawObject[_]] = Array()
  var latestBoundingBox: Option[Shape] = None
  var errors: Array[(Int, String)] = Array()

  def addError(obj: DrawObject[_], error: String): Unit = {
    val index: Int = objects.indexOf(obj)
    errors :+  (index, error)
  }

}
