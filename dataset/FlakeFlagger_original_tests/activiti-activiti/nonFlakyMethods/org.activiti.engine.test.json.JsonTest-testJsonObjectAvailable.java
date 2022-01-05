@Deployment public void testJsonObjectAvailable(){
  Map<String,Object> vars=new HashMap<String,Object>();
  ObjectNode varNode=objectMapper.createObjectNode();
  varNode.put("var","myValue");
  vars.put(MY_JSON_OBJ,varNode);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testJsonAvailableProcess",vars);
  ObjectNode value=(ObjectNode)runtimeService.getVariable(processInstance.getId(),MY_JSON_OBJ);
  assertNotNull(value);
  assertEquals("myValue",value.get("var").asText());
  ObjectNode var2Node=objectMapper.createObjectNode();
  var2Node.put("var","myValue");
  var2Node.put("var2","myOtherValue");
  runtimeService.setVariable(processInstance.getId(),MY_JSON_OBJ,var2Node);
  value=(ObjectNode)runtimeService.getVariable(processInstance.getId(),MY_JSON_OBJ);
  assertNotNull(value);
  assertEquals("myValue",value.get("var").asText());
  assertEquals("myOtherValue",value.get("var2").asText());
  Task task=taskService.createTaskQuery().active().singleResult();
  assertNotNull(task);
  ObjectNode var3Node=objectMapper.createObjectNode();
  var3Node.put("var","myValue");
  var3Node.put("var2","myOtherValue");
  var3Node.put("var3","myThirdValue");
  vars=new HashMap<String,Object>();
  vars.put(MY_JSON_OBJ,var3Node);
  vars.put(BIG_JSON_OBJ,createBigJsonObject());
  taskService.complete(task.getId(),vars);
  value=(ObjectNode)runtimeService.getVariable(processInstance.getId(),MY_JSON_OBJ);
  assertNotNull(value);
  assertEquals("myValue",value.get("var").asText());
  assertEquals("myOtherValue",value.get("var2").asText());
  assertEquals("myThirdValue",value.get("var3").asText());
  value=(ObjectNode)runtimeService.getVariable(processInstance.getId(),BIG_JSON_OBJ);
  assertNotNull(value);
  assertEquals(createBigJsonObject().toString(),value.toString());
  task=taskService.createTaskQuery().active().singleResult();
  assertNotNull(task);
  assertEquals("userTaskSuccess",task.getTaskDefinitionKey());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    List<HistoricVariableInstance> historicVariableInstances=historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstance.getProcessInstanceId()).orderByVariableName().asc().list();
    assertEquals(2,historicVariableInstances.size());
    assertEquals(BIG_JSON_OBJ,historicVariableInstances.get(0).getVariableName());
    value=(ObjectNode)historicVariableInstances.get(0).getValue();
    assertNotNull(value);
    assertEquals(createBigJsonObject().toString(),value.toString());
    assertEquals(MY_JSON_OBJ,historicVariableInstances.get(1).getVariableName());
    value=(ObjectNode)historicVariableInstances.get(1).getValue();
    assertNotNull(value);
    assertEquals("myValue",value.get("var").asText());
    assertEquals("myOtherValue",value.get("var2").asText());
    assertEquals("myThirdValue",value.get("var3").asText());
  }
  runtimeService.removeVariable(processInstance.getId(),MY_JSON_OBJ);
  assertNull(runtimeService.getVariable(processInstance.getId(),MY_JSON_OBJ));
  runtimeService.removeVariable(processInstance.getId(),BIG_JSON_OBJ);
  assertNull(runtimeService.getVariable(processInstance.getId(),BIG_JSON_OBJ));
}