package ml.util
import ml.Data
/**
  * Created by tac on 2017/01/12.
  */
object RamdomGeneratorTest {

  def main(args: Array[String]): Unit = {
    val data = new Data(Array(0.2378, 2.578921),Array(1.0, 0.0,0.0))
    val aa = RamdomGenerator.fill2DRandom(data.outputs.size, data.inputs.size)
    println(aa)
  }
}
