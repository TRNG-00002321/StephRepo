import unittest
from src.calculator import Calculator

class TestCalculator(unittest.TestCase):
    def setUp(self):
        self.calc = Calculator()
    def tearDown(self):
        self.calc=None

    def test_add(self):
        result = self.calc.add(1, 2)
        self.assertEqual(result, 3)

    def test_is_even_true(self):
        result = self.calc.is_even(3)
        self.assertEqual(result, True)

    def test_greater_than(self ):
        self.assertGreater(self.calc.add(1, 2), 8)

    def test_divide_by_zero_raises_exception(self):
        with self.assertRaises(ZeroDivisionError):
            self.calc.divide(1, 0)

    def test_divide_by_zero_exception_message(self):
        with self.assertRaises(ZeroDivisionError) as context:
            self.calc.divide(1, 0)

        self.assertIn(str(context.exception), "Cannot divide by zero")