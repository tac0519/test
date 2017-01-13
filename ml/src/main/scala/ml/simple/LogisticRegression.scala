package ml.simple

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

import ml.Data
import ml.NeuralNetwork
import ml.util.ActivationFunction
import ml.util.Evaluater
import ml.util.GaussianDistribution

object LogisticRegression {

  // train setting
  val epochs = 2000
  var learningRate = 0.2
  val minibatchSize = 50
  val trainSetSize = 400
  val testSetSize = 60
  // create LogisticRegression and get trainSet and testSet for each classes
  val logisticRegression = new LogisticRegression(trainSetSize, testSetSize)
  val trainSet = logisticRegression.trainSet.toArray
  val testSet = logisticRegression.testSet.toArray
  // define neuralNetwork
  val neuralNetwork = new NeuralNetwork(ActivationFunction.softmax)

  def main(args: Array[String]): Unit = {
    train()
    val predictSet = Data.copy(testSet)
    test(predictSet)
    Evaluater.evaluate(testSet, predictSet)
  }

  def train() {
    val sets = minibatch(trainSet)
    for (i <- 0 to epochs) {
      sets.foreach(dataSet => {
        dataSet.foreach(data => { neuralNetwork.train(data, learningRate / minibatchSize) })
      })
      learningRate *= 0.95
    }
  }

  private def test(predictSet: Array[Data]) {
    predictSet.foreach(data => {
      neuralNetwork.activate(data)
      predict(data)
    })
  }

  def minibatch(dataSet: Array[Data]): Array[Array[Data]] = {
    val sets = new Array[Array[Data]](dataSet.size / minibatchSize)
    for (i <- 0 to sets.size - 1) {
      sets(i) = dataSet.slice(i * minibatchSize, (i + 1) * minibatchSize - 1)
    }
    sets
  }

  private def predict(data: Data) {
    val max = data.outputs.indexOf(data.outputs.max)
    data.outputs.map(output => 0)
    data.outputs(max) = 1
  }

}

/**
 * class 1 : in1 ~ (N( -2.0, 1.0 ), N( +2.0, 1.0 )), out1 = (1, 0, 0)
 * class 2 : in2 ~ (N( +2.0, 1.0 ), N( -2.0, 1.0 )), out2 = (1, 0, 0)
 * class 3 : in3 ~ (N(  0.0, 1.0 ), N(  0.0, 1.0 )), out3 = (1, 0, 0)
 */
class LogisticRegression(trainSetSize: Int, testSetSize: Int) {

  val trainSet = new ArrayBuffer[Data]
  val testSet = new ArrayBuffer[Data]

  private val random1 = new GaussianDistribution(-2.0, 1.0)
  private val random2 = new GaussianDistribution(2.0, 1.0)
  private val random3 = new GaussianDistribution(0.0, 1.0)

  makeData(random1, random2, Array(1, 0, 0), trainSetSize, testSetSize)
  makeData(random2, random1, Array(0, 1, 0), trainSetSize, testSetSize)
  makeData(random3, random3, Array(0, 0, 1), trainSetSize, testSetSize)

  private def makeData(random1: Random, random2: Random, correctOutputs: Array[Int], trainSetSize: Int, testSetSize: Int) {
    for (i <- 0 to trainSetSize - 1) trainSet += newData(random1, random2, correctOutputs)
    for (i <- 0 to testSetSize - 1) testSet += newData(random1, random2, correctOutputs)
  }

  private def newData(random1: Random, random2: Random, correctOutputs: Array[Int]): Data = {
    val inputs: Array[Double] = Array(random1.nextDouble(), random2.nextDouble());
    val outputs: Array[Double] = correctOutputs.map(f => f.toDouble)
    new Data(inputs, outputs);
  }

}
