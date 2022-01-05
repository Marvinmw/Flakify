@Test public void should_remove_first_n_with_clustering_components() throws Exception {
  Long partitionKey=RandomUtils.nextLong();
  Object[] clusteringComponents=new Object[]{1,"name"};
  builder.partitionComponentsInternal(partitionKey).removeFirst(9,clusteringComponents);
  assertThat(Whitebox.getInternalState(builder,"limit")).isEqualTo(9);
  assertThat(Whitebox.<List<Object>>getInternalState(builder,"fromClusterings")).containsExactly(clusteringComponents);
  assertThat(Whitebox.<List<Object>>getInternalState(builder,"toClusterings")).containsExactly(clusteringComponents);
  verify(sliceQueryExecutor).remove(anySliceQuery());
}
