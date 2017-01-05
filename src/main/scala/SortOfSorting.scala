import scala.io.Source

/**
  * Created by Montana Ruth on 1/5/2017.
  */

object SortOfSorting extends App{
  val src = Source.stdin.getLines().takeWhile(_ != "0")

  while(src.hasNext){
    val n = src.next.toInt
    src.take(n).toSeq.sortWith((e1,e2) => e1.take(2) < e2.take(2)).foreach(println)
    println
  }
}
