@Test public void should_pass_if_actual_is_less_than_other(){
  shorts.assertLessThanOrEqualTo(someInfo(),(short)6,(short)8);
}
