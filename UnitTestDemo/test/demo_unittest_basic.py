import unittest
from src.Calculator import Calculator

class TestCalculator(unittest.TestCase):
    def setUp(self):
        self.calculator = Calculator()

    #@unittest.skip("skip test")
    def test_add(self):
        n1=1
        n2=2
        eResult=4
        result = self.calculator.add(n1, n2)
        self.assertIsNot(result, eResult)
        #self.assertEqual(self.calculator.add(1, 2), 3)

    def test_sub(self):
        n1=1
        n2=2
        eResult=-1
        result = self.calculator.subtract(n1, n2)
        self.assertEqual(result, eResult)
        #self.assertEqual(self.calculator.subtract(1, 2), -1)

    def test_is_even(self):
        n2=2
        result = self.calculator.is_even( n2)
        self.assertTrue(result)
        #self.assertTrue(self.calculator.is_even(n2))

    def test_divide_by_zero(self):
        with self.assertRaises(ZeroDivisionError):
            self.calculator.divide(1,0)

    def test_divide_by_zero_context(self):
        with self.assertRaises(ZeroDivisionError) as context:
            self.calculator.divide(1,0)
            self.assertEqual(str(context.exception), "division by zero")

    def tearDown(self):
        self.calculator = None

if __name__ == '__main__':
    unittest.main()