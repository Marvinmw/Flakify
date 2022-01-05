@Test public void testDefaultPasswordAutogeneratedIfUnresolovedPlaceholder(){
  this.binder.bind(new MutablePropertyValues(Collections.singletonMap("security.user.password","${ADMIN_PASSWORD}")));
  assertFalse(this.binder.getBindingResult().hasErrors());
  assertTrue(this.security.getUser().isDefaultPassword());
}
