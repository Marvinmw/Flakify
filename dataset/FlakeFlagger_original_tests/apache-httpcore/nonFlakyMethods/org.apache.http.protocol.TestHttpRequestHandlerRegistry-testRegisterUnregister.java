@Test public void testRegisterUnregister() throws Exception {
  HttpRequestHandler h1=new DummyHttpRequestHandler();
  HttpRequestHandler h2=new DummyHttpRequestHandler();
  HttpRequestHandler h3=new DummyHttpRequestHandler();
  HttpRequestHandlerRegistry registry=new HttpRequestHandlerRegistry();
  registry.register("/h1",h1);
  registry.register("/h2",h2);
  registry.register("/h3",h3);
  Map<String,HttpRequestHandler> handlers=registry.getHandlers();
  Assert.assertEquals(3,handlers.size());
  HttpRequestHandler h;
  h=registry.lookup("/h1");
  Assert.assertNotNull(h);
  Assert.assertTrue(h1 == h);
  h=registry.lookup("/h2");
  Assert.assertNotNull(h);
  Assert.assertTrue(h2 == h);
  h=registry.lookup("/h3");
  Assert.assertNotNull(h);
  Assert.assertTrue(h3 == h);
  registry.unregister("/h1");
  h=registry.lookup("/h1");
  Assert.assertNull(h);
  handlers=registry.getHandlers();
  Assert.assertEquals(2,handlers.size());
  Map<String,HttpRequestHandler> map=new HashMap<String,HttpRequestHandler>();
  map.put("/a1",h1);
  map.put("/a2",h2);
  map.put("/a3",h3);
  registry.setHandlers(map);
  handlers=registry.getHandlers();
  Assert.assertEquals(3,handlers.size());
  h=registry.lookup("/h2");
  Assert.assertNull(h);
  h=registry.lookup("/h3");
  Assert.assertNull(h);
  h=registry.lookup("/a1");
  Assert.assertNotNull(h);
  Assert.assertTrue(h1 == h);
}
