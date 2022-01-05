@Test public void testChunkedOutputStream() throws IOException {
  SessionOutputBufferMock buffer=new SessionOutputBufferMock();
  ChunkedOutputStream out=new ChunkedOutputStream(buffer,2);
  out.write('1');
  out.write('2');
  out.write('3');
  out.write('4');
  out.finish();
  out.close();
  byte[] rawdata=buffer.getData();
  Assert.assertEquals(19,rawdata.length);
  Assert.assertEquals('2',rawdata[0]);
  Assert.assertEquals('\r',rawdata[1]);
  Assert.assertEquals('\n',rawdata[2]);
  Assert.assertEquals('1',rawdata[3]);
  Assert.assertEquals('2',rawdata[4]);
  Assert.assertEquals('\r',rawdata[5]);
  Assert.assertEquals('\n',rawdata[6]);
  Assert.assertEquals('2',rawdata[7]);
  Assert.assertEquals('\r',rawdata[8]);
  Assert.assertEquals('\n',rawdata[9]);
  Assert.assertEquals('3',rawdata[10]);
  Assert.assertEquals('4',rawdata[11]);
  Assert.assertEquals('\r',rawdata[12]);
  Assert.assertEquals('\n',rawdata[13]);
  Assert.assertEquals('0',rawdata[14]);
  Assert.assertEquals('\r',rawdata[15]);
  Assert.assertEquals('\n',rawdata[16]);
  Assert.assertEquals('\r',rawdata[17]);
  Assert.assertEquals('\n',rawdata[18]);
}
