from random import randint


def speed():
    return randint(40,120)

def alert():
    s=speed()
    if s<60 or s>120:
        return True
    return False

