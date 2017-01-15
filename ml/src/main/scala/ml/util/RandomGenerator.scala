package ml.util

import scala.util.Random

object RandomGenerator extends RandomGenerator {

  def gaussianDistribution(mean: Double, variance: Double): RandomGenerator = {
    val gaussianDistribution = new GaussianDistribution(mean, variance)
    new RandomGenerator(gaussianDistribution)
  }
}

class RandomGenerator(val self: scala.util.Random) {
  def this() = this(Random)

  def binomial(n: Int, p: Double): Int = {
    var c = 0;
    if (p < 0 || p > 1) c;
    while (c < n) {
      c = cal(c, p);
    }
    c;
  }

  def cal(c: Int, p: Double): Int = {
    if (self.nextDouble() < p)
      return c + 1;
    return c;
  }

  def fillRandom(count: Int): Array[Double] = {
    Seq.fill(count)(self.nextDouble()).toArray
  }

  def fill2DRandom(outer: Int, inner: Int): Array[Array[Double]] = {
    Seq.fill(outer)(fillRandomMinMax(inner)).toArray
  }

  def fillRandomMinMax(count: Int): Array[Double] = {
    val weightRange = 1.0 / count
    Seq.fill(count)(uniform(-weightRange, weightRange)).toArray
  }

  def uniform(min: Double, max: Double): Double = {
    self.nextDouble() * (max - min) + min;
  }

}