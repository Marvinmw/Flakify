@Test public void should_fail_since_actual_is_zero(){
  thrown.expectAssertionError("\nExpecting:\n <0>\nnot to be equal to:\n <0>\n");
  bytes.assertIsNotZero(someInfo(),(byte)0);
}
