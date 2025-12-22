import unittest
from unittest.mock import Mock, MagicMock, patch
from unit_test.patch_demo import total

class TestTotal(unittest.TestCase):
    def test_calculate_total(self):
       # total.read=Mock()
        total.read=MagicMock()
        total.read.return_value=[1,2,3];
        result=total.calculate_total('')
        self.assertEqual(result,6)
        total.read.assert_called_once_with('')

    @patch('unit_test.patch_demo.total.read')
    def test_calculate_total_patch(self, mock_read):
        mock_read.return_value = [1, 2, 3]
        result = total.calculate_total('')
        self.assertEqual(result, 6)
        mock_read.assert_called_once_with('')

    def test_calculate_total_patch_ctx(self):
        with patch('unit_test.patch_demo.total.read') as mock_read:
            mock_read.return_value = [1, 2, 3,4]
            result = total.calculate_total('')
            self.assertEqual(result, 10)

    @patch('unit_test.patch_demo.total.read')
    def test_calculate_total_patch_side_effect(self, mock_read):
        # Instead of returning a static list, we simulate read() producing values
        mock_read.return_value = [1, 2, 3,4]

       # Patch calculate_total’s internal behavior (assuming it uses sum)
        with patch('unit_test.patch_demo.total.sum', side_effect=self.multiply_side_effect):
            result = total.calculate_total('')

        # Now 1 * 2 * 3 = 6
        self.assertEqual(result, 24)

        mock_read.assert_called_once_with('')

    # Side effect: multiply instead of add
    def multiply_side_effect(self,values):
        result = 1
        for v in values:
            result *= v
        return result

# Side effect that raises an error if values contain a negative number
    def negative_check_side_effect(self,values):
        if any(v < 0 for v in values):
            raise ValueError("Negative numbers are not allowed")
        return sum(values)

    @patch('unit_test.patch_demo.total.read')
    def test_calculate_total_with_negative_raises(self, mock_read):
        # Simulate read() returning a list with a negative number
        mock_read.return_value = [1, -5, 3]

        # Patch sum (or whatever function total.py uses to combine values)
        with patch('unit_test.patch_demo.total.sum', side_effect=self.negative_check_side_effect):
            with self.assertRaises(ValueError):
                total.calculate_total('')
                mock_read.assert_called_once_with('')
