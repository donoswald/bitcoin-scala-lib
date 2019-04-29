package donmiguel.script

import java.util

import donmiguel.script.OpCode.{decode, encode, toBool}

abstract class OpValue(code: Int) {
  def execute(stack: util.LinkedList[Array[Byte]]): Boolean


}

object OpValue {
  def OP_0 = new OpValue(0) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      stack.push(encode(0))
      true
    }
  }

  val OP_FALSE = OP_0
  val OP_1NEGATE = new OpValue(79) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      stack.push(encode(-1))
      true
    }
  }
  val OP_1 = new OpValue(81) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      stack.push(encode(1))
      true
    }
  }

  val OP_TRUE = OP_1

  val OP_2 = new OpValue(82) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      stack.push(encode(2))
      true
    }
  }
  val OP_3 = new OpValue(83) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      stack.push(encode(3))
      true
    }
  }
  val OP_4 = new OpValue(84) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      stack.push(encode(4))
      true
    }
  }
  val OP_5 = new OpValue(85) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      stack.push(encode(5))
      true
    }
  }
  val OP_6 = new OpValue(86) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      stack.push(encode(6))
      true
    }
  }
  val OP_7 = new OpValue(87) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      stack.push(encode(7))
      true
    }
  }
  val OP_8 = new OpValue(88) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      stack.push(encode(8))
      true
    }
  }
  val OP_9 = new OpValue(89) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      stack.push(encode(9))
      true
    }
  }
  val OP_10 = new OpValue(90) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      stack.push(encode(10))
      true
    }
  }
  val OP_11 = new OpValue(91) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      stack.push(encode(11))
      true
    }
  }
  val OP_12 = new OpValue(92) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      stack.push(encode(12))
      true
    }
  }
  val OP_13 = new OpValue(93) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      stack.push(encode(13))
      true
    }
  }
  val OP_14 = new OpValue(94) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      stack.push(encode(14))
      true
    }
  }
  val OP_15 = new OpValue(95) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      stack.push(encode(15))
      true
    }
  }
  val OP_16 = new OpValue(96) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      stack.push(encode(16))
      true
    }
  }
  // control
  val OP_NOP = new OpValue(97) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = true
  }
  val OP_VERIFY = new OpValue(105) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 1)
        return false

      val elem = stack.pop()
      if (decode(elem) == 0)
        return false
      true
    }
  }
  val OP_RETURN = new OpValue(106) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = false
  }

  // stack ops
  val OP_TOALTSTACK = new OpValue(107) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = throw new NotImplementedError()

    def execute(stack: util.Stack[Any], altstack: util.Deque[Any]): Boolean = {
      if (stack.size() < 1)
        return false
      altstack.push(stack.pop())
      true
    }
  }
  val OP_FROMALTSTACK = new OpValue(108) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = throw new NotImplementedError()

    def execute(stack: util.Stack[Any], altstack: util.Deque[Any]): Boolean = {
      if (altstack.size() < 1)
        return false
      stack.push(altstack.pop())
      true
    }

  }
  val OP_2DROP = new OpValue(109) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 2)
        return false
      stack.pop()
      stack.pop()
      true
    }
  }
  val OP_2DUP = new OpValue(110) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 2)
        return false
      val e0 = stack.get(0)
      val e1 = stack.get(1)
      stack.push(e1)
      stack.push(e0)
      true
    }
  }
  val OP_3DUP = new OpValue(111) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 3)
        return false
      val e0 = stack.get(0)
      val e1 = stack.get(1)
      val e2 = stack.get(2)
      stack.push(e2)
      stack.push(e1)
      stack.push(e0)
      true
    }
  }
  val OP_2OVER = new OpValue(112) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 4)
        return false
      val e2 = stack.get(2)
      val e3 = stack.get(3)
      stack.push(e3)
      stack.push(e2)
      true
    }
  }

  val OP_2ROT = new OpValue(113) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 6)
        return false
      val e4 = stack.remove(4)
      val e5 = stack.remove(4)
      stack.push(e5)
      stack.push(e4)
      true
    }
  }

  val OP_2SWAP = new OpValue(114) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 4)
        return false
      val e2 = stack.remove(2)
      val e3 = stack.remove(2)
      stack.push(e3)
      stack.push(e2)
      true
    }
  }
  val OP_IFDUP = new OpValue(115) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 1)
        return false
      if (decode(stack.peek()) != 0)
        stack.push(stack.peek())
      true
    }
  }
  val OP_DEPTH = new OpValue(116) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      stack.push(encode(stack.size()))
      true
    }
  }
  val OP_DROP = new OpValue(117) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 1)
        return false
      stack.pop()
      true
    }
  }
  val OP_DUP = new OpValue(118) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 1)
        return false
      stack.push(stack.peek())
      true
    }
  }
  val OP_NIP = new OpValue(119) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 2)
        return false
      stack.remove(1)
      true
    }
  }
  val OP_OVER = new OpValue(120) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 2)
        return false
      stack.push(stack.get(1))
      true
    }
  }
  val OP_PICK = new OpValue(121) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 1)
        return false

      val n = decode(stack.pop()).asInstanceOf[Int]
      if (stack.size() < n + 1)
        return false

      stack.push(stack.get(n))
      true
    }
  }
  val OP_ROLL = new OpValue(122) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 1)
        return false

      val n = decode(stack.pop()).asInstanceOf[Int]
      if (stack.size() < n + 1)
        return false

      stack.push(stack.remove(n))
      true
    }
  }
  val OP_ROT = new OpValue(123) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 3)
        return false

      val e0 = stack.pop()
      val e1 = stack.pop()
      val e2 = stack.pop()

      stack.push(e1)
      stack.push(e0)
      stack.push(e2)
      true
    }
  }
  val OP_SWAP = new OpValue(124) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 2)
        return false

      var e0 = stack.pop()
      var e1 = stack.pop()
      stack.push(e0)
      stack.push(e1)
      true
    }
  }
  val OP_TUCK = new OpValue(125) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 2)
        return false

      var e0 = stack.peek()
      stack.add(2, e0)
      true
    }

  }


  // splice ops
  val OP_CAT = new OpValue(126) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = throw new NotImplementedError()
  }
  val OP_SUBSTR = new OpValue(127) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = throw new NotImplementedError()
  }
  val OP_LEFT = new OpValue(128) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = throw new NotImplementedError()
  }
  val OP_RIGHT = new OpValue(129) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = throw new NotImplementedError()
  }

  val OP_SIZE = new OpValue(130) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 1)
        return false
      stack.push(encode(stack.peek().length))
      true
    }
  }

  // bit logic
  val OP_INVERT = new OpValue(131) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = throw new NotImplementedError()
  }
  val OP_AND = new OpValue(132) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = throw new NotImplementedError()
  }
  val OP_OR = new OpValue(133) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = throw new NotImplementedError()
  }
  val OP_XOR = new OpValue(134) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = throw new NotImplementedError()
  }

  val OP_EQUAL = new OpValue(135) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 2)
        return false
      val e0 = stack.pop()
      val e1 = stack.pop()
      if (e0.deep == e1.deep)
        stack.push(encode(1))
      else
        stack.push(encode(0))

      true
    }
  }
  val OP_EQUALVERIFY = new OpValue(136) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 2)
        return false
      val e0 = stack.pop()
      val e1 = stack.pop()
      if (e0.deep == e1.deep)
        stack.push(encode(1))
      else
        stack.push(encode(0))

      OpValue.OP_VERIFY.execute(stack)

    }
  }

  // numeric
  val OP_1ADD = new OpValue(139) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 1)
        return false
      val n = decode(stack.pop())
      stack.push(encode(n + 1))
      true
    }
  }

  val OP_1SUB = new OpValue(140) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 1)
        return false
      val n = decode(stack.pop())
      stack.push(encode(n - 1))
      true
    }
  }

  val OP_2MUL = new OpValue(141) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = throw new NotImplementedError()
  }
  val OP_2DIV = new OpValue(142) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = throw new NotImplementedError()
  }

  val OP_NEGATE = new OpValue(143) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 1)
        return false

      val n = decode(stack.pop())
      stack.push(encode(-n))
      true
    }
  }
  val OP_ABS = new OpValue(144) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 1)
        return false

      val n = decode(stack.pop())
      stack.push(encode(if (n < 0) -n else n))
      true
    }
  }
  val OP_NOT = new OpValue(145) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 1)
        return false

      if (decode(stack.pop) == 0)
        stack.push(encode(1))
      else
        stack.push(encode(0))

      true
    }
  }
  val OP_0NOTEQUAL = new OpValue(146) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 1)
        return false

      if (decode(stack.pop) == 0)
        stack.push(encode(0))
      else
        stack.push(encode(1))

      true
    }
  }
  val OP_ADD = new OpValue(147) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 2)
        return false

      val b = decode(stack.pop())
      val a = decode(stack.pop())
      stack.push(encode(a + b))
      true
    }
  }

  val OP_SUB = new OpValue(148) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 2)
        return false

      val b = decode(stack.pop())
      val a = decode(stack.pop())
      stack.push(encode(a - b))
      true
    }
  }

  val OP_MUL = new OpValue(149) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = throw new NotImplementedError()
  }
  val OP_DIV = new OpValue(150) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = throw new NotImplementedError()
  }
  val OP_MOD = new OpValue(151) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = throw new NotImplementedError()
  }
  val OP_LSHIFT = new OpValue(152) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = throw new NotImplementedError()
  }
  val OP_RSHIFT = new OpValue(153) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = throw new NotImplementedError()
  }

  val OP_BOOLAND = new OpValue(154) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 2)
        return false

      val e0 = stack.pop()
      val e1 = stack.pop()
      if (toBool(e0) && toBool(e1))
        stack.push(encode(1))
      else
        stack.push(encode(0))

      true
    }
  }
  val OP_BOOLOR = new OpValue(155) {
    override def execute(stack: util.LinkedList[Array[Byte]]): Boolean = {
      if (stack.size() < 2)
        return false

      val e0 = stack.pop()
      val e1 = stack.pop()
      if (toBool(e0) || toBool(e1))
        stack.push(encode(1))
      else
        stack.push(encode(0))

      true
    }
  }


}
