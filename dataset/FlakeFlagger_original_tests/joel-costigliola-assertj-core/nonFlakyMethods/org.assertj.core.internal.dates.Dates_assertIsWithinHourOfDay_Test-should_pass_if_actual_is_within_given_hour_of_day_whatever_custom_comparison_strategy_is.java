@Test public void should_pass_if_actual_is_within_given_hour_of_day_whatever_custom_comparison_strategy_is(){
  datesWithCustomComparisonStrategy.assertIsWithinHourOfDay(someInfo(),actual,3);
}
