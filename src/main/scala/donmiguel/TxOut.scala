package donmiguel

case class TxOut(amount: Long, script_pubkey: Script) {

}

object TxOut {

  def parse(it: Iterator[Byte]): TxOut = {

    var amount = LeConverter.readLongLE(it, 8)
    var script = Script.parse(it)

    new TxOut(amount, script)
  }
}
