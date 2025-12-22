from random import randint

def speed():
    return randint(40, 120)

def alert():
    s = speed()
    if s < 60 or s > 100:
        return True
    return False

# The speed() returns the current speed of a vehicle.
# It returns a random value between 40 and 120.
# In the real world, the function would read the data from the odometer.
#The alert() function returns true if the current speed is lower than 60 km/ # and higher than 120 km/h.
# The alert() function uses the speed() function to get the current speed.