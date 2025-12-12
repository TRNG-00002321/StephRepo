from unittest.mock import Mock

mock = Mock()

mock.api.return_value = {'id':1,'name':'Steph'}

#calls the api
print(mock.api)

#calls api function
print(mock.api())