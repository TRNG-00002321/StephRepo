import unittest
from unittest.mock import Mock

from test import odometer


class TestOdometer(unittest.TestCase):
    def test_speed_zero(self):
        odometer.speed = Mock()
        odometer.speed.return_value = 0
        self.assertTrue(odometer.alert())

    def test_speed_normal(self):
        odometer.speed = Mock()
        odometer.speed.return_value = 65
        self.assertFalse(odometer.alert())

    def test_speed_high_edge_case(self):
        odometer.speed = Mock()
        odometer.speed.return_value = 120
        self.assertFalse(odometer.alert())



if __name__ == '__main__':
    unittest.main()