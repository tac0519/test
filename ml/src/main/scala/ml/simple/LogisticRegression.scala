package ml.simple

import ml.Data
import ml.util.RamdomGenerator
import scala.util.Random
import ml.util.GaussianDistribution

object LogisticRegression {

  //val trainSetting = new TrainSetting(2000, 50, 0.2)
  val random1 = new GaussianDistribution(-2.0, 0.0)
  val random2 = new GaussianDistribution(2.0, 0.0)
  val random3 = new GaussianDistribution(1.0, 0.0)

  private def dataArray(): Array[ClassifiedData] = {
    val data1 = new ClassifiedData(random1, random2, Array(1, 0, 0), 400, 60)
    val data2 = new ClassifiedData(random2, random1, Array(0, 1, 0), 400, 60)
    val data3 = new ClassifiedData(random3, random3, Array(0, 0, 1), 400, 60)
    Array(data1, data2, data3)
  }

}

// それぞれのデータ・セットはパターンに関係なくしたい。
class ClassifiedData(random1: Random, random2: Random, correctOutputs: Array[Int], trainSetSize: Int, testSetSize: Int) {
  val dataSet = Seq.fill(trainSetSize + testSetSize)(newData(random1, random2, correctOutputs)).toArray

  def newData(random1: Random, random2: Random, correctOutputs: Array[Int]): Data = {
    val inputs: Array[Double] = Array(random1.nextDouble(), random2.nextDouble());
    val outputs: Array[Double] = correctOutputs.map(f => f.toDouble)
    new Data(inputs, outputs);
  }

  def trainSet(): Array[Data] = {
    dataSet.slice(0, trainSetSize - 1)
  }

  def testSet(): Array[Data] = {
    dataSet.slice(trainSetSize, dataSet.size)
  }

}