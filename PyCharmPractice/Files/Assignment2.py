#copy an image to an image file
import os

path = os.path.join("C:\\", "Users", "Stephenie", "Pictures", "ClockBirds", "lantern.png")
print(path)
with open(path,"rb") as f:
    with open("Lantern2.png","wb") as g:
        g.write(f.read())