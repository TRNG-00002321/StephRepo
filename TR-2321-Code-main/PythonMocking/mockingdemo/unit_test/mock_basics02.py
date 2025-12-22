from unittest.mock import Mock


# create a new mock object
mock = Mock()

# mock the api function
mock.api.return_value = {
    'id': 1,
    'message': 'hello'
}
# call the api function
print(mock.api())

# call the api
print(mock.api)