@Test public void should_pass_if_actual_is_greater_than_other_according_to_custom_comparison_strategy(){
  integersWithAbsValueComparisonStrategy.assertGreaterThan(someInfo(),-8,6);
}
