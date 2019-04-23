package donmiguel

class LeConverterSpec extends UnitSpec {

  it should "read le" in {

    var v = CryptoUtil.hexToBytes("01000000")
    assert(LeConverter.readLongLE(v.iterator, 4, 0) == 1)


  }

  it should "read 32 bytes" in {
    var v = CryptoUtil.hexToBytes("813f79011acb80925dfe69b3def355fe914bd1d96a3f5f71bf8303c6a989c7d1")

    assert(CryptoUtil.bytesToHex(LeConverter.readByteArrayLE(v.iterator, 32, 0)) == "d1c789a9c60383bf715f3f6ad9d14b91fe55f3deb369fe5d9280cb1a01793f81")

  }

}