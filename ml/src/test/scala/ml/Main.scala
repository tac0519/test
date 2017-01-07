package ml

object Main {
  
  val patterns = 3

  
  
  def main(args: Array[String]): Unit = {
    val inputs:Array[Double] = new Array[Double](2)
    inputs(0) = 1
    inputs(1) = 2
    val outputs:Array[Double] = new Array[Double](3)
    outputs(0) = 3
    outputs(1) = 4
    outputs(2) = 5
    val data:Data = new Data(inputs, outputs)
    val data2:Data = data.clone()
    data.outputs(1) = 6
    println(data2.outputs(1))
    println(data.outputs(1))
    
    
  }
}