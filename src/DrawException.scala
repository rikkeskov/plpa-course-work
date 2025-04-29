case class DrawException(error: String, drawObject: DrawObject[_] = null) extends Throwable(error) {
}
