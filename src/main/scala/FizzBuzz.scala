
/**
  * Created by Montana Ruth on 1/5/2017.
  */

object FizzBuzz extends App {
  def fizzBuzz(x: Long, y: Long)(i: Long): String =
    if(i % x == 0 && i % y == 0) "FizzBuzz"
    else if(i % x == 0) "Fizz"
    else if(i % y == 0) "Buzz"
    else i.toString
    val Array(x,y,n) = io.StdIn.readLine.split(" ").map(_.toLong)

    val fizzBuzzXY: Long => String = fizzBuzz(x,y)
    (1L to n).foreach(i => println(fizzBuzzXY(i)))
}
