@Test public void testInvalidInput() throws Exception {
  String s="10;key=\"value\"\r\n1234567890123456\r\n" + "5\r\n12345\r\n5\r\n12345\r\n0\r\nFooter1 abcde\r\n\r\n";
  ReadableByteChannel channel=new ReadableByteChannelMock(new String[]{s},"US-ASCII");
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,256,params);
  HttpTransportMetricsImpl metrics=new HttpTransportMetricsImpl();
  ChunkDecoder decoder=new ChunkDecoder(channel,inbuf,metrics);
  try {
    decoder.read(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
