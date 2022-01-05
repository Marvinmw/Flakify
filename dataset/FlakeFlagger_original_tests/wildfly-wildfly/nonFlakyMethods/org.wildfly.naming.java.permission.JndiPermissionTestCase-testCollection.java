@Test public void testCollection(){
  final PermissionCollection permissionCollection=new JndiPermission("","").newPermissionCollection();
  Enumeration<Permission> e;
  permissionCollection.add(new JndiPermission("foo/bar","lookup,bind"));
  assertTrue(permissionCollection.implies(new JndiPermission("foo/bar","lookup,bind")));
  assertFalse(permissionCollection.implies(new JndiPermission("foo/bar","lookup,bind,unbind")));
  assertFalse(permissionCollection.implies(new JndiPermission("foo/bar","unbind")));
  assertNotNull(e=permissionCollection.elements());
  assertTrue(e.hasMoreElements());
  assertEquals(new JndiPermission("foo/bar","lookup,bind"),e.nextElement());
  assertFalse(e.hasMoreElements());
  permissionCollection.add(new JndiPermission("foo/bar","unbind"));
  assertTrue(permissionCollection.implies(new JndiPermission("foo/bar","lookup,bind")));
  assertTrue(permissionCollection.implies(new JndiPermission("foo/bar","lookup,bind,unbind")));
  assertTrue(permissionCollection.implies(new JndiPermission("foo/bar","unbind")));
  assertNotNull(e=permissionCollection.elements());
  assertTrue(e.hasMoreElements());
  assertEquals(new JndiPermission("foo/bar","lookup,bind,unbind"),e.nextElement());
  assertFalse(e.hasMoreElements());
  permissionCollection.add(new JndiPermission("-","lookup"));
  assertTrue(permissionCollection.implies(new JndiPermission("foo/bar","lookup,bind")));
  assertTrue(permissionCollection.implies(new JndiPermission("foo/bar","lookup,bind,unbind")));
  assertTrue(permissionCollection.implies(new JndiPermission("foo/bar","unbind")));
  assertTrue(permissionCollection.implies(new JndiPermission("baz/zap","lookup")));
  assertTrue(permissionCollection.implies(new JndiPermission("","lookup")));
  assertFalse(permissionCollection.implies(new JndiPermission("baz/zap","lookup,bind,unbind")));
  assertFalse(permissionCollection.implies(new JndiPermission("baz/zap","unbind")));
  assertNotNull(e=permissionCollection.elements());
  assertTrue(e.hasMoreElements());
  assertEquals(new JndiPermission("foo/bar","lookup,bind,unbind"),e.nextElement());
  assertTrue(e.hasMoreElements());
  assertEquals(new JndiPermission("-","lookup"),e.nextElement());
  assertFalse(e.hasMoreElements());
  permissionCollection.add(new JndiPermission("-","bind,unbind"));
  assertTrue(permissionCollection.implies(new JndiPermission("foo/bar","lookup,bind")));
  assertTrue(permissionCollection.implies(new JndiPermission("foo/bar","lookup,bind,unbind")));
  assertTrue(permissionCollection.implies(new JndiPermission("foo/bar","unbind")));
  assertTrue(permissionCollection.implies(new JndiPermission("baz/zap","lookup")));
  assertTrue(permissionCollection.implies(new JndiPermission("","lookup")));
  assertTrue(permissionCollection.implies(new JndiPermission("baz/zap","lookup,bind,unbind")));
  assertTrue(permissionCollection.implies(new JndiPermission("baz/zap","unbind")));
  assertNotNull(e=permissionCollection.elements());
  assertTrue(e.hasMoreElements());
  assertEquals(new JndiPermission("-","lookup,bind,unbind"),e.nextElement());
  assertFalse(e.hasMoreElements());
}
