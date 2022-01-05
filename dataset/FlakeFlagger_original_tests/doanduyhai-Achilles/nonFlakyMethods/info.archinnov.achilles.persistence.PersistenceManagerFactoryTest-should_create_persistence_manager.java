@Test public void should_create_persistence_manager() throws Exception {
  Map<Class<?>,EntityMeta> entityMetaMap=new HashMap<Class<?>,EntityMeta>();
  PersistenceContextFactory contextFactory=mock(PersistenceContextFactory.class);
  pmf.entityMetaMap=entityMetaMap;
  pmf.configContext=configContext;
  pmf.daoContext=daoContext;
  pmf.contextFactory=contextFactory;
  PersistenceManager manager=pmf.createPersistenceManager();
  assertThat(manager).isNotNull();
}
