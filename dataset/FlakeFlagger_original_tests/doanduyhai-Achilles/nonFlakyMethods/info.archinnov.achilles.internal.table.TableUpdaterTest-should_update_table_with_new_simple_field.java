@Test public void should_update_table_with_new_simple_field() throws Exception {
  PropertyMeta idMeta=PropertyMetaTestBuilder.valueClass(Long.class).type(ID).field("id").build();
  PropertyMeta longColPM=PropertyMetaTestBuilder.valueClass(Long.class).type(SIMPLE).field("longCol").build();
  meta=new EntityMeta();
  meta.setAllMetasExceptId(asList(longColPM));
  meta.setIdMeta(idMeta);
  meta.setTableName("tableName");
  meta.setClassName("entityName");
  meta.setSchemaUpdateEnabled(true);
  updater.updateTableForEntity(session,meta,tableMeta);
  verify(session).execute(stringCaptor.capture());
  assertThat(stringCaptor.getValue()).isEqualTo("\n\tALTER TABLE tableName ADD longCol bigint");
}
