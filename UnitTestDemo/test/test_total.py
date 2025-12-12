import unittest
from unittest.mock import Mock, MagicMock, patch

from test import total


class TestOdometer(unittest.TestCase):
    def test_total_no_numbers(self):
        #total.read = Mock()
        total.read = MagicMock()
        total.read.return_value=[]
        result = total.calculate_total('')
        self.assertEqual(result,0)
        total.read.assert_called_once_with('')

    def test_calculate_total_patch(self):
        with patch('test.total.read') as mock_read:
            mock_read.return_value = [1,2]
            result = total.calculate_total('')
            self.assertEqual(3,result)

    @patch('test.total.read')
    def test_calculate_total_patch_again(self, mock_read):
        mock_read.return_value = [1,2]
        result = total.calculate_total('')
        self.assertEqual(3,result)

    def multiply_values(self,values):
        result = 1
        for v in values:
            result *= v
        return result

    @patch('test.total.read')
    def test_multiply_values(self,mock_read):
        mock_read.return_value = [1,2,3,4]

        with patch('test.total.sum',side_effect=self.multiply_values):
            result = total.calculate_total('')

        self.assertEqual(24,result)
        mock_read.assert_called_once_with('')

    def side_effect_neg_number(self,values):
        if any(v < 0 for v in values):
            raise ValueError('Negative numbers not allowed')
        return sum(values)

    @patch('test.total.read')
    def test_negative_values(self, mock_read):
        mock_read.return_value = [1, 2, -3, 4]

        with self.assertRaises(ValueError):
            with patch('test.total.sum', side_effect=self.side_effect_neg_number):
                total.calculate_total('')

        mock_read.assert_called_once_with('')


if __name__ == '__main__':
    unittest.main()