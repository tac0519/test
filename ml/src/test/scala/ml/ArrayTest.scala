package ml

object ArrayTest {
  def main(args: Array[String]): Unit = {
    val array: Array[Double] = new Array[Double](3);
    array(0) = 1
    array(1) = 2
    array(2) = 3
    val array2 = array.clone()
    array(1) = 4
    array.foreach(f => println(f))
    array2.foreach(f => println(f))
  }
}