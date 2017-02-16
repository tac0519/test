package ml.deep.pre

import ml.simple.Perceptron
import ml.{Data, Layers, NeuralNetwork}

object RestrictedBoltzmannMachines {


  def sample(source: Data, target: Data): Unit ={
    for (o <- 0 until source.outputs.size) {

    }


  }

  def mean(layers:Layers, up:Boolean): Double ={
//    for (h <- 0 until layers.hiddenData.size) {
//      for (v <- 0 until layers.visibleData.size) {
//        data.outputs(o) += weights(o)(i) * data.inputs(i)
//      }
//      data.outputs(o) += bias(o)
//    }


    null
  }



  def main(args: Array[String]): Unit = {

  }
}

class RestrictedBoltzmannMachines(layers: Layers) {

  def train(): Array[Array[Double]] = {
    null
  }

  // CD-k in RBM
  def contrastiveDivergence(learningRate:Double, k:Int): Unit = {


  }

  def gibbsHVH(): Unit = {

  }

  def sampleHidden(): Unit = {

  }

  def propdown(): Unit = {

  }

  def reconstruct(): Unit = {

  }


}