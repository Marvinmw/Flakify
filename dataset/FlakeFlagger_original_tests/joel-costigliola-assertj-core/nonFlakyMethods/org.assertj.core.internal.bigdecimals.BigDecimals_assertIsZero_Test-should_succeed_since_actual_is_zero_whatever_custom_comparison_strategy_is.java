@Test public void should_succeed_since_actual_is_zero_whatever_custom_comparison_strategy_is(){
  bigDecimalsWithComparatorComparisonStrategy.assertIsZero(someInfo(),BigDecimal.ZERO);
}
