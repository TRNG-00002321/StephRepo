import pandas as pd


data = pd.read_csv('Datasets/data.csv')

my_dataframe = pd.DataFrame([1, 2, 3, 4, 5, 6])
print(my_dataframe)


my_series = pd.Series([1, 2, 3, 4, 5, 6],index=['a', 'b', 'c', 'd', 'e', 'f'])
print(my_series)


calories = {'day1': 420,'day2':380,'day3':450}
newcalories = pd.Series(calories)
print(calories)
print(newcalories)

#data['Duration'].plot(kind='hist')

data2 = {
    "calories":[420,380,450],
    "duration":[50,40,45]
}

df = pd.DataFrame(data2, index=['day1', 'day2', 'day3'])
print(df)

print(df.loc['day2'])

#print(data.to_string())
print(data.describe())
print(data.info())
data2 = data.dropna()
print(data2)



#Json section of Pandas
datajson = pd.read_json('Datasets/data.json')
print(datajson.info())

datajson2 = datajson.drop_duplicates()
datajson3 = datajson2.dropna()
print(datajson2['Duration'].sum())














