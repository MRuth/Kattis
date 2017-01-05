import scala.io.Source

/**
  * Created by Montana Ruth on 1/5/2017.
  */

object lineup extends App {
  val src = Source.stdin.getLines()
  val n = src.next().toInt
  val names = src.take(n).toSeq

  println(
    if(names == names.sorted) "INCREASING"
    else if(names == names.sortWith(_ > _)) "DECREASING"
    else "NEITHER"
  )
}
