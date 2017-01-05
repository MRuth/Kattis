import scala.io.Source

/**
  * Created by Montana Ruth on 1/5/2017.
  */

object DanceRecital extends App {
  val src = Source.stdin.getLines()
  val in = src.take(src.next().toInt).toList

}
