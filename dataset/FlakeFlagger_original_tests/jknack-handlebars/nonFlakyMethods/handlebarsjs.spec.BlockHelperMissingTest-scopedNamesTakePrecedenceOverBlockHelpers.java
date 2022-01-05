@Test public void scopedNamesTakePrecedenceOverBlockHelpers() throws IOException {
  Hash helpers=$("goodbye",new Helper<Map<String,Object>>(){
    @Override public CharSequence apply(    final Map<String,Object> context,    final Options options) throws IOException {
      return context.get("goodbye").toString().toUpperCase() + options.fn(context);
    }
  }
,"cruel",new Helper<String>(){
    @Override public CharSequence apply(    final String world,    final Options options) throws IOException {
      return "cruel " + world.toUpperCase();
    }
  }
);
  shouldCompileTo("{{#goodbye}} {{cruel world}}{{/goodbye}} {{this.goodbye}}","{goodbye: goodbye, world: world}",helpers,"GOODBYE cruel WORLD goodbye");
}
