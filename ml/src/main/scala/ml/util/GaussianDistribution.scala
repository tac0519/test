package ml.util

import scala.util.Random

class GaussianDistribution(mean: Double, variance: Double) extends Random {

  Random.setSeed(1)
  /*
   * TODO it seems to use Box-Muller transform
   * https://en.wikipedia.org/wiki/Box–Muller_transform
   */
  override def nextDouble(): Double = {
    val c = Math.sqrt(-2.0 * Math.log(Random.nextDouble()));
    if (Random.nextDouble() < 0.5)
      return c * Math.sin(2.0 * Math.PI * Random.nextDouble()) * variance + mean;
    return c * Math.cos(2.0 * Math.PI * Random.nextDouble()) * variance + mean;
  }

}