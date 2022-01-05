@Test public void should_throw_error_if_Index_is_out_of_bounds(){
  thrown.expectIndexOutOfBoundsException("Index should be between <0> and <2> (inclusive,) but was:\n <6>");
  lists.assertContains(someInfo(),actual,"Yoda",atIndex(6));
}
