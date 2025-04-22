import java.awt.{Graphics2D, Shape}

class DrawContext(val graphics: Graphics2D) {
  var objects: Array[DrawObject[_]] = Array()
  var latestBoundingBox: Option[Shape] = None
  var errors: Array[(Int, String)] = Array()

  def addError(obj: DrawObject[_], error: String): Unit = {
    val index: Int = objects.indexOf(obj)
    errors :+  (index, error)
  }

}
