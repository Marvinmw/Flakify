@Test public void testMalformedFooters() throws Exception {
  String s="10;key=\"value\"\r\n1234567890123456\r\n" + "5\r\n12345\r\n5\r\n12345\r\n0\r\nFooter1 abcde\r\n\r\n";
  ReadableByteChannel channel=new ReadableByteChannelMock(new String[]{s},"US-ASCII");
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,256,params);
  HttpTransportMetricsImpl metrics=new HttpTransportMetricsImpl();
  ChunkDecoder decoder=new ChunkDecoder(channel,inbuf,metrics);
  ByteBuffer dst=ByteBuffer.allocate(1024);
  try {
    decoder.read(dst);
    Assert.fail("MalformedChunkCodingException should have been thrown");
  }
 catch (  IOException ex) {
  }
}
