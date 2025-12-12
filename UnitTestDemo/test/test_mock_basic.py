import unittest
from unittest.mock import Mock

def greet(func):
    return "Hello " + func()





class TestMockBasic(unittest.TestCase):
    def test_mock_return_value_and_assert_call(self):
        fake_func = Mock(return_value="World")
        result = greet(fake_func)

        self.assertEqual(result, "Hello World")

        fake_func.assert_called_once()

if __name__ == '__main__':
    unittest.main()
    weather_api = Mock()
    weather_api.get_temp.return_value = 25

    temp = weather_api.get_temp('Plano')

    weather_api.get_temp.assert_called_with('Plano')