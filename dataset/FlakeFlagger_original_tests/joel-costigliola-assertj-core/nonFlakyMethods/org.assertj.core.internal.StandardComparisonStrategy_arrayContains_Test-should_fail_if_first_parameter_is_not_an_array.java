@Test public void should_fail_if_first_parameter_is_not_an_array(){
  thrown.expect(IllegalArgumentException.class);
  standardComparisonStrategy.arrayContains("not an array","Pippin");
}
