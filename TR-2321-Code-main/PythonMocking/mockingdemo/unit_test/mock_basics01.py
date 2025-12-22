# 👋 Hello, Mock!
from unittest.mock import Mock

# 🎨 Creating a simple mock
weather_api = Mock()

# 🎯 Configure the mock to return specific data
weather_api.get_temperature.return_value = 25  # 🌡️ Always sunny!

# 💡 Use it like a real object
temp = weather_api.get_temperature("London")
print(f"Temperature: {temp}°C")  # Temperature: 25°C

# 🔍 Check if the method was called
weather_api.get_temperature.assert_called_with("London")