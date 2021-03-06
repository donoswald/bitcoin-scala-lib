package donmiguel.tx

import com.sun.jna.platform.win32.WinDef.UINT
import donmiguel.UnitSpec
import donmiguel.util.CryptoUtil

class TxInSpec extends UnitSpec {

  it should "read tx_prev" in {
    var raw = "813f79011acb80925dfe69b3def355fe914bd1d96a3f5f71bf8303c6a989c7d1000000006b483045022100ed81ff192e75a3fd2304004dcadb746fa5e24c5031ccfcf21320b0277457c98f02207a986d955c6e0cb35d446a89d3f56100f4d7f67801c31967743a9c8e10615bed01210349fc4e631e3624a545de3f89f5d8684c7b8138bd94bdd531d2e213bf016b278afeffffff"

    var txIn = TxIn.parse(CryptoUtil.hexToBytes(raw).iterator)

    assert(txIn.prevIdx.intValue() == 0)
    assert(txIn.prevTx == "d1c789a9c60383bf715f3f6ad9d14b91fe55f3deb369fe5d9280cb1a01793f81")

  }

  it should "read script_sig" in {
    var raw = "813f79011acb80925dfe69b3def355fe914bd1d96a3f5f71bf8303c6a989c7d1000000006b483045022100ed81ff192e75a3fd2304004dcadb746fa5e24c5031ccfcf21320b0277457c98f02207a986d955c6e0cb35d446a89d3f56100f4d7f67801c31967743a9c8e10615bed01210349fc4e631e3624a545de3f89f5d8684c7b8138bd94bdd531d2e213bf016b278afeffffff"

    var txIn = TxIn.parse(CryptoUtil.hexToBytes(raw).iterator)

    assert(CryptoUtil.bytesToHex(txIn.scriptSig.serialize) == "6b483045022100ed81ff192e75a3fd2304004dcadb746fa5e24c5031ccfcf21320b0277457c98f02207a986d955c6e0cb35d446a89d3f56100f4d7f67801c31967743a9c8e10615bed01210349fc4e631e3624a545de3f89f5d8684c7b8138bd94bdd531d2e213bf016b278a")
  }

  it should "read sequence" in {
    var raw = "813f79011acb80925dfe69b3def355fe914bd1d96a3f5f71bf8303c6a989c7d1000000006b483045022100ed81ff192e75a3fd2304004dcadb746fa5e24c5031ccfcf21320b0277457c98f02207a986d955c6e0cb35d446a89d3f56100f4d7f67801c31967743a9c8e10615bed01210349fc4e631e3624a545de3f89f5d8684c7b8138bd94bdd531d2e213bf016b278afeffffff"

    var txIn = TxIn.parse(CryptoUtil.hexToBytes(raw).iterator)

    assert(txIn.sequence.longValue() == 4294967294L)
  }

  it should "input value" in {
    val fetcher = TxFetcher.load("tx.cache")

    var txIn = TxIn("d1c789a9c60383bf715f3f6ad9d14b91fe55f3deb369fe5d9280cb1a01793f81", new UINT())
    assert(txIn.value == 42505594)
  }

  it should "pub_key" in {
    val fetcher = TxFetcher.load("tx.cache")

    var txIn = TxIn("d1c789a9c60383bf715f3f6ad9d14b91fe55f3deb369fe5d9280cb1a01793f81", new UINT())
    assert(txIn.scriptPubkey.serialize.deep == CryptoUtil.hexToBytes("1976a914a802fc56c704ce87c42d7c92eb75e7896bdc41ae88ac").deep)
  }

  it should "serialize txIn" in {

    var raw = "813f79011acb80925dfe69b3def355fe914bd1d96a3f5f71bf8303c6a989c7d1000000006b483045022100ed81ff192e75a3fd2304004dcadb746fa5e24c5031ccfcf21320b0277457c98f02207a986d955c6e0cb35d446a89d3f56100f4d7f67801c31967743a9c8e10615bed01210349fc4e631e3624a545de3f89f5d8684c7b8138bd94bdd531d2e213bf016b278afeffffff"
    var txIn = TxIn.parse(CryptoUtil.hexToBytes(raw).iterator)
    assert(CryptoUtil.bytesToHex(txIn.serialize) == raw)

  }

}
