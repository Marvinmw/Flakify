@Test public void testDefaultPasswordAutogeneratedIfEmpty(){
  SimpleAuthenticationProperties security=new SimpleAuthenticationProperties();
  RelaxedDataBinder binder=new RelaxedDataBinder(security,"security");
  binder.bind(new MutablePropertyValues(Collections.singletonMap("shell.auth.simple.user.password","")));
  assertFalse(binder.getBindingResult().hasErrors());
  assertTrue(security.getUser().isDefaultPassword());
}
