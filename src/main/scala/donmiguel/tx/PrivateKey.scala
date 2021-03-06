package donmiguel.tx

import donmiguel.crypto.Secp256k1
import donmiguel.util.CryptoUtil
import org.bouncycastle.crypto.digests.SHA256Digest
import org.bouncycastle.crypto.params.ECPrivateKeyParameters
import org.bouncycastle.crypto.signers.{ECDSASigner, HMacDSAKCalculator}

class PrivateKey(val privKey: BigInt) {
  val point = Secp256k1.G * privKey

  def sign(message: String): Signature = {
    sign(message.getBytes("UTF-8"))
  }

  def sign(z: Array[Byte]): Signature = {
    val hash = Secp256k1.doubleDigest(z)
    val signer = new ECDSASigner(new HMacDSAKCalculator(new SHA256Digest()))
    val privKeyParams = new ECPrivateKeyParameters(privKey.bigInteger, Secp256k1.ecParams)
    signer.init(true, privKeyParams)
    val sigs = signer.generateSignature(hash)

    new Signature(BigInt(sigs(0)), BigInt(sigs(1)))
  }

  def wif(compressed: Boolean, testnet: Boolean): String = {

    var secret = privKey.toByteArray
    var i = 0
    while (secret(i) == 0) {
      i = i + 1
    }

    if (secret.length - i > 32) throw new RuntimeException("key too large")

    if (i > 0) {
      var end = if (secret.length > i + 32) i + 32 else secret.length
      secret = secret.slice(i, end)
    } else if (secret.length < 32) {
      var fill = Array.ofDim[Byte](32 - secret.length)
      secret = Array.concat(fill, secret)
    }

    var b1: Byte = if (testnet) 0xef.asInstanceOf[Byte] else 0x80.asInstanceOf[Byte]
    var prefix = Array(b1)
    var b2: Byte = if (compressed) 0x01 else 0x00
    var suffix = Array(b2)
    if (suffix(0) == 0x00) {
      return CryptoUtil.checksumBase58(Array.concat(prefix, secret))

    }

    return CryptoUtil.checksumBase58(Array.concat(prefix, secret, suffix))
  }

}
