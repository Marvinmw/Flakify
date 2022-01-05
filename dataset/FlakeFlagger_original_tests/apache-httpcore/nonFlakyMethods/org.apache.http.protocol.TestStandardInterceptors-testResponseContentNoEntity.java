@Test public void testResponseContentNoEntity() throws Exception {
  HttpContext context=new BasicHttpContext(null);
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
  ResponseContent interceptor=new ResponseContent();
  interceptor.process(response,context);
  Header header=response.getFirstHeader(HTTP.CONTENT_LEN);
  Assert.assertNotNull(header);
  Assert.assertEquals("0",header.getValue());
}
