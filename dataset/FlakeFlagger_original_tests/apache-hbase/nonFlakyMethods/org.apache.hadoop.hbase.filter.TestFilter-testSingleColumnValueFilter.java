public void testSingleColumnValueFilter() throws IOException {
  Put p=new Put(ROWS_ONE[2]);
  p.add(FAMILIES[0],QUALIFIERS_ONE[2],VALUES[1]);
  this.region.put(p);
  List<Filter> filters=new ArrayList<Filter>();
  filters.add(new SingleColumnValueFilter(FAMILIES[0],QUALIFIERS_ONE[0],CompareOp.EQUAL,VALUES[0]));
  filters.add(new SingleColumnValueFilter(FAMILIES[0],QUALIFIERS_ONE[2],CompareOp.EQUAL,VALUES[1]));
  Filter f=new FilterList(Operator.MUST_PASS_ALL,filters);
  Scan s=new Scan(ROWS_ONE[0],ROWS_TWO[0]);
  s.addFamily(FAMILIES[0]);
  s.setFilter(f);
  KeyValue[] kvs={new KeyValue(ROWS_ONE[2],FAMILIES[0],QUALIFIERS_ONE[0],VALUES[0]),new KeyValue(ROWS_ONE[2],FAMILIES[0],QUALIFIERS_ONE[2],VALUES[1]),new KeyValue(ROWS_ONE[2],FAMILIES[0],QUALIFIERS_ONE[3],VALUES[0])};
  verifyScanNoEarlyOut(s,1,3);
  verifyScanFull(s,kvs);
  filters=new ArrayList<Filter>();
  filters.add(new SkipFilter(new SingleColumnValueFilter(FAMILIES[0],QUALIFIERS_ONE[0],CompareOp.EQUAL,VALUES[0])));
  filters.add(new SkipFilter(new SingleColumnValueFilter(FAMILIES[0],QUALIFIERS_ONE[2],CompareOp.EQUAL,VALUES[1])));
  f=new FilterList(Operator.MUST_PASS_ALL,filters);
  s=new Scan(ROWS_ONE[0],ROWS_TWO[0]);
  s.addFamily(FAMILIES[0]);
  s.setFilter(f);
  verifyScanNoEarlyOut(s,1,3);
  verifyScanFull(s,kvs);
  byte[][] ROWS_THREE={Bytes.toBytes("rowThree-0"),Bytes.toBytes("rowThree-1"),Bytes.toBytes("rowThree-2"),Bytes.toBytes("rowThree-3")};
  KeyValue[] srcKVs=new KeyValue[]{new KeyValue(ROWS_THREE[0],FAMILIES[0],QUALIFIERS_ONE[0],VALUES[0]),new KeyValue(ROWS_THREE[1],FAMILIES[0],QUALIFIERS_ONE[0],VALUES[1]),new KeyValue(ROWS_THREE[2],FAMILIES[0],QUALIFIERS_ONE[1],VALUES[0]),new KeyValue(ROWS_THREE[3],FAMILIES[0],QUALIFIERS_ONE[1],VALUES[1])};
  for (  KeyValue kv : srcKVs) {
    this.region.put(new Put(kv.getRow()).add(kv));
  }
  SingleColumnValueFilter scvf=new SingleColumnValueFilter(FAMILIES[0],QUALIFIERS_ONE[0],CompareOp.EQUAL,VALUES[0]);
  s=new Scan(ROWS_THREE[0],Bytes.toBytes("rowThree-4"));
  s.addFamily(FAMILIES[0]);
  s.setFilter(scvf);
  kvs=new KeyValue[]{srcKVs[0],srcKVs[2],srcKVs[3]};
  verifyScanFull(s,kvs);
  scvf=new SingleColumnValueFilter(FAMILIES[0],QUALIFIERS_ONE[0],CompareOp.EQUAL,VALUES[0]);
  scvf.setFilterIfMissing(true);
  s=new Scan(ROWS_THREE[0],Bytes.toBytes("rowThree-4"));
  s.addFamily(FAMILIES[0]);
  s.setFilter(scvf);
  kvs=new KeyValue[]{srcKVs[0]};
  verifyScanFull(s,kvs);
  scvf=new SingleColumnValueFilter(FAMILIES[0],QUALIFIERS_ONE[1],CompareOp.EQUAL,VALUES[1]);
  scvf.setFilterIfMissing(true);
  s=new Scan(ROWS_THREE[0],Bytes.toBytes("rowThree-4"));
  s.addFamily(FAMILIES[0]);
  s.setFilter(scvf);
  kvs=new KeyValue[]{srcKVs[3]};
  verifyScanFull(s,kvs);
  KeyValue kvA=new KeyValue(ROWS_THREE[0],FAMILIES[0],QUALIFIERS_ONE[1],VALUES[0]);
  this.region.put(new Put(kvA.getRow()).add(kvA));
  scvf=new SingleColumnValueFilter(FAMILIES[0],QUALIFIERS_ONE[1],CompareOp.EQUAL,VALUES[1]);
  scvf.setFilterIfMissing(true);
  s=new Scan(ROWS_THREE[0],Bytes.toBytes("rowThree-4"));
  s.addFamily(FAMILIES[0]);
  s.setFilter(scvf);
  kvs=new KeyValue[]{srcKVs[3]};
  verifyScanFull(s,kvs);
}