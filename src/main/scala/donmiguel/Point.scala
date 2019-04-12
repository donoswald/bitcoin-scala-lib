package donmiguel

class Point(_x: Option[Element], _y: Option[Element], _a: Element, _b: Element) {
  require(_x != null)
  require(_y != null)

  if (_x != None && _y != None) {
    assert(_y.get ** 2 == _x.get ** 3 + _a * _x.get + _b, "the point is not on the curve")
  } else {
    require(_x == None && _y == None, "either x and y must be None")
  }

  val x = _x
  val y = _y
  val a = _a
  val b = _b


  def ==(that: Point): Boolean = {

    //Fixme this.x.getOrElse(None) didn't work!!!
    if (this.x == None && this.y == None) {
      return this.x == that.x &&
        this.y == that.y &&
        this.a == that.a &&
        this.b == that.b
    }

    this.x.get == that.x.get &&
      this.y.get == that.y.get &&
      this._a == that.a &&
      this.b == that.b
  }

  def !=(that: Point): Boolean = {
    !(this == that)
  }

  def +(that: Point): Point = {
    require(this.a == that.a && this.b == that.b, "the points are not on the same curve")

    //Case 0.0: self is the point at infinity, return other
    if (this.x == None && y == None) {
      return that
    }
    //Case 0.1: other is the point at infinity, return self
    if (that.x == None && that.y == None) {
      return this
    }

    //Case 1: self.x == other.x, self.y != other.y
    //Result is point at infinity
    if (this.x.get == that.x.get && this.y.get != that.y.get) {
      return new Point(None, None, this.a, this.b)
    }
    //Case 2: self.x != other.x
    //Formula (x3,y3)==(x1,y1)+(x2,y2)
    //s=(y2-y1)/(x2-x1)
    //x3=s**2-x1-x2
    //y3=s*(x1-x3)-y1
    if (this.x.get != that.x.get) {

      var s = (that.y.get - this.y.get) / (that.x.get - this.x.get)
      var x = s ** 2 - this.x.get - that.x.get
      var y = s * (this.x.get - x) - this.y.get

      Option.apply(x)
      return new Point(Some(x), Some(y), this.a, this.b)
    }

    //Case 4: if we are tangent to the vertical line,
    //we return the point at infinity
    //note instead of figuring out what 0 is for each type
    //we just use 0 * self.x

    if (this == that && this.y.get == this.x.get * 0) {
      return new Point(None, None, this.a, this.b)
    }

    //Case 3: self == other
    //Formula (x3,y3)=(x1,y1)+(x1,y1)
    //s=(3*x1**2+a)/(2*y1)
    //x3=s**2-2*x1
    //y3=s*(x1-x3)-y1
    if (this == that) {
      var s = ((this.x.get ** 2) * 3 + this.a) / (this.y.get * 2)
      var x = s ** 2 - this.x.get * 2
      var y = s * (this.x.get - x) - this.y.get
      return new Point(Some(x), Some(y), this.a, this.b)
    }
    throw new IllegalStateException("should not reach here")
  }

  def *(coefficient: Int): Point = {
    var coef = coefficient
    var cur = this
    var result = new Point(None, None, this.a, this.b)
    while (coef != 0) {
      if ((coef & 1) == 1) {
        result += cur
      }
      cur += cur
      coef >>= 1
    }
    result
  }

  override def toString: String = StringBuilder.newBuilder + "x: " + x + " ,y: " + y + " ,a: " + a + " ,b: " + b


}
