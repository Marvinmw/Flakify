@Test public void requestOnlyIfCachedWithUnhelpfulResponseCached() throws IOException {
  server.enqueue(new MockResponse().setBody("A"));
  server.play();
  assertEquals("A",readAscii(openConnection(server.getUrl("/"))));
  HttpURLConnection connection=openConnection(server.getUrl("/"));
  connection.addRequestProperty("Cache-Control","only-if-cached");
  assertGatewayTimeout(connection);
}
