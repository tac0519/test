package ml.util

import ml.Data

object Evaluater {

  def evaluate(actualDataSet: Array[Data], predictDataSet: Array[Data]) {
    val evaluater = new Evaluater(actualDataSet, predictDataSet, actualDataSet(0).outputs.size)
    evaluater.makeConfusionMatrix()
    evaluater.evaluate()
    evaluater.report()
  }

}

class Evaluater(actualDataSet: Array[Data], predictDataSet: Array[Data], classes: Int) {
  var accuracy = 0.0
  val precision = new Array[Double](classes)
  val recall = new Array[Double](classes)

  // create confusionMatrix
  val confusionMatrix = Array.ofDim[Int](classes, classes)

  def makeConfusionMatrix() {
    for (i <- 0 to actualDataSet.size - 1) {
      var actualOutputs = actualDataSet(i).outputs.map(output => output.toInt)
      var predictOutputs = predictDataSet(i).outputs.map(output => output.toInt)
      confusionMatrix(actualOutputs.indexOf(1))(predictOutputs.indexOf(1)) += 1
    }
  }

  /**
   * TODO add comment.
   */
  def evaluate() {
    var colTotal = 0.0
    var rowTotal = 0.0
    for (i <- 0 to classes - 1) {
      for (j <- 0 to classes - 1) {
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
    for (i <- 0 to precision.size - 1) {
      printf(" class %d: %.1f %%\n", i + 1, precision(i) * 100)
    }
    println("Recall:")
    for (i <- 0 to recall.size - 1) {
      printf(" class %d: %.1f %%\n", i + 1, recall(i) * 100)
    }

  }

}