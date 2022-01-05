@Test public void testAddTailRequestInterceptorNull(){
  HttpRequestInterceptor itcp=null;
  BasicHttpProcessor instance=new BasicHttpProcessor();
  instance.addRequestInterceptor(itcp,0);
  int itcpCount=instance.getRequestInterceptorCount();
  Assert.assertEquals(0,itcpCount);
  Assert.assertEquals(null,instance.getRequestInterceptor(itcpCount - 1));
}
