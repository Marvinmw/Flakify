@Test public void should_pass_if_difference_is_less_than_given_offset(){
  bytes.assertIsCloseTo(someInfo(),ONE,ONE,within(ONE));
  bytes.assertIsCloseTo(someInfo(),ONE,TWO,within(TEN));
}
