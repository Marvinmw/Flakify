@Test public void testCustomContextPath() throws Exception {
  ResponseEntity<String> entity=new TestRestTemplate().getForEntity("http://localhost:" + this.port + "/admin/health",String.class);
  assertEquals(HttpStatus.OK,entity.getStatusCode());
  assertTrue("Wrong body: " + entity.getBody(),entity.getBody().contains("\"status\":\"UP\""));
}
