@Test public void should_pass_if_actual_contains_value_at_index(){
  arrays.assertContains(someInfo(),actual,8d,atIndex(1));
}
