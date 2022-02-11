import pandas as pd

d=pd.read_csv("dataset/graph_info.csv")
p=pd.read_csv("dataset/Flakify_dataset.csv", sep=",")

testset=d["Test"].tolist()
indexNames=[]
print(len(d))
print(len(p["package_name"]))
#assert "dataObject.getDataObjectDefinitionKey());" not in p["package_name"].tolist()
for i in range(len(p)) :
  try:
    if p.loc[i, "package_name"]+"."+ p.loc[i, "class_name"]+"#"+p.loc[i, "test_name"] not in testset:
        indexNames.append(i)
  except:
      pass
# Delete these row indexes from dataFrame
p.drop(indexNames , inplace=True)
p.to_csv("dataset/filtered_Flakify_dataset.csv", index=False)
print(p["flaky"].sum())
print(len(p))