import pytest
import os
import csv
from pathlib import Path
from src.calculator import Calculator
from test.test_fixtures_demo import calculator


# Parametrized test function
@pytest.mark.parametrize("input_a, input_b, expected_sum", [
    (1, 2, 3),
    (0, 0, 0),
    (-1, 5, 4),
    (10, -3, 7)
])
def test_add_numbers(input_a, input_b, expected_sum):
    """
    Tests the add_numbers function with various inputs.
    """
    calculator = Calculator()
    assert calculator.add(input_a, input_b) == expected_sum


def load_csv_data(path="data.csv"):
    csv_path = Path(__file__).parent / "data" / path
    with open(csv_path, newline="") as csvfile:
        reader = csv.reader(csvfile)
        next(reader, None)  # <-- Skip header row
        # Convert values to float or int automatically

        data = []
        for row in reader:
            a, b, expected = row
            # convert to numbers (int if possible, else float)
            def num(x):
                return int(x) if x.isdigit() or (x.startswith('-') and x[1:].isdigit()) else float(x)

            data.append((num(a), num(b), num(expected)))
        return data


@pytest.mark.parametrize("a,b,expected", load_csv_data())
def test_add(a, b, expected):
    calculator= Calculator()
    assert calculator.add(a, b) == expected
