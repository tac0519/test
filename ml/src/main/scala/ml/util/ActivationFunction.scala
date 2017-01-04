package ml.util

class ActivationFunction(name: String) {

  val activate = (x: Double) => func(x, true);
  val deactivate = (x: Double) => func(x, false);

  private def func(x: Double, isActivate: Boolean): Double = {
    (name) match {
      case ("sigmoid") => if (isActivate) 1 / (1 + Math.pow(Math.E, -x)) else x * (1 - x);
      case ("tanh")    => if (isActivate) Math.tanh(x) else 1 - x * x;
      case ("ReLU")    => if (isActivate) { if (x > 0) x else 0 } else { if (x > 0) 1 else 0 };
    }
  }

}

