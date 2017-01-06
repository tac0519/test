package ml.simple

import ml.data.Data
import ml.util.RamdomGenerator
import scala.util.Random
import ml.util.GaussianDistribution
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.ArrayBuffer

object LogisticRegression {

  //val trainSetting = new TrainSetting(2000, 50, 0.2)
  val logisticRegression = new LogisticRegression()
  


}

class LogisticRegression {

  val random1 = new GaussianDistribution(-2.0, 0.0)
  val random2 = new GaussianDistribution(2.0, 0.0)
  val random3 = new GaussianDistribution(1.0, 0.0)

  val dataSet = new ArrayBuffer[Data]
  val trainSet = new ArrayBuffer[Data]
  val testSet = new ArrayBuffer[Data]

  def makeData(trainSetSize: Int, testSetSize: Int) {
    makeData(random1, random2, Array(1, 0, 0), trainSetSize, testSetSize)
    makeData(random2, random1, Array(0, 1, 0), trainSetSize, testSetSize)
    makeData(random3, random3, Array(0, 0, 1), trainSetSize, testSetSize)
  }
  
  def makeData(random1: Random, random2: Random, correctOutputs: Array[Int], trainSetSize: Int, testSetSize: Int){
      for (i <- 0 to trainSetSize) trainSet += newData(random1, random2, correctOutputs)
      dataSet.appendAll(trainSet)
      for (i <- 0 to testSetSize) testSet += newData(random1, random2, correctOutputs)
      dataSet.appendAll(testSet)
  }

  def newData(random1: Random, random2: Random, correctOutputs: Array[Int]): Data = {
    val inputs: Array[Double] = Array(random1.nextDouble(), random2.nextDouble());
    val outputs: Array[Double] = correctOutputs.map(f => f.toDouble)
    new Data(inputs, outputs);
  }

}
