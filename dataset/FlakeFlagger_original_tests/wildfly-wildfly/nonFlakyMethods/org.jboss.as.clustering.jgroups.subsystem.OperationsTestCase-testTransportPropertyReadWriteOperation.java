@Test public void testTransportPropertyReadWriteOperation() throws Exception {
  KernelServices services=this.buildKernelServices();
  ModelNode result=services.executeOperation(getTransportGetPropertyOperation("maximal","TCP","enable_bundling"));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  Assert.assertEquals("true",ExpressionResolver.TEST_RESOLVER.resolveExpressions(result.get(RESULT)).asString());
  result=services.executeOperation(getTransportPutPropertyOperation("maximal","TCP","enable_bundling","false"));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  result=services.executeOperation(getTransportGetPropertyOperation("maximal","TCP","enable_bundling"));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  Assert.assertEquals("false",result.get(RESULT).asString());
  result=services.executeOperation(getTransportRemovePropertyOperation("maximal","TCP","enable_bundling"));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  result=services.executeOperation(getTransportGetPropertyOperation("maximal","TCP","enable_bundling"));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  Assert.assertFalse(result.get(RESULT).isDefined());
  result=services.executeOperation(getTransportPropertyAddOperation("maximal","TCP","shared","false"));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  result=services.executeOperation(getTransportPropertyReadOperation("maximal","TCP","shared"));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  Assert.assertEquals("false",result.get(RESULT).asString());
  result=services.executeOperation(getTransportGetPropertyOperation("maximal","TCP","shared"));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  Assert.assertEquals("false",result.get(RESULT).asString());
  result=services.executeOperation(getTransportPropertyWriteOperation("maximal","TCP","shared","true"));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  result=services.executeOperation(getTransportPropertyReadOperation("maximal","TCP","shared"));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  Assert.assertEquals("true",result.get(RESULT).asString());
  result=services.executeOperation(getTransportGetPropertyOperation("maximal","TCP","shared"));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  Assert.assertEquals("true",result.get(RESULT).asString());
  result=services.executeOperation(getTransportPropertyRemoveOperation("maximal","TCP","shared"));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  result=services.executeOperation(getTransportGetPropertyOperation("maximal","TCP","shared"));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  Assert.assertFalse(result.get(RESULT).isDefined());
}
