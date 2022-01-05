@Test public void recursiveFilesByPattern(){
  List<String> urls=ResourceUtils.getUrls("src/test/resources/dir-sample/**/*.groovy",ClassUtils.getDefaultClassLoader());
  assertEquals(1,urls.size());
  assertTrue(urls.get(0).startsWith("file:"));
}
