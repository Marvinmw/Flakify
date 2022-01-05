/** 
 * Make a GET request with a URL that needs encoding
 * @throws Exception
 */
@Test public void getUrlEncodedWithPercent() throws Exception {
  String unencoded="/%";
  final AtomicReference<String> path=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      path.set(request.getPathInfo());
      response.setStatus(HTTP_OK);
    }
  }
;
  HttpRequest request=get(encode(url + unencoded));
  assertTrue(request.ok());
  assertEquals(unencoded,path.get());
}
