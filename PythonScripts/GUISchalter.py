filepath = "/home/pi/NetBeansProjects/LUMA/PythonScripts/KnopfDruck.txt"
f = open(filepath , "r")
i = int(f.read()) + 1
f.close()
g = open(filepath , "w")
g.write(str(i))
g.close()
