@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testRemoveVariableLocal(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("variable1","value1");
  vars.put("variable2","value2");
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);
  runtimeService.removeVariableLocal(processInstance.getId(),"variable1");
  assertNull(runtimeService.getVariable(processInstance.getId(),"variable1"));
  assertNull(runtimeService.getVariableLocal(processInstance.getId(),"variable1"));
  assertEquals("value2",runtimeService.getVariable(processInstance.getId(),"variable2"));
  checkHistoricVariableUpdateEntity("variable1",processInstance.getId());
}
