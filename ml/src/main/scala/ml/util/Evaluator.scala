package ml.util

import ml.Data

object Evaluator {

  def evaluate(actualDataSet: Array[Data], predictDataSet: Array[Data]) {
    val evaluator = new Evaluator(actualDataSet, predictDataSet, actualDataSet(0).outputs.size)
    evaluator.makeConfusionMatrix()
    evaluator.evaluate()
    evaluator.report()
  }

}

class Evaluator(actualDataSet: Array[Data], predictDataSet: Array[Data], classes: Int) {
  var accuracy = 0.0
  val precision = new Array[Double](classes)
  val recall = new Array[Double](classes)

  // create confusionMatrix
  val confusionMatrix = Array.ofDim[Int](classes, classes)

  def makeConfusionMatrix() {
    for (i <- 0 until actualDataSet.size) {
      val actualOutputs = actualDataSet(i).outputs.map(output => output.toInt)
      val predictOutputs = predictDataSet(i).outputs.map(output => output.toInt)
      confusionMatrix(actualOutputs.indexOf(1))(predictOutputs.indexOf(1)) += 1
    }
  }

  /**
    * TODO add comment.
    */
  def evaluate() {
    for (i <- 0 until classes) {
      var colTotal = 0.0
      var rowTotal = 0.0
      for (j <- 0 until classes) {
        if (i == j) {
          accuracy += confusionMatrix(i)(j)
          precision(i) += confusionMatrix(j)(i)
          recall(i) += confusionMatrix(i)(j)
        }
        colTotal += confusionMatrix(j)(i)
        rowTotal += confusionMatrix(i)(j)
      }
      precision(i) /= colTotal
      recall(i) /= rowTotal
    }
    accuracy /= actualDataSet.size
  }

  private def report() {
    println("------------------------------------")
    println("Logistic Regression model evaluation")
    println("------------------------------------")
    printf("Accuracy: %.1f %%\n", accuracy * 100)
    println("Precision:")
    for (i <- 0 until precision.size) {
      printf(" class %d: %.1f %%\n", i + 1, precision(i) * 100)
    }
    println("Recall:")
    for (i <- 0 until recall.size) {
      printf(" class %d: %.1f %%\n", i + 1, recall(i) * 100)
    }

  }

}