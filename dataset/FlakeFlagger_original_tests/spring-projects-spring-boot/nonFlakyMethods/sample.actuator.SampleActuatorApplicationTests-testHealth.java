@Test public void testHealth() throws Exception {
  ResponseEntity<String> entity=new TestRestTemplate().getForEntity("http://localhost:" + this.port + "/health",String.class);
  assertEquals(HttpStatus.OK,entity.getStatusCode());
  assertTrue("Wrong body: " + entity.getBody(),entity.getBody().contains("\"status\":\"UP\""));
  assertFalse("Wrong body: " + entity.getBody(),entity.getBody().contains("\"hello\":\"1\""));
}
