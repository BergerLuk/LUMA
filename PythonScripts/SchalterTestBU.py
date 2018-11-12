import RPi.GPIO as GPIO
import time
GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(True)

i = True
t = 1

def button_callback(channel):
    print("irgenwas")
    global i
    global t
    if i:
        # f = open("/home/pi/NetBeansProjects/LUMA/PythonScripts/KnopfDruck.txt", "w")
        #f.write("1")
        #f.close()
        i = False
        print(i)
        time.sleep(t)
        
    elif not i:
        # f = open("/home/pi/NetBeansProjects/LUMA/PythonScripts/KnopfDruck.txt", "w")
        #f.write("0")
        #f.close()
        i = True
        print (i)
        time.sleep(t)
        
    print("rdy")
        
print("Funktion ende")
GPIO.setup(24, GPIO.IN, pull_up_down=GPIO.PUD_DOWN)
GPIO.add_event_detect(24,GPIO.RISING,callback=button_callback)

input("Press enter to quit \n\n")

GPIO.cleanup()

