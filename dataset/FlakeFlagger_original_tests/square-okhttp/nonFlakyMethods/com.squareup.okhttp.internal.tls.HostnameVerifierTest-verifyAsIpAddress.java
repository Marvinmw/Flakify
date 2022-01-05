@Test public void verifyAsIpAddress(){
  assertTrue(OkHostnameVerifier.verifyAsIpAddress("127.0.0.1"));
  assertTrue(OkHostnameVerifier.verifyAsIpAddress("1.2.3.4"));
  assertTrue(OkHostnameVerifier.verifyAsIpAddress("::1"));
  assertTrue(OkHostnameVerifier.verifyAsIpAddress("2001:db8::1"));
  assertTrue(OkHostnameVerifier.verifyAsIpAddress("::192.168.0.1"));
  assertTrue(OkHostnameVerifier.verifyAsIpAddress("::ffff:192.168.0.1"));
  assertTrue(OkHostnameVerifier.verifyAsIpAddress("FEDC:BA98:7654:3210:FEDC:BA98:7654:3210"));
  assertTrue(OkHostnameVerifier.verifyAsIpAddress("1080:0:0:0:8:800:200C:417A"));
  assertTrue(OkHostnameVerifier.verifyAsIpAddress("1080::8:800:200C:417A"));
  assertTrue(OkHostnameVerifier.verifyAsIpAddress("FF01::101"));
  assertTrue(OkHostnameVerifier.verifyAsIpAddress("0:0:0:0:0:0:13.1.68.3"));
  assertTrue(OkHostnameVerifier.verifyAsIpAddress("0:0:0:0:0:FFFF:129.144.52.38"));
  assertTrue(OkHostnameVerifier.verifyAsIpAddress("::13.1.68.3"));
  assertTrue(OkHostnameVerifier.verifyAsIpAddress("::FFFF:129.144.52.38"));
  assertFalse(OkHostnameVerifier.verifyAsIpAddress("go"));
  assertFalse(OkHostnameVerifier.verifyAsIpAddress("localhost"));
  assertFalse(OkHostnameVerifier.verifyAsIpAddress("squareup.com"));
  assertFalse(OkHostnameVerifier.verifyAsIpAddress("www.nintendo.co.jp"));
}
