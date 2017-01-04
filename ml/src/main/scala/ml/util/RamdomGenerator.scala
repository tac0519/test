package ml.util

import scala.util.Random

object RamdomGenerator extends RamdomGenerator {
  
  def gaussianDistribution(mean: Double, variance: Double): RamdomGenerator = {
    val gaussianDistribution = new GaussianDistribution(mean, variance)
    new RamdomGenerator(gaussianDistribution)
  }
}

class RamdomGenerator(val self: scala.util.Random) {
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
    var array2D: Array[Array[Double]] = Array.ofDim[Double](outer, inner)
    Seq.fill(outer)(fillRandomMinMax(inner)).toArray
  }

  def fillRandomMinMax(count: Int): Array[Double] = {
    val weightRange = 1 / count
    Seq.fill(count)(uniform(-weightRange, weightRange)).toArray
  }

  def uniform(min: Double, max: Double): Double = {
    self.nextDouble() * (max * min) + min;
  }

}