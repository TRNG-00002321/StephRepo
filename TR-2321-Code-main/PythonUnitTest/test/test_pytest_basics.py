import pytest
from src.calculator import Calculator

def test_addition():
    calculator = Calculator()
    result = calculator.add(1, 2)
    assert result == 3

def test_multiplication():
    calculator = Calculator()
    result = calculator.multiply(1, 2)
    assert result == 3,"Expected 3 got {}".format(result)

def test_is_even_with_even_numbers():
    """Even numbers return True."""
    calc = Calculator()
    assert calc.is_even(2) is True
    assert calc.is_even(0) is True
    assert calc.is_even(-4) is True

class TestCalculatorDivision:
    def test_divide_returns_correct_quotient(self):
        calc = Calculator()
        assert calc.divide(10, 2) == 5.0


def test_divide_by_zero_raises_exception():
    """Use pytest.raises to test exception handling."""
    calc = Calculator()

    with pytest.raises(ZeroDivisionError):
        calc.divide(10, 0)


def test_divide_by_zero_exception_message():
    """Capture exception to verify message."""
    calc = Calculator()

    with pytest.raises(ZeroDivisionError) as exc_info:
        calc.divide(10, 0)

    assert "zero" in str(exc_info.value).lower()