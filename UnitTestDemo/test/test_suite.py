import unittest

from src.TestStringMethods import TestStringMethods
from test.demo_unittest_basic import TestCalculator

suite = unittest.TestSuite()

#suite.addTest(unittest.makeSuite(TestCalculator))

# Add a specific test method from AnotherFeatureTests
suite.addTest(TestStringMethods('test_isupper'))

runner = unittest.TextTestRunner()
runner.run(suite)

# python -m unittest